package com.systematic.app.biblioteca.models;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {
    private Integer idPrestamo;
    private Integer idUsuario;
    private Integer idLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private LocalDate fechaDevolucionEstimada;
    private String estado; // "ACTIVO", "DEVUELTO", "VENCIDO"

    // Constructores
    public Prestamo() {
        this.estado = "ACTIVO";
    }

    public Prestamo(Integer idUsuario, Integer idLibro, LocalDate fechaPrestamo, 
                   LocalDate fechaDevolucionEstimada) {
        this();
        this.idUsuario = Objects.requireNonNull(idUsuario, "ID Usuario no puede ser nulo");
        this.idLibro = Objects.requireNonNull(idLibro, "ID Libro no puede ser nulo");
        this.fechaPrestamo = Objects.requireNonNull(fechaPrestamo, "Fecha préstamo no puede ser nula");
        setFechaDevolucionEstimada(fechaDevolucionEstimada);
    }

    // Getters y Setters
    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        if (idPrestamo != null && idPrestamo <= 0) {
            throw new IllegalArgumentException("ID Préstamo debe ser positivo");
        }
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = Objects.requireNonNull(idUsuario, "ID Usuario no puede ser nulo");
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = Objects.requireNonNull(idLibro, "ID Libro no puede ser nulo");
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = Objects.requireNonNull(fechaPrestamo, "Fecha préstamo no puede ser nula");
        
        // Validar que la fecha de préstamo no sea futura
        if (fechaPrestamo.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha préstamo no puede ser futura");
        }
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        if (fechaDevolucion != null) {
            // Validar que la fecha de devolución no sea anterior al préstamo
            if (fechaDevolucion.isBefore(fechaPrestamo)) {
                throw new IllegalArgumentException("Fecha devolución no puede ser anterior al préstamo");
            }
            this.estado = "DEVUELTO";
        }
        this.fechaDevolucion = fechaDevolucion;
    }

    public LocalDate getFechaDevolucionEstimada() {
        return fechaDevolucionEstimada;
    }

    public void setFechaDevolucionEstimada(LocalDate fechaDevolucionEstimada) {
        this.fechaDevolucionEstimada = Objects.requireNonNull(
            fechaDevolucionEstimada, "Fecha devolución estimada no puede ser nula");
        
        // Validar que la fecha estimada sea posterior al préstamo
        if (fechaPrestamo != null && !fechaDevolucionEstimada.isAfter(fechaPrestamo)) {
            throw new IllegalArgumentException("Fecha devolución estimada debe ser posterior al préstamo");
        }
        
        // Actualizar estado si está vencido
        if (fechaDevolucionEstimada.isBefore(LocalDate.now()) && !"DEVUELTO".equals(estado)) {
            this.estado = "VENCIDO";
        }
    }

    public String getEstado() {
        // Calcular estado dinámico si es necesario
        if ("ACTIVO".equals(estado) && fechaDevolucionEstimada != null 
            && fechaDevolucionEstimada.isBefore(LocalDate.now())) {
            return "VENCIDO";
        }
        return estado;
    }

    public void setEstado(String estado) {
        if (!"ACTIVO".equals(estado) && !"DEVUELTO".equals(estado) && !"VENCIDO".equals(estado)) {
            throw new IllegalArgumentException("Estado inválido. Debe ser ACTIVO, DEVUELTO o VENCIDO");
        }
        this.estado = estado;
    }

    // Métodos de negocio
    public boolean estaVencido() {
        return "VENCIDO".equals(getEstado());
    }

    public boolean estaActivo() {
        return "ACTIVO".equals(getEstado());
    }

    public boolean estaDevuelto() {
        return "DEVUELTO".equals(getEstado());
    }

    // Métodos sobreescritos
    @Override
    public String toString() {
        return "Prestamo{" +
               "idPrestamo=" + idPrestamo +
               ", idUsuario=" + idUsuario +
               ", idLibro=" + idLibro +
               ", fechaPrestamo=" + fechaPrestamo +
               ", fechaDevolucion=" + fechaDevolucion +
               ", fechaDevolucionEstimada=" + fechaDevolucionEstimada +
               ", estado='" + getEstado() + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return Objects.equals(idPrestamo, prestamo.idPrestamo) &&
               Objects.equals(idUsuario, prestamo.idUsuario) &&
               Objects.equals(idLibro, prestamo.idLibro) &&
               Objects.equals(fechaPrestamo, prestamo.fechaPrestamo) &&
               Objects.equals(fechaDevolucion, prestamo.fechaDevolucion) &&
               Objects.equals(fechaDevolucionEstimada, prestamo.fechaDevolucionEstimada) &&
               Objects.equals(getEstado(), prestamo.getEstado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrestamo, idUsuario, idLibro, fechaPrestamo, 
                           fechaDevolucion, fechaDevolucionEstimada, getEstado());
    }

    // Builder pattern para creación flexible
    public static class Builder {
        private Integer idPrestamo;
        private Integer idUsuario;
        private Integer idLibro;
        private LocalDate fechaPrestamo;
        private LocalDate fechaDevolucion;
        private LocalDate fechaDevolucionEstimada;
        private String estado;

        public Builder withIdPrestamo(Integer idPrestamo) {
            this.idPrestamo = idPrestamo;
            return this;
        }

        public Builder withIdUsuario(Integer idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public Builder withIdLibro(Integer idLibro) {
            this.idLibro = idLibro;
            return this;
        }

        public Builder withFechaPrestamo(LocalDate fechaPrestamo) {
            this.fechaPrestamo = fechaPrestamo;
            return this;
        }

        public Builder withFechaDevolucion(LocalDate fechaDevolucion) {
            this.fechaDevolucion = fechaDevolucion;
            return this;
        }

        public Builder withFechaDevolucionEstimada(LocalDate fechaDevolucionEstimada) {
            this.fechaDevolucionEstimada = fechaDevolucionEstimada;
            return this;
        }

        public Builder withEstado(String estado) {
            this.estado = estado;
            return this;
        }

        public Prestamo build() {
            Prestamo prestamo = new Prestamo();
            prestamo.setIdPrestamo(idPrestamo);
            prestamo.setIdUsuario(idUsuario);
            prestamo.setIdLibro(idLibro);
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setFechaDevolucion(fechaDevolucion);
            prestamo.setFechaDevolucionEstimada(fechaDevolucionEstimada);
            if (estado != null) {
                prestamo.setEstado(estado);
            }
            return prestamo;
        }
    }
}