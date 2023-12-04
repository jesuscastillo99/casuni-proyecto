/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Jesus
 */
public class Pieza3DAO {
    ConexionBD conexion;

    public Pieza3DAO() {
        conexion = new ConexionBD();
    }
    
    public ArrayList<Pieza> listarPiezas() {
        ArrayList listaPiezas = new ArrayList();
        Pieza pieza;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from piezas3");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                pieza = new Pieza();
                
                pieza.setCodigo(resultado.getString("codigo"));
                pieza.setDescripcion(resultado.getString("descripcion"));
                pieza.setMarca(resultado.getString("marca"));
                
                listaPiezas.add(pieza);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de piezas ccdm");
        }

        conexion.desconectar();
        return listaPiezas;
    }
}
