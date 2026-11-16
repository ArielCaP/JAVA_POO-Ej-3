package org.example;

public class ProductoDigital extends Producto {
    private double tamanioMB;

    public ProductoDigital(String codigo, String nombre, double precio, double tamanioMB) {
        super(codigo, nombre, precio);
        this.tamanioMB = tamanioMB;
    }

    public double getTamanioMB() {
        return tamanioMB;
    }

    public void setTamanioMB(double tamanioMB) {
        this.tamanioMB = tamanioMB;
    }

    @Override
    public String toString() {
        return "[Digital] "+super.toString()+" "+tamanioMB+" MB,"+getPrecio();
    }

    @Override
    public double calcularPrecioFinal() {
        return super.calcularPrecioFinal();
    }

    @Override
    public ProductoDigital crearVariante(String nuevoCodigo, double nuevoPrecio) {
        return new ProductoDigital(nuevoCodigo, getNombre(), nuevoPrecio, tamanioMB);
    }
}
