// Archivo: Circulo.java
package com.chafael11.figuras.model;

public class Circulo extends Figura {
    private double radio;

    public Circulo(double radio) {
        super("Círculo");
        this.radio = radio;
    }

    @Override
    public double perimetro() {
        return 2 * Math.PI * radio;
    }

    @Override
    public double area() {
        return Math.PI * radio * radio;
    }

    public double getRadio() { return radio; }
}