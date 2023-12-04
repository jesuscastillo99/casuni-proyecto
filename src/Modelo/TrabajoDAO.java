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

public class TrabajoDAO {
    ConexionBD conexion;

    public TrabajoDAO() {
        conexion = new ConexionBD();
    }
    
    public ArrayList<Trabajo> listarTrabajosA() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where nombre = 'ALEXIS'");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosH() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where nombre = 'HECTOR'");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2020() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2020-01-01' and '2020-12-31' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-01-01' and '2021-12-31' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021Enero() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-01-01' and '2021-01-31' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021Febrero() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-02-01' and '2021-02-28' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021Marzo() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-03-01' and '2021-03-31' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021Abril() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-04-01' and '2021-04-30' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021Mayo() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-05-01' and '2021-05-31' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021Junio() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-06-01' and '2021-06-30' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021Julio() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-07-01' and '2021-07-31' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021Agosto() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-08-01' and '2021-08-30' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021Septiembre() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-09-01' and '2021-09-30' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021Octubre() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-10-01' and '2021-10-31' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021Noviembre() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-11-01' and '2021-11-30' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    public ArrayList<Trabajo> listarTrabajosAño2021Diciembre() {
        ArrayList listaTrabajos = new ArrayList();
        Trabajo trabajo;
        try {
            Connection accesoBD = conexion.establecerConexion();
            Statement estado = accesoBD.createStatement();
            ResultSet resultado = estado.executeQuery("select * from trabajos where fecha between '2021-12-01' and '2021-12-31' ");
            //iteracion a través del resultado obtenido
            while (resultado.next()) {
                trabajo = new Trabajo();
                trabajo.setSerie(resultado.getInt("serie"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setApellido(resultado.getString("apellido"));
                trabajo.setFecha(resultado.getString("fecha"));
                
                listaTrabajos.add(trabajo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer la tabla de trabajos");
        }

        conexion.desconectar();
        return listaTrabajos;
    }
    
    
}
