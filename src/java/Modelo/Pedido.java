/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS X
 */
public class Pedido {
    private int id_pedido;
    private int id_cliente;
    private int id_empleado; 
    private Date fecha_hora_pedido;
    private Timestamp fecha_hora_entrega;
    private double acuenta;
    private double saldo;
    private String estado;
    private double total;
    private List<Producto> productos=new ArrayList();

    public Pedido() {
    }

    public Pedido(int id_pedido, int id_cliente, int id_empleado, Date fecha_hora_pedido, Timestamp fecha_hora_entrega, double acuenta, double saldo, String estado, List<Producto> productos,double total) {
        this.id_pedido = id_pedido;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.fecha_hora_pedido = fecha_hora_pedido;
        this.fecha_hora_entrega = fecha_hora_entrega;
        this.acuenta = acuenta;
        this.saldo = saldo;
        this.estado = estado;
        this.productos = productos;
        this.total=total;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Date getFecha_hora_pedido() {
        return fecha_hora_pedido;
    }

    public void setFecha_hora_pedido(Date fecha_hora_pedido) {
        this.fecha_hora_pedido = fecha_hora_pedido;
    }

    public Timestamp getFecha_hora_entrega() {
        return fecha_hora_entrega;
    }

    public void setFecha_hora_entrega(Timestamp fecha_hora_entrega) {
        this.fecha_hora_entrega = fecha_hora_entrega;
    }

    public double getAcuenta() {
        return acuenta;
    }

    public void setAcuenta(double acuenta) {
        this.acuenta = acuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
   public void agregarProductoLista(Producto producto){
       productos.add(producto);
   }
   public void eliminarProductoLista(int id_producto){
        productos.remove(id_pedido);
   }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void copiarArchivoServidor(String path){
                // Rutas de las carpetas de origen y destino
        String carpetaOrigen = "D:"+ File.separator +"Cursos"+File.separator +"Netbeans"+ File.separator +"AdministradorPedidos"+ File.separator +"web"+ File.separator +"imgCatalogo";
        String carpetaDestino = "D:"+ File.separator +"Cursos"+File.separator +"Netbeans"+ File.separator +"AdministradorPedidos"+ File.separator +"web"+ File.separator +"files";

        // Nombre del archivo a copiar
        String nombreArchivo = path;

        // Crear el objeto File para el archivo de origen
        File archivoOrigen = new File(carpetaOrigen, nombreArchivo);

        // Crear el objeto File para el archivo de destino
        File archivoDestino = new File(carpetaDestino, nombreArchivo);

        try( FileInputStream fis = new FileInputStream(archivoOrigen);
            FileOutputStream fos = new FileOutputStream(archivoDestino);){
            // Buffer para la copia
            byte[] buffer = new byte[1024];
            int length;

            // Leer del archivo de origen y escribir en el archivo de destino
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            System.out.println("Archivo copiado correctamente.");

        }   catch (FileNotFoundException e) {
                e.printStackTrace();
                // Manejo específico para archivo no encontrado
            } catch (IOException e) {
                e.printStackTrace();
                // Manejo específico para otras excepciones de E/S
            }
        
            catch(Exception e){
                e.printStackTrace();
            }
         }
    
}
