package com.fullgamer494.figuras.model;

// Clase abstracta Figura
public abstract class Figura {
    protected String nombre;

    public Figura(String nombre) {
        this.nombre = nombre;
    }

    public abstract double perimetro();
    public abstract double area();

    public String getNombre() {
        return nombre;
    }
}
