package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Tienda {
    private HashMap<String,Producto> listaCatalogo=new HashMap<>();
    private ArrayList<Producto> listaCarrito =new ArrayList<>();
    private HashSet<String> listaCuponesValidos=new HashSet<>();
    private HashSet<String> listaCuponesUsados=new HashSet<>();

    public HashMap<String, Producto> getListaCatalogo() {
        return listaCatalogo;
    }
    public ArrayList<Producto> setListaCarrito() {
        return listaCarrito;
    }

    public Tienda() {
        listaCuponesValidos.add("ESFOT10");
        listaCuponesValidos.add("POO10");
    }

    public Producto agregarCarrito(String codigo, int cantidad){
       Producto p=listaCatalogo.get(codigo);
       if(p==null){
           return null;
       }
       for (int i=0;i<cantidad;i++){
           listaCarrito.add(p);
       }
       return p;
    }
    public Producto agregarCarrito(String codigo){
        return agregarCarrito(codigo,1);
    }
    public double calcularTotal(){
        double total=0;
        for (Producto producto:listaCarrito){
            total+= producto.calcularPrecioFinal();
        }
        return total;
    }

    public void pagar() {
        if (listaCarrito.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        double total = 0;
        for (Producto p : listaCarrito) {
            total += p.calcularPrecioFinal();
        }
        System.out.println("Total a pagar: $" + String.format("%.2f", total));
        listaCarrito.clear();
        System.out.println("Gracias por su compra. El carrito quedó vacío.");
    }

    public void pagar(String cupon){
        if (listaCarrito.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        if (!listaCuponesValidos.contains(cupon)) {
            System.out.println("El cupón " + cupon + " no es válido.");
            return;
        }
        if (!listaCuponesUsados.add(cupon)) {
            System.out.println("El cupón " + cupon + " ya fue utilizado.");
            return;
        }
        double total = 0;
        for (Producto p : listaCarrito) {
            total += p.calcularPrecioFinal();
        }
        System.out.println("Descuento aplicado: 10 %");
        total = total - (total * 10 / 100);
        System.out.println("Total a pagar: $" + String.format("%.2f", total));
        listaCarrito.clear();
        System.out.println("Gracias por su compra. El carrito quedó vacío.");
    }}

