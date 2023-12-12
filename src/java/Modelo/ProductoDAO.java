/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS X
 */
public class ProductoDAO {
       public boolean guardar(List<Producto> listaCarrito,int id_pedido) {
           boolean rpta=false;
           String sql;
           sql = "insert into lista_productos (id_pedido,id_categoria,piso,diametro,keke,relleno,precio_Unitario,cantidad,descripcion,imagen) values(?,?,?,?,?,?,?,?,?,?)";
       
        try{
                PreparedStatement stmt=Conexion.getConexion().prepareStatement(sql);
                for(Producto p:listaCarrito){
                stmt.setInt(1,id_pedido);
                stmt.setInt(2,p.getId_categoria());
                stmt.setString(3, p.getPisos());
                stmt.setString(4, p.getDiametros());
                stmt.setString(5, p.getKeke());
                stmt.setString(6, p.getRellenos());
                stmt.setDouble(7, p.getPrecioUnitario());
                stmt.setInt(8, p.getCantidad());
                stmt.setString(9, p.getDescripcion());
                stmt.setString(10, p.getImagen());
                stmt.executeUpdate();
                }
                rpta=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rpta;
    }
}
