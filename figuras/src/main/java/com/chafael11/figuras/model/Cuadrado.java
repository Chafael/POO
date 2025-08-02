// Archivo: Cuadrado.java
package com.chafael11.figuras.model;

public class Cuadrado extends Figura {
    private double lado;

    public Cuadrado(double lado) {
        super("Cuadrado");
        this.lado = lado;
    }

    @Override
    public double perimetro() {
        return 4 * lado;
    }

    @Override
    public double area() {
        return lado * lado;
    }

    public double getLado() { return lado; }
}
