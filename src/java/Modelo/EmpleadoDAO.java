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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS X
 */
public class EmpleadoDAO {
      public Empleado BuscarPorId(int id) {
         Empleado empleado=new Empleado();
         Connection con=null;
         con=Conexion.getConexion();
        try (PreparedStatement stmt=con.prepareStatement("select * FROM empleado where id_empleado=?"))
        
        {
            stmt.setInt(1,id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                          
                           empleado.setId_empleado(rs.getInt("id_empleado"));
                           empleado.setNombre(rs.getString("nombre"));
                           empleado.setApellido(rs.getString("apellido"));
                           empleado.setTelefono(rs.getString("telefono"));
                           empleado.setCorreo(rs.getString("correo"));
                           empleado.setNombre_Sesion(rs.getString("nombre_Sesion"));
                           empleado.setClave(rs.getString("clave"));
                           empleado.setRol(rs.getInt("rol"));
                           empleado.setEstado(rs.getString("estado"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return empleado;
    }
      
    public Empleado loguinEmpleado(String usuario, String clave) {
         Empleado empleado=new Empleado();
         Connection con=null;
         con=Conexion.getConexion();
        try (PreparedStatement stmt=con.prepareStatement("select * FROM empleado where nombre_Sesion=? AND clave=?"))
        
        {
            stmt.setString(1,usuario);
            stmt.setString(2    ,clave);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                          
                           empleado.setId_empleado(rs.getInt("id_empleado"));
                           empleado.setNombre(rs.getString("nombre"));
                           empleado.setApellido(rs.getString("apellido"));
                           empleado.setTelefono(rs.getString("telefono"));
                           empleado.setCorreo(rs.getString("correo"));
                           empleado.setNombre_Sesion(rs.getString("nombre_Sesion"));
                           empleado.setClave(rs.getString("clave"));
                           empleado.setRol(rs.getInt("rol"));
                           empleado.setEstado(rs.getString("estado"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return empleado;
    }
    public void cambiarEstado(int id_empleado){
          Connection con=null;
          con=Conexion.getConexion();
          String sql;
          sql = "update empleado set estado=? where id_empleado=?";
         try( PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1,"inactivo");
            stmt.setInt(2, id_empleado);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
     }
    
    public List<Empleado> listar() {
     List<Empleado> empleados=new ArrayList();
      Connection con=null;
       con=Conexion.getConexion();
     try (
             PreparedStatement stmt=con.prepareStatement("select * FROM empleado where estado = ?")
             ) {
             stmt.setString(1,"activo");
             ResultSet rs = stmt.executeQuery();
             while (rs.next()){
             Empleado empleado =new Empleado();
             empleado.setId_empleado(rs.getInt("id_empleado"));
             empleado.setNombre(rs.getString("nombre"));
             empleado.setApellido(rs.getString("apellido"));
             empleado.setTelefono(rs.getString("telefono"));
             empleado.setCorreo(rs.getString("correo"));
             empleado.setNombre_Sesion(rs.getString("clave"));
             empleado.setEstado(rs.getString("estado"));
             empleados.add(empleado);
              }

     } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
     }
     return empleados;
    }
    
    public void agregarEmpleado(Empleado empleado){
            Connection con=null;
              con=Conexion.getConexion();
              String sql;
            if(empleado!=null && empleado.getId_empleado()>0){
                  sql="update empleado set nombre=?, apellido=?, telefono=?, correo=?, nombre_Sesion=?, clave=?, rol=?, estado=? where id_empleado=?";
            }else{
                sql="INSERT INTO empleado (nombre,apellido,telefono,correo,nombre_Sesion,clave,rol,estado) VALUES (?,?,?,?,?,?,?,?)";
            }
              
            try (PreparedStatement stmt=con.prepareStatement(sql);){
                   
                    stmt.setString(1,empleado.getNombre());
                    stmt.setString(2,empleado.getApellido());
                    stmt.setString(3, empleado.getTelefono());
                    stmt.setString(4, empleado.getCorreo());
                    stmt.setString(5, empleado.getNombre_Sesion());
                    stmt.setString(6, empleado.getClave());
                    stmt.setInt(7, empleado.getRol());
                    stmt.setString(8, empleado.getEstado());
                    if(empleado!=null && empleado.getId_empleado()>0){
                            stmt.setInt(9, empleado.getId_empleado());
                    }
                    stmt.executeUpdate();                   
                    }catch (SQLException e){
                        e.printStackTrace();
                }
                   
     }
    
  
}
