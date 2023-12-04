/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author choy
 */
public class Parte {
    private String Localidad;
    private String Marca;
    private String Parte;
    private int Serie;
    private String Estatus;

    public Parte() {
        Serie=0;
        Localidad=Marca=Parte=Estatus="";
    }

    public String getLocalidad() {
        return Localidad;
    }

    public void setLocalidad(String Localidad) {
        this.Localidad = Localidad;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getParte() {
        return Parte;
    }

    public void setParte(String Parte) {
        this.Parte = Parte;
    }

    public int getSerie() {
        return Serie;
    }

    public void setSerie(int Serie) {
        this.Serie = Serie;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    
    
    
    
    
}
