/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author choy
 */
public class ParteDAO {

    ConexionBD conexion;

    public ParteDAO() {
        conexion = new ConexionBD();
    }

    public void editarparte(int Serie, String Marca, String Parte,  String Localidad,  int claveeditar) {
        try {
            Connection accesoDB = conexion.establecerConexion();
            String query = "update PARTES set serie = ?, marca = ?, parte = ?, localidad = ? where serie = ?";
            PreparedStatement preparedStmt = accesoDB.prepareStatement(query);
            preparedStmt.setInt(1, Serie);
            preparedStmt.setString(2, Marca);
            preparedStmt.setString(3, Parte);
            preparedStmt.setString(4, Localidad);
//            preparedStmt.setString(5, Estatus);
            preparedStmt.setInt(5, claveeditar);

            preparedStmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al editar"+e);
        }
      
    }

    public void eliminarParte(int Serie) {
        try {
            Connection accesoDB = conexion.establecerConexion();
            String query = "delete from PARTES where serie = ?";
            PreparedStatement prepared = accesoDB.prepareStatement(query);
            prepared.setInt(1, Serie);
            prepared.execute();

        } catch (Exception e) {
            System.out.println("Error al eliminar parte\n" + e);
        }
    }

    public String insertarParte(int Serie, String Marca, String Parte, String Localidad, String Estatus) {
        try {
            Connection accesoDB = conexion.establecerConexion();
            String query = "INSERT INTO PARTES VALUES(?,?,?,?,?)";
            PreparedStatement prepared = accesoDB.prepareStatement(query);
            prepared.setInt(1, Serie);
            prepared.setString(2, Marca);
            prepared.setString(3, Parte);
            prepared.setString(4, Localidad);
            prepared.setString(5, Estatus);
            

            prepared.execute();

            return "Registro insertado correctamente";
        } catch (Exception e) {
            return "Error al insertar registro\n" + e;
        }

    }

    public ArrayList<Parte> listarPartes() {
        ArrayList listaPartes = new ArrayList();
        Parte parte;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from PARTES");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                parte = new Parte();
                parte.setSerie(resultado.getInt("serie"));
                parte.setMarca(resultado.getString("marca"));
                parte.setParte(resultado.getString("parte"));
                parte.setLocalidad(resultado.getString("localidad"));
                parte.setEstatus(resultado.getString("estatus"));
                
                listaPartes.add(parte);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de partes");
        }

        conexion.desconectar();
        return listaPartes;
    }
}
