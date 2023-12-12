/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Conexion.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS X
 */
public class ReporteDAO {
    public List<ReporteFecha> guardar(Date fechaIncio,Date fechaFin) {
           boolean rpta=false;
           String sql;
           List<ReporteFecha> lista_reporte=new ArrayList();
           sql = "select fecha_hora_pedido, sum(total) AS total_ventas from pedido where fecha_hora_pedido BETWEEN ? AND ? GROUP BY fecha_hora_pedido ORDER BY fecha_hora_pedido";
       
        try{
                PreparedStatement stmt=Conexion.getConexion().prepareStatement(sql);
                stmt.setDate(1,fechaIncio);
                stmt.setDate(2,fechaFin);
                 ResultSet rs = stmt.executeQuery();
                  while (rs.next()){
                    ReporteFecha reporteFecha =new ReporteFecha();
                    reporteFecha.setFechaPedido(rs.getDate(1));
                    reporteFecha.setTotal_ventas(rs.getDouble(2));
                    reporteFecha.setFechaInicio(fechaIncio);
                    reporteFecha.setFechaFin(fechaFin);
                    lista_reporte.add(reporteFecha);
                 }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista_reporte;
    }

}
