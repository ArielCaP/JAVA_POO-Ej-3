package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tienda tienda = new Tienda();
        int opcion;
        do{

            System.out.println("--- MENÚ SHOPESFOT ---");
            System.out.println("1. Registrar producto físico");
            System.out.println("2. Registrar producto digital");
            System.out.println("3. Crear variante de producto");
            System.out.println("4. Mostrar catálogo");
            System.out.println("5. Agregar al carrito");
            System.out.println("6. Pagar");
            System.out.println("7. Salir");
            System.out.println("Ingrese opción:");
            opcion=sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:{
                    System.out.print("Codigo: ");
                    String codigo = sc.nextLine();
                    if (tienda.getListaCatalogo().containsKey(codigo)){
                        System.out.println("El producto ya existe");
                    }else{
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine().trim();
                        System.out.print("Precio: ");
                        double precio = Double.parseDouble(sc.nextLine());
                        if(precio<0){
                            System.out.println("El precio no puede ser negativo");
                        }else {
                            System.out.println("Desea indicar el peso? (s/n): ");
                            boolean respuesta= sc.nextLine().equalsIgnoreCase("s");
                            if (respuesta) {
                                System.out.print("Peso (kg): ");
                                double peso = Double.parseDouble(sc.nextLine());
                                if (peso<0){
                                    System.out.println("El peso no puede ser negativo");
                                }else{
                                    tienda.getListaCatalogo().put(codigo,new ProductoFisico(codigo,nombre,precio, peso));
                                    System.out.println("Producto fisico registrado exitosamente");
                                }
                            }else {
                                tienda.getListaCatalogo().put(codigo, new ProductoFisico(codigo, nombre, precio));
                                System.out.println("Producto fisico registrado exitosamente");
                            }
                        }
                    }
                    break;}
                case 2:{
                    System.out.print("Codigo: ");
                    String codigo = sc.nextLine();
                    if(tienda.getListaCatalogo().containsKey(codigo)) {
                        System.out.println("El producto ya existe");
                    }else{
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine().trim();
                        System.out.print("Precio: ");
                        double precio=Double.parseDouble(sc.nextLine());
                        if(precio<0){
                            System.out.println("El precio no puede ser negativo");
                        }else{
                            System.out.println("Tamaño (MB): ");
                            double tamanio=Double.parseDouble(sc.nextLine());
                            if(tamanio<0){
                                System.out.println("El tamaño no puede ser negativo");
                            }else{
                                tienda.getListaCatalogo().put(codigo,new ProductoDigital(codigo,nombre,precio,tamanio));
                                System.out.println("Producto digital registrado exitosamente");
                            }
                        }
                    }
                    break;}
                case 3:{
                    System.out.print("Codigo del producto original: ");
                    String codigo = sc.nextLine();
                    Producto p = tienda.getListaCatalogo().get(codigo);
                    if(p==null){
                        System.out.println("Producto no registrado");
                    }else{
                        System.out.print("Nuevo codigo: ");
                        String nuevoCodigo = sc.nextLine();
                        if(tienda.getListaCatalogo().containsKey(nuevoCodigo)){
                            System.out.println("El codigo "+nuevoCodigo+" ya esta registrado");
                        }else{
                            System.out.print("Nuevo precio: ");
                            double nuevoPrecio = Double.parseDouble(sc.nextLine());
                            if (nuevoPrecio<0){
                                System.out.println("El nuevo precio no puede ser negativo");
                            }else {
                                Producto variante=p.crearVariante(nuevoCodigo,nuevoPrecio);
                                tienda.getListaCatalogo().put(nuevoCodigo,variante);
                                System.out.println("Variante "+nuevoCodigo+" creada a partir de "+codigo+".");
                            }
                        }
                    }
                    break;}
                case 4:{
                    if(tienda.getListaCatalogo().isEmpty()){
                        System.out.println("No hay prodctos en este catalogo");
                    }else{
                        for (Producto p: tienda.getListaCatalogo().values()){
                            System.out.println(p);
                        }
                    }
                    break;}
                case 5:{
                    System.out.print("Codigo: ");
                    String codigo = sc.nextLine().trim();
                    System.out.print("Cuantas unidades? (Enter para 1): ");
                    String respuesta=sc.nextLine().trim();
                    if(respuesta.isEmpty()){
                        Producto p = tienda.agregarCarrito(codigo);
                        if(p==null){
                            System.out.println("El codigo "+codigo+" no existe");
                        }else{
                            System.out.println("Se agrego 1 unidad de: "+p.getNombre()+".");
                        }
                    }else{
                        int cantidad=Integer.parseInt(respuesta);
                        if(cantidad<=0){
                            System.out.println("La cantidad deber sr mayor a 0");
                        }else{
                            Producto p=tienda.agregarCarrito(codigo,cantidad);
                            if(p==null){
                                System.out.println("El codigo "+codigo+" no existe");
                            }else{
                                System.out.println("Se agregaron "+cantidad+" unidad de: "+p.getNombre()+".");
                            }
                        }
                    }
                    break;}
                case 6:{
                    System.out.print("Tiene cupon? (s/n): ");
                    boolean tieneCupon = sc.nextLine().equalsIgnoreCase("s");
                    if (tieneCupon) {
                        System.out.print("Cupon: ");
                        String cupon = sc.nextLine().trim();
                        tienda.pagar(cupon);
                    } else {
                        tienda.pagar();
                    }
                    break;}
                case 7:
                    System.out.println("Saliendo del sistema");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while(opcion!=7);
        sc.close();
    }
}
