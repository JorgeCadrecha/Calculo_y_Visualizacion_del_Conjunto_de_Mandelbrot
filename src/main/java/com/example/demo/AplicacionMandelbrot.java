package com.example.demo;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AplicacionMandelbrot extends JFrame {
    private RenderizadorMandelbrot renderizador;
    private JSpinner trabajadoresSpinner;
    private JButton botonCalcular;

    public AplicacionMandelbrot() {
        setTitle("Conjunto de Mandelbrot");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        renderizador = new RenderizadorMandelbrot();
        add(renderizador, BorderLayout.CENTER);

        JPanel panelControl = new JPanel();
        trabajadoresSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 16, 1));

        //panelControl.add(new JLabel("Número de trabajadores:"));
        //panelControl.add(trabajadoresSpinner);
        /**
         * Si añado varios trabajadores, el programa se ejecuta más rápido, pero la imagen no se ve bien.
         * Por eso he decidido dejarlo con un solo trabajador, para que se vea bien.
         */
        panelControl.add(new JLabel("DEBES DARLE A ¨Calcular Mandelbrot¨ PARA VER LA IMAGEN"));

        botonCalcular = new JButton("Calcular Mandelbrot");
        botonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularMandelbrot();
            }
        });

        panelControl.add(botonCalcular);

        add(panelControl, BorderLayout.SOUTH);
    }

    private void calcularMandelbrot() {
        int numTrabajadores = (int) trabajadoresSpinner.getValue();
        ExecutorService executorService = Executors.newFixedThreadPool(numTrabajadores);

        int ancho = renderizador.getWidth();
        int alto = renderizador.getHeight();

        // Define la región del conjunto de Mandelbrot a calcular
        double xMin = -2.0;
        double xMax = 1.0;
        double yMin = -1.5;
        double yMax = 1.5;

        // Calcula Mandelbrot usando múltiples hilos
        for (int i = 0; i < numTrabajadores; i++) {
            int startY = i * (alto / numTrabajadores);
            int endY = (i + 1) * (alto / numTrabajadores);
            Runnable trabajador = new TrabajadorMandelbrot(startY, endY, ancho, alto, xMin, xMax, yMin, yMax, renderizador);
            executorService.execute(trabajador);
        }

        // Apaga el executor y espera a que se completen todas las tareas
        executorService.shutdown();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplicacionMandelbrot app = new AplicacionMandelbrot();
            app.setVisible(true);
        });
    }
}


