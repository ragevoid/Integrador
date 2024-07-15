/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;

import com.mycompany.integrador.model.Cronometro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Top System
 */
public class CronometroService {

    private Cronometro cronometro;
    private Timer timer;
    private CronometroListener listener;

    public interface CronometroListener {

        void onTempoAtualizado(String tempoFormatado);
    }

    public CronometroService(CronometroListener listener) {
        this.listener = listener;
        this.cronometro = new Cronometro();
    }

    public void iniciar() {
        if (timer == null || !timer.isRunning()) {
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cronometro.tick();
                    if (listener != null) {
                        listener.onTempoAtualizado(cronometro.formatarTempo());
                    }
                }
            });
            timer.start();
        }
    }

    public void parar() {
        if (timer != null) {
            timer.stop();
        }
    }

    public void resetar() {
        parar();
        cronometro.resetar();
        if (listener != null) {
            listener.onTempoAtualizado(cronometro.formatarTempo());
        }
    }

}
