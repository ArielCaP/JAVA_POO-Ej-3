package org.example;

public class ProductoFisico extends Producto {
    private double peso;

    public ProductoFisico(String codigo, String nombre, double precio, double peso) {
        super(codigo, nombre, precio);
        this.peso = peso;
    }

    public ProductoFisico(String codigo, String nombre, double precio) {
        this(codigo, nombre, precio,1.0);
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "[Fisico] "+super.toString()+", "+peso;
    }

    @Override
    public double calcularPrecioFinal() {
        return (super.calcularPrecioFinal()+2.50)+(0.80*peso);
    }

    @Override
    public ProductoFisico crearVariante(String nuevoCodigo, double nuevoPrecio) {
        return new ProductoFisico(nuevoCodigo, getNombre(), nuevoPrecio, peso);
    }
}
