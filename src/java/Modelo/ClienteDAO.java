/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS X
 */
public class ClienteDAO {
    
    
      
     
    public Cliente BuscarClientePorDNI(String dni) {
        Cliente cliente=new Cliente();
       Connection con=null;
         con=Conexion.getConexion();
        try (PreparedStatement stmt=con.prepareStatement("select * from cliente where DNI = ?")){
           
            stmt.setString(1,dni);
            try (ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    cliente.setId_cliente(rs.getInt("id_cliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setDNI(rs.getString("DNI")); 
                    cliente.setEstado(rs.getString("estado"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }
     public List<Cliente> listar() {
        List<Cliente> clientes=new ArrayList();
         Connection con=null;
          con=Conexion.getConexion();
        try (
                PreparedStatement stmt=con.prepareStatement("select * FROM cliente where estado = ?")
                ) {
                stmt.setString(1,"activo");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente =new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setDNI(rs.getString("DNI"));
                cliente.setEstado(rs.getString("estado"));
                clientes.add(cliente);
                 }

        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e);
        }
        return clientes;
    }
     public void cambiarEstado(int id_cliente){
          Connection con=null;
          con=Conexion.getConexion();
          String sql;
          sql = "update cliente set estado=? where id_cliente=?";
         try( PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1,"inactivo");
            stmt.setInt(id_cliente, id_cliente);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
     }
     public void agregarCliente(Cliente cliente){
            Connection con=null;
              con=Conexion.getConexion();
              String sql;
            if(cliente!=null && cliente.getId_cliente()>0){
                  sql="update cliente set nombre=?, apellido=?, telefono=?, correo=?, DNI=?, estado=? where id_cliente=?";
            }else{
                sql="INSERT INTO cliente (nombre,apellido,telefono,correo,DNI,estado) VALUES (?,?,?,?,?,?)";
            }
              
            try (PreparedStatement stmt=con.prepareStatement(sql);){
                   
                    stmt.setString(1,cliente.getNombre());
                    stmt.setString(2,cliente.getApellido());
                    stmt.setString(3, cliente.getTelefono());
                    stmt.setString(4, cliente.getCorreo());
                    stmt.setString(5, cliente.getDNI());
                    stmt.setString(6, "activo");
                    if(cliente!=null && cliente.getId_cliente()>0){
                            stmt.setInt(7, cliente.getId_cliente());
                    }
                    stmt.executeUpdate();                   
                    }catch (SQLException e){
                        e.printStackTrace();
                }
                   
     }
     public Cliente BuscarPorId(int id) {
         Cliente cliente=new Cliente();
         Connection con=null;
         con=Conexion.getConexion();
        try (PreparedStatement stmt=con.prepareStatement("select id_cliente,nombre,apellido,telefono,correo,DNI,estado FROM cliente where id_cliente = ?"))
        
        {
            stmt.setInt(1,id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                          
                           cliente.setId_cliente(rs.getInt("id_cliente"));
                           cliente.setNombre(rs.getString("nombre"));
                           cliente.setApellido(rs.getString("apellido"));
                           cliente.setTelefono(rs.getString("telefono"));
                           cliente.setCorreo(rs.getString("correo"));
                           cliente.setDNI(rs.getString("DNI"));
                           cliente.setEstado(rs.getString("estado"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }
    public Cliente ObtenerCliente() {
         Cliente cliente=new Cliente();
         Connection con=null;
         con=Conexion.getConexion();
        try (PreparedStatement stmt=con.prepareStatement("select * FROM cliente Order by id_cliente desc limit 1"))
        {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                          
                           cliente.setId_cliente(rs.getInt("id_cliente"));
                           cliente.setNombre(rs.getString("nombre"));
                           cliente.setApellido(rs.getString("apellido"));
                           cliente.setTelefono(rs.getString("telefono"));
                           cliente.setCorreo(rs.getString("correo"));
                           cliente.setDNI(rs.getString("DNI"));
                           cliente.setEstado(rs.getString("estado"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }
}
