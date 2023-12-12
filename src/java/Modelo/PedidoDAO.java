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
import java.util.List;

/**
 *
 * @author ASUS X
 */
public class PedidoDAO {
        public int guardarPedido(Pedido pedido) {
            int ultimoId=-1;  
            Connection con=null;
              con=Conexion.getConexion();
              String sql;
            sql="INSERT INTO pedido (id_cliente,id_empleado,fecha_hora_pedido,fecha_hora_entrega,acuenta,saldo,total,estado) VALUES (?,?,?,?,?,?,?,?)";

            try{
                    PreparedStatement stmt=con.prepareStatement(sql);
                    stmt.setInt(1,pedido.getId_cliente());
                    stmt.setInt(2,pedido.getId_empleado());
                    stmt.setDate(3, pedido.getFecha_hora_pedido());
                    stmt.setTimestamp(4, pedido.getFecha_hora_entrega());
                    stmt.setDouble(5, pedido.getAcuenta());
                    stmt.setDouble(6, pedido.getSaldo());
                    stmt.setDouble(7, pedido.getTotal());
                    stmt.setString(8, pedido.getEstado());
                    stmt.executeUpdate();
                    String sql_id = "SELECT LAST_INSERT_ID()";
                    try (PreparedStatement stmt2 = con.prepareStatement(sql_id);
                         ResultSet rs = stmt2.executeQuery()) {
                        if (rs.next()) {
                            ultimoId = rs.getInt(1);
                        }
                        }
                    }catch (SQLException e){
                        e.printStackTrace();

                    }finally {
                        // Cerrar la conexión en el bloque finally
                        if (con != null) {
                            try {
                                con.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                                // Puedes lanzar la excepción aquí o simplemente registrarla
                            }
                        }
                    }
                        return ultimoId;
        }
        
        
        
}
