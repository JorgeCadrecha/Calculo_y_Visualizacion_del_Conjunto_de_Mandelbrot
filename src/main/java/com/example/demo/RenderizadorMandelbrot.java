package com.example.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderizadorMandelbrot extends JPanel {
    private BufferedImage imagenMandelbrot;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenMandelbrot != null) {
            g.drawImage(imagenMandelbrot, 0, 0, this);
        }
    }

    public void setImagenMandelbrot(BufferedImage imagen) {
        imagenMandelbrot = imagen;
        repaint();
    }
}
