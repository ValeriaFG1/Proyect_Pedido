/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ASUS X
 */
public class Producto {
    private int idProducto;
    private int id_categoria;
    private int id_pedido;
    private String pisos;
    private String diametros;
    private String keke;
     private String rellenos;
    private double precioUnitario;
    private int cantidad;
    private String descripcion;
    private String imagen;

    public Producto() {
    }

    public Producto(int idProducto, int id_categoria, String pisos, String diametros, String keke, String rellenos, double precioUnitario, int cantidad, String descripcion, String imagen) {
        this.idProducto = idProducto;
        this.id_categoria = id_categoria;
        this.pisos = pisos;
        this.diametros = diametros;
        this.keke = keke;
        this.rellenos = rellenos;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getPisos() {
        return pisos;
    }

    public void setPisos(String pisos) {
        this.pisos = pisos;
    }

    public String getDiametros() {
        return diametros;
    }

    public void setDiametros(String diametros) {
        this.diametros = diametros;
    }

    public String getKeke() {
        return keke;
    }

    public void setKeke(String keke) {
        this.keke = keke;
    }

    public String getRellenos() {
        return rellenos;
    }

    public void setRellenos(String rellenos) {
        this.rellenos = rellenos;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
    
   
   

}
