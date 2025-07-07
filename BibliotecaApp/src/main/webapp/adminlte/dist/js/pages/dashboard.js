$(function () {
  'use strict';

  // 1. Animación para las tarjetas de estadísticas
  $('.small-box').hover(
    function() {
      $(this).css('transform', 'translateY(-5px)');
      $(this).css('box-shadow', '0 10px 20px rgba(0,0,0,.1)');
    },
    function() {
      $(this).css('transform', 'translateY(0)');
      $(this).css('box-shadow', 'none');
    }
  );

  // 2. Actualización periódica de estadísticas (cada 2 minutos)
  function actualizarEstadisticas() {
    $.ajax({
      url: '${pageContext.request.contextPath}/dashboard/stats',
      type: 'GET',
      dataType: 'json',
      success: function(data) {
        actualizarTarjetas(data);
        actualizarGraficos(data);
      },
      error: function(xhr, status, error) {
        console.error('Error al actualizar estadísticas:', error);
      }
    });
  }

  // Actualizar los valores de las tarjetas
  function actualizarTarjetas(estadisticas) {
    $('.small-box h3').eq(0).text(estadisticas.totalLibros);
    $('.small-box h3').eq(1).text(estadisticas.totalUsuarios);
    $('.small-box h3').eq(2).text(estadisticas.totalPrestamosActivos);
    $('.small-box h3').eq(3).text(estadisticas.totalCategorias);
    $('.small-box h3').eq(4).text(estadisticas.totalEditoriales);
    $('.small-box h3').eq(5).text(estadisticas.totalAutores);
    $('.small-box h3').eq(6).text(estadisticas.totalCargos);
  }

  // 3. Gráfico de préstamos por mes
  function initPrestamosChart() {
    var ctx = document.getElementById('prestamosChart').getContext('2d');
    window.prestamosChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
        datasets: [{
          label: 'Préstamos por mes',
          backgroundColor: 'rgba(60, 141, 188, 0.7)',
          borderColor: 'rgba(60, 141, 188, 1)',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            beginAtZero: true,
            ticks: {
              stepSize: 1
            }
          }
        }
      }
    });
  }

  // 4. Gráfico de libros más prestados
  function initLibrosPopularesChart() {
    var ctx = document.getElementById('librosPopularesChart').getContext('2d');
    window.librosPopularesChart = new Chart(ctx, {
      type: 'doughnut',
      data: {
        labels: [],
        datasets: [{
          data: [],
          backgroundColor: [
            '#f56954', '#00a65a', '#f39c12', 
            '#00c0ef', '#3c8dbc', '#d2d6de',
            '#ff851b', '#605ca8', '#d81b60',
            '#001f3f'
          ]
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          legend: {
            position: 'right'
          }
        }
      }
    });
  }

  // Actualizar gráficos con datos del servidor
  function actualizarGraficos(data) {
    if (data.prestamosPorMes) {
      window.prestamosChart.data.datasets[0].data = data.prestamosPorMes;
      window.prestamosChart.update();
    }
    
    if (data.librosPopulares) {
      window.librosPopularesChart.data.labels = data.librosPopulares.map(libro => libro.titulo);
      window.librosPopularesChart.data.datasets[0].data = data.librosPopulares.map(libro => libro.totalPrestamos);
      window.librosPopularesChart.update();
    }
  }

  // 5. Inicialización de componentes
  $(document).ready(function() {
    // Inicializar gráficos
    initPrestamosChart();
    initLibrosPopularesChart();
    
    // Cargar datos iniciales
    actualizarEstadisticas();
    
    // Configurar actualización periódica
    setInterval(actualizarEstadisticas, 120000); // 2 minutos
    
    // 6. Tooltips para las tarjetas
    $('[data-toggle="tooltip"]').tooltip();
    
    // 7. Notificación de préstamos próximos a vencer
    $.get('${pageContext.request.contextPath}/dashboard/prestamos-proximos-vencer', function(data) {
      if (data.length > 0) {
        var notificacion = $('<div class="alert alert-warning alert-dismissible">')
          .html('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' +
                '<h5><i class="icon fas fa-exclamation-triangle"></i> Atención!</h5>' +
                'Tienes ' + data.length + ' préstamos próximos a vencer.');
        
        $('#notifications-container').append(notificacion);
      }
    });
  });

  // 8. Manejo de eventos personalizados
  $(document).on('click', '.refresh-stats', function() {
    var btn = $(this);
    btn.prop('disabled', true).html('<i class="fas fa-sync-alt fa-spin"></i> Actualizando...');
    
    actualizarEstadisticas();
    
    setTimeout(function() {
      btn.prop('disabled', false).html('<i class="fas fa-sync-alt"></i> Actualizar');
    }, 2000);
  });
});