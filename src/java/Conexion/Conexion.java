/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS X
 */
public class Conexion {
       public static Connection getConexion() {
        Connection conexion=null;
        
        String servidor="localhost:3307";
        String database="bd_gestionpedido";
        String usuario="root";
        String clave="12345";
        String url="";
        try{
           Class.forName("com.mysql.jdbc.Driver");
            url="jdbc:mysql://"+servidor+"/"+database;
            conexion=DriverManager.getConnection(url,usuario,clave);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch(Exception b){
           JOptionPane.showMessageDialog(null, b.getMessage());
        }
        return conexion;
    }
}
