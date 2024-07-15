/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model;

/**
 *
 * @author Top System
 */
public class Cronometro {

    private int horas = 0;
    private int minutos = 0;
    private int segundos = 0;

    public Cronometro() {
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public void tick() {
        segundos++;
        if (segundos == 60) {
            segundos = 0;
            minutos++;
            if (minutos == 60) {
                minutos = 0;
                horas++;
            }
        }
    }

    public String formatarTempo() {
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public void resetar() {
        horas = 0;
        minutos = 0;
        segundos = 0;
    }
}


