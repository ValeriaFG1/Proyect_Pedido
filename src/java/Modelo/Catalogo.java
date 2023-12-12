/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ASUS X
 */
public class Catalogo {
   private int id_producto_catalogo;
   private int id_categoria;
   private String piso;
   private String diametro;
   private String keke;
   private String relleno;
   private double precio_unitario;
   private String descripcion;
   private String imagen;
   private String nombre;
   private int cantidad;
    public Catalogo() {
    }

    public Catalogo(int id_producto_catalogo, int id_categoria, String piso, String diametro, String keke, String relleno, double precio_unitario, String descripcion, String imagen) {
        this.id_producto_catalogo = id_producto_catalogo;
        this.id_categoria = id_categoria;
        this.piso = piso;
        this.diametro = diametro;
        this.keke = keke;
        this.relleno = relleno;
        this.precio_unitario = precio_unitario;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId_producto_catalogo() {
        return id_producto_catalogo;
    }

    public void setId_producto_catalogo(int id_producto_catalogo) {
        this.id_producto_catalogo = id_producto_catalogo;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDiametro() {
        return diametro;
    }

    public void setDiametro(String diametro) {
        this.diametro = diametro;
    }

    public String getKeke() {
        return keke;
    }

    public void setKeke(String keke) {
        this.keke = keke;
    }

    public String getRelleno() {
        return relleno;
    }

    public void setRelleno(String relleno) {
        this.relleno = relleno;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
   
}
