/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.sql.Date;

/**
 *
 * @author ANDERSON
 */
public class Reserva {
    private int idReserva;
    private int idHabitacion;
    private String cliente;
    private Date fecha_ingreso;
    private Date fecha_salida;
    private Double costo_reserva;
    private String estado;

    public Reserva() {
    }

    public Reserva(int idReserva, int idHabitacion, String cliente, Date fecha_ingreso, Date fecha_salida, Double costo_reserva, String estado) {
        this.idReserva = idReserva;
        this.idHabitacion = idHabitacion;
        this.cliente = cliente;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_salida = fecha_salida;
        this.costo_reserva = costo_reserva;
        this.estado = estado;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Double getCosto_reserva() {
        return costo_reserva;
    }

    public void setCosto_reserva(Double costo_reserva) {
        this.costo_reserva = costo_reserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
