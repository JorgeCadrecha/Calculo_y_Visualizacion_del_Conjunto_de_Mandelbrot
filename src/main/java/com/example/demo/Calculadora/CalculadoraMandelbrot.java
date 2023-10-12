package com.example.demo.Calculadora;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class CalculadoraMandelbrot {
    public static BufferedImage calcularMandelbrot(int ancho, int alto, int maxIteraciones, double xMin, double xMax, double yMin, double yMax) {
        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        // Itera a través de cada píxel y calcula el conjunto de Mandelbrot
        for (int x = 0; x < ancho; x++) {
            for (int y = 0; y < alto; y++) {
                double zx = x * (xMax - xMin) / (ancho - 1) + xMin;
                double zy = y * (yMax - yMin) / (alto - 1) + yMin;

                int color = calcularPuntoMandelbrot(zx, zy, maxIteraciones);
                imagen.setRGB(x, y, color);
            }
        }

        return imagen;
    }

    private static int calcularPuntoMandelbrot(double zx, double zy, int maxIteraciones) {
        int iteracion = 0;
        double x = zx;
        double y = zy;

        while (x * x + y * y <= 4 && iteracion < maxIteraciones) {
            double tempX = x * x - y * y + zx;
            y = 2 * x * y + zy;
            x = tempX;
            iteracion++;
        }

        if (iteracion == maxIteraciones) {
            return Color.BLACK.getRGB();
        }

        return Color.HSBtoRGB((float) iteracion / maxIteraciones, 1, 1);
    }
}

