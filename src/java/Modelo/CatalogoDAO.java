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

/**
 *
 * @author ASUS X
 */
public class CatalogoDAO {
         public List<Catalogo> BuscarCatalogoPorIdCategoria() {
         
         List<Catalogo> lista=new ArrayList();
         Connection con=null;
         con=Conexion.getConexion();
        try (PreparedStatement stmt=con.prepareStatement("select * FROM catalogo"))
        
        {
            try (ResultSet rs = stmt.executeQuery()) {
                 while (rs.next()) {
                          Catalogo catalogo=new Catalogo();
                          catalogo.setId_producto_catalogo(rs.getInt("id_producto_catalogo"));
                          catalogo.setId_categoria(rs.getInt("id_categoria"));
                          catalogo.setPiso(rs.getString("piso"));
                          catalogo.setDiametro(rs.getString("diametro"));
                          catalogo.setKeke(rs.getString("keke"));
                          catalogo.setRelleno(rs.getString("relleno"));
                          catalogo.setPrecio_unitario(rs.getDouble("precio_unitario"));
                          catalogo.setDescripcion(rs.getString("descripcion"));
                          catalogo.setImagen(rs.getString("imagen"));
                          catalogo.setNombre(rs.getString("nombre"));
                          lista.add(catalogo);
                        
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    public Catalogo ProductoCatalogoPorId(int id) {
           Catalogo catalogo=new Catalogo();
         Connection con=null;
         con=Conexion.getConexion();
        try (PreparedStatement stmt=con.prepareStatement("select * FROM catalogo where id_producto_catalogo = ?"))
        
        {
            stmt.setInt(1,id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                          catalogo.setId_producto_catalogo(rs.getInt("id_producto_catalogo"));
                          catalogo.setId_categoria(rs.getInt("id_categoria"));
                          catalogo.setPiso(rs.getString("piso"));
                          catalogo.setDiametro(rs.getString("diametro"));
                          catalogo.setKeke(rs.getString("keke"));
                          catalogo.setRelleno(rs.getString("relleno"));
                          catalogo.setPrecio_unitario(rs.getDouble("precio_unitario"));
                          catalogo.setDescripcion(rs.getString("descripcion"));
                          catalogo.setImagen(rs.getString("imagen"));
                          catalogo.setNombre(rs.getString("nombre"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return catalogo;
    }
    
      public void agregarCatalogo(Catalogo catalogo){
            Connection con=null;
              con=Conexion.getConexion();
              String sql;
            if(catalogo!=null && catalogo.getId_producto_catalogo()>0){
                  sql="update catalogo set id_categoria=?, nombre=?, diametro=?, keke=?, relleno=?, precio_unitario=?, descripcion=?, imagen=?, piso=?  where id_producto_catalogo=?";
            }else{
                sql="INSERT INTO catalogo (id_categoria,nombre,diametro,keke,relleno,precio_unitario,descripcion,imagen,piso) VALUES (?,?,?,?,?,?,?,?,?)";
            }
              
            try (PreparedStatement stmt=con.prepareStatement(sql);){
                   
                    stmt.setInt(1,catalogo.getId_categoria());
                    stmt.setString(2,catalogo.getNombre());
                    stmt.setString(3, catalogo.getDiametro());
                    stmt.setString(4, catalogo.getKeke());
                    stmt.setString(5, catalogo.getRelleno());
                    stmt.setDouble(6, catalogo.getPrecio_unitario());
                    stmt.setString(7, catalogo.getDescripcion());
                    stmt.setString(8, catalogo.getImagen());
                    stmt.setString(9, catalogo.getPiso());
                    if(catalogo!=null && catalogo.getId_producto_catalogo()>0){
                            stmt.setInt(10, catalogo.getId_producto_catalogo());
                    }
                    stmt.executeUpdate();                   
                    }catch (SQLException e){
                        e.printStackTrace();
                }         
     }
     
       public void EliminarCatalogo(int id_producto){
        int ret=1;
        Connection con=null;
        PreparedStatement pstm=null;
        try{
           con=Conexion.getConexion();
           String sql="";
           sql="DELETE FROM catalogo WHERE id_producto_catalogo=?";
           pstm=con.prepareStatement(sql);
           pstm.setInt(1, id_producto);
           pstm.executeUpdate();
        }catch(Exception e){
            ret=-1;
            e.printStackTrace();
        }finally{
            try {
                    if(pstm!=null)pstm.close();
                    if(con!=null)con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
