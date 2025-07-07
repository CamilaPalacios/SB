package com.systematic.app.biblioteca.models;

public class PrestamosPorMesDTO {
    private int mes;
    private int total;

    public PrestamosPorMesDTO(int mes, int total) {
        this.mes = mes;
        this.total = total;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
