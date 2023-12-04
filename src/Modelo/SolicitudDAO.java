/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jesus
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SolicitudDAO {
    ConexionBD conexion;

    public SolicitudDAO() {
        conexion = new ConexionBD();
    }
    
    public void editarsolicitud(String Estatus,  int claveeditar) {
        try {
            Connection accesoDB = conexion.establecerConexion();
            String query = "update solicitudes set estatus = ? where codigo = ?";
            PreparedStatement preparedStmt = accesoDB.prepareStatement(query);
            preparedStmt.setString(1, Estatus);
            preparedStmt.setInt(2, claveeditar);

            preparedStmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al editar"+e);
        }
      
    }
    
    public ArrayList<Solicitud> listarSolicitudes() {
        ArrayList listaSolicitudes = new ArrayList();
        Solicitud solicitud;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from solicitudes");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                solicitud = new Solicitud();
                solicitud.setCodigo(resultado.getInt("codigo"));
                solicitud.setNombre(resultado.getString("nombre"));
                solicitud.setCantidad(resultado.getInt("cantidad"));
                solicitud.setEstatus(resultado.getString("estatus"));
                
                listaSolicitudes.add(solicitud);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de solicitudes");
        }

        conexion.desconectar();
        return listaSolicitudes;
    }
    
}
