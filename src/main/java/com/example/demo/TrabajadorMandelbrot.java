package com.example.demo;

import java.awt.image.BufferedImage;

public class TrabajadorMandelbrot implements Runnable {
    private int startY;
    private int endY;
    private int ancho;
    private int alto;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private RenderizadorMandelbrot renderizador;

    public TrabajadorMandelbrot(int startY, int endY, int ancho, int alto, double xMin, double xMax, double yMin, double yMax, RenderizadorMandelbrot renderizador) {
        this.startY = startY;
        this.endY = endY;
        this.ancho = ancho;
        this.alto = alto;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.renderizador = renderizador;
    }

    @Override
    public void run() {
        BufferedImage imagen = CalculadoraMandelbrot.calcularMandelbrot(ancho, endY - startY, 1000, xMin, xMax, yMin + startY * (yMax - yMin) / alto, yMin + endY * (yMax - yMin) / alto);
        renderizador.setImagenMandelbrot(imagen);
    }
}
