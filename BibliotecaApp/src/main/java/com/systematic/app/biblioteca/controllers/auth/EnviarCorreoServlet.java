package com.systematic.app.biblioteca.controllers.auth;

import com.systematic.app.biblioteca.config.DBConnection;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAOImpl;
import com.systematic.app.biblioteca.models.Usuario;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import com.systematic.app.biblioteca.dao.usuario.UsuarioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/enviar-recuperacion")
public class EnviarCorreoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        System.out.println("Email recibido del formulario: " + email);

        UsuarioDAO usuarioDAO = null;
        try {
            usuarioDAO = new UsuarioDAOImpl(DBConnection.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(EnviarCorreoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Optional<Usuario> usuarioOpt = usuarioDAO.findByEmail(email); 

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                System.out.println("Usuario encontrado: " + usuario.getNombre());

                // Generar token
                String token = UUID.randomUUID().toString();
                Timestamp expiracion = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000); // 30 min

                usuarioDAO.guardarTokenRecuperacion(email, token, expiracion);

                // Enlace completo
                String baseURL = req.getRequestURL().toString().replace(req.getServletPath(), "");
                String enlace = baseURL + "/views/cambiar-password.jsp?token=" + token;
                System.out.println("Enlace de recuperación generado: " + enlace);

                // Configurar correo
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                final String correoEmisor = "TU_CORREO@gmail.com";
                final String claveApp = "TU_CLAVE_APP";

                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correoEmisor, claveApp);
                    }
                });

                Message mensaje = new MimeMessage(session);
                mensaje.setFrom(new InternetAddress(correoEmisor));
                mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                mensaje.setSubject("Recuperación de Contraseña - Sistema Biblioteca");
                mensaje.setText("Hola " + usuario.getNombre() + ",\n\nHaz clic en este enlace para cambiar tu contraseña:\n" + enlace);

                Transport.send(mensaje);
                System.out.println("Correo enviado correctamente.");

                // Mostrar mensaje
                resp.sendRedirect("recuperar-password.jsp?enviado=1");
            } else {
                resp.sendRedirect("recuperar-password.jsp?error=correo-no-existe");
            }

        } catch (MessagingException e) {
            e.printStackTrace();
            resp.sendRedirect("recuperar-password.jsp?error=envio");
        }
    }
}
