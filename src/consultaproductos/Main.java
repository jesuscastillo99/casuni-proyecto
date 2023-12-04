/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultaproductos;
import Controlador.ControladorCrud;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import Modelo.ConexionBD;
import Modelo.ParteDAO;
import Modelo.Pieza2DAO;
import Modelo.Pieza3DAO;
import Modelo.PiezaDAO;
import Modelo.SolicitudDAO;
import Modelo.TrabajoDAO;
import Vista.Catalogos;
import Vista.ExportarBD;
import Vista.ImportarBD;
import Vista.Inicio;
import Vista.VistaMenu;
import Vista.VistaPartes;
import Vista.VistaSolicitudes;
import Vista.VistaTrabajos;


public class Main {

  
    public static void main(String[] args) {
      ConexionBD conexion= new ConexionBD();
      conexion.establecerConexion();
      Inicio vistaI = new Inicio();
      VistaMenu vistaM = new VistaMenu();
      VistaSolicitudes vistaS = new VistaSolicitudes();
      Catalogos vistaC = new Catalogos();
      VistaPartes vistaP = new VistaPartes();
      ExportarBD vistaex= new ExportarBD();
      ImportarBD vistaim= new ImportarBD();
      VistaTrabajos vistat= new VistaTrabajos();
      ParteDAO modeloPD= new ParteDAO();
      SolicitudDAO modeloSD= new SolicitudDAO();
      PiezaDAO modeloC= new PiezaDAO();
      Pieza2DAO modeloC2= new Pieza2DAO();
      Pieza3DAO modeloC3= new Pieza3DAO();
      TrabajoDAO modeloc4= new TrabajoDAO();
      ControladorCrud controladorC= new ControladorCrud(vistaI, vistaM, vistaP, modeloPD, vistaS, modeloSD, vistaC, modeloC, modeloC2, modeloC3, vistat, modeloc4, vistaex, vistaim,conexion);
      vistaI.setVisible(true);
      vistaI.setLocationRelativeTo(null);
      vistaI.setDefaultCloseOperation(EXIT_ON_CLOSE);
      
    }
    
}
