/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ASUS X
 */
public class Empleado {
    private int id_empleado;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String nombre_Sesion;
    private String clave;
    private int rol;
    private String estado;
    public Empleado() {
    }

    public Empleado(int id_empleado, String nombre, String apellido, String telefono, String correo, String nombre_Sesion, String clave, int rol) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.nombre_Sesion = nombre_Sesion;
        this.clave = clave;
        this.rol = rol;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre_Sesion() {
        return nombre_Sesion;
    }

    public void setNombre_Sesion(String nombre_Sesion) {
        this.nombre_Sesion = nombre_Sesion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave =clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

 
    
}
