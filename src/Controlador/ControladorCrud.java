
package Controlador;

import Modelo.ConexionBD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Modelo.Parte;
import Modelo.ParteDAO;
import Modelo.Solicitud;
import Modelo.SolicitudDAO;
import Modelo.Pieza;
import Modelo.Pieza2DAO;
import Modelo.Pieza3DAO;
import Modelo.TrabajoDAO;
import Modelo.Trabajo;
import Modelo.PiezaDAO;
import Vista.Inicio;
import Vista.VistaMenu;
import Vista.VistaPartes;
import Vista.VistaSolicitudes;
import Vista.Catalogos;
import Vista.ExportarBD;
import Vista.ImportarBD;
import Vista.VistaTrabajos;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;


/**
 *
 * @author choy
 */
public class ControladorCrud implements ActionListener {
    private Inicio vistaInicio;
    private VistaMenu vistamenu;
    private VistaSolicitudes vistasoli;
    private VistaPartes vistapartes;
    private Catalogos vistacatalogos;
    private VistaTrabajos vistat;
    private ExportarBD vistaex;
    private ImportarBD vistaim;
    private ParteDAO modeloP;
    private SolicitudDAO modeloS;
    private PiezaDAO modeloC;
    private Pieza2DAO modeloC2;
    private Pieza3DAO modeloC3;
    private TrabajoDAO modeloc4;
    private ConexionBD conexion;
    
    DefaultTableModel modelT;
    int claveeditar=0;

    public ControladorCrud(Inicio vistaInicio, VistaMenu vistamenu, VistaPartes vistapartes, ParteDAO modeloP, VistaSolicitudes vistasoli, SolicitudDAO modeloS, Catalogos vistacatalogos, PiezaDAO modeloC, Pieza2DAO modeloC2, Pieza3DAO modeloC3, VistaTrabajos vistat, TrabajoDAO modeloc4, ExportarBD vistaex, ImportarBD vistaim, ConexionBD conexion) {
        this.vistaInicio = vistaInicio;
        this.vistamenu= vistamenu;
        this.vistapartes=vistapartes;
        this.vistasoli= vistasoli;
        this.vistacatalogos=vistacatalogos;
        this.vistat=vistat;
        this.vistaex=vistaex;
        this.vistaim=vistaim;
        this.modeloP=modeloP;
        this.modeloS=modeloS;
        this.modeloC=modeloC;
        this.modeloC2=modeloC2;
        this.modeloC3=modeloC3;
        this.modeloc4=modeloc4;
        this.conexion=conexion;
        vistaInicio.ingresar_btn.addActionListener(this);
        vistamenu.registrop_btn.addActionListener(this);
        vistamenu.solicitudp_btn.addActionListener(this);
        vistamenu.regres_btn.addActionListener(this);
        vistamenu.btn_cata.addActionListener(this);
        vistamenu.btntraba.addActionListener(this);
        vistamenu.btn_resp.addActionListener(this);
        vistapartes.regresarp_btn.addActionListener(this);
        vistapartes.btnListar.addActionListener(this);
        vistapartes.btnIngresar.addActionListener(this);
        vistapartes.btnEliminar.addActionListener(this);
        vistapartes.btnEditar.addActionListener(this);
        vistapartes.btnactualizar.addActionListener(this);
        vistasoli.regresarvs_btn.addActionListener(this);
        vistasoli.buscars_btn.addActionListener(this);
        vistasoli.actualizare_btn.addActionListener(this);
        vistasoli.cambiare_btn.addActionListener(this);
        vistasoli.textfieldestatus.addActionListener(this);
        vistacatalogos.btn_ccdm.addActionListener(this);
        vistacatalogos.btn_diebold.addActionListener(this);
        vistacatalogos.btn_ncr.addActionListener(this);
        vistacatalogos.btn_rgre.addActionListener(this);
        vistat.btn_tra.addActionListener(this);
        vistat.btn_trh.addActionListener(this);
        vistat.btnregresart.addActionListener(this);
        vistat.btn_tra2020.addActionListener(this);
        vistat.btn2021t.addActionListener(this);
        vistat.combobox2020.addActionListener(this);
        vistaex.btn_respald.addActionListener(this);
        vistaex.btn_selecc.addActionListener(this);
        vistaex.btn_cancel.addActionListener(this);
        vistaex.btn_regrere.addActionListener(this);
        llenarTabla(vistapartes.jTable1);
        llenarTablaSoli(vistasoli.tablasolicitudes);
//        llenarTablaPiezas(vistacatalogos.tablapiezas);
    }
    
    public void validarcampo1(){
Pattern pat1 = Pattern.compile("jesusalberto990@gmail.com");
Matcher mat1 = pat1.matcher(vistaInicio.jtxt1.getText());
Pattern pat = Pattern.compile("casuni9910");
Matcher mat = pat.matcher(vistaInicio.jtxt2.getText());

     if (mat1.find()&&mat.find()) {
        vistaInicio.setVisible(false);
        vistamenu.setVisible(true);
     } else {
           JOptionPane.showMessageDialog(null, "Ingrese usuario o contraseña correcta","Error De Validacion",JOptionPane.ERROR_MESSAGE);                                                                             
     }
 
}
    public void limpiarelementossoli(){
        vistasoli.textfieldestatus.setText("");
        vistasoli.textfieldestatus.setEditable(true);
        
    }
    
    public void limpiarelementos(){
        vistapartes.txtserie.setText("");
        vistapartes.txtserie.setEditable(true);
        vistapartes.txtmarca.setText("");
        vistapartes.txtmarca.setEditable(true);
        vistapartes.txtparte.setText("");
        vistapartes.txtparte.setEditable(true);
        vistapartes.txtlocalidad.setText("");
        vistapartes.txtlocalidad.setEditable(true);
    }
    
    public void llenarTablaSoli(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("NO. DE PEDIDO");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("CANTIDAD");
        modelT.addColumn("ESTATUS");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Solicitud> listaSolicitudes = modeloS.listarSolicitudes();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaSolicitudes.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaSolicitudes.get(i).getCodigo();
            columna[1]= listaSolicitudes.get(i).getNombre();
            columna[2]= listaSolicitudes.get(i).getCantidad();
            columna[3]= listaSolicitudes.get(i).getEstatus();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaPiezas(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("CODIGO");
        modelT.addColumn("DESCRIPCION");
        modelT.addColumn("MARCA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[3];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Pieza> listaPiezas = modeloC.listarPiezas();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaPiezas.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaPiezas.get(i).getCodigo();
            columna[1]= listaPiezas.get(i).getDescripcion();
            columna[2]= listaPiezas.get(i).getMarca();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaPiezas2(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("CODIGO");
        modelT.addColumn("DESCRIPCION");
        modelT.addColumn("MARCA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[3];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Pieza> listaPiezas = modeloC2.listarPiezas();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaPiezas.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaPiezas.get(i).getCodigo();
            columna[1]= listaPiezas.get(i).getDescripcion();
            columna[2]= listaPiezas.get(i).getMarca();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaPiezas3(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("CODIGO");
        modelT.addColumn("DESCRIPCION");
        modelT.addColumn("MARCA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[3];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Pieza> listaPiezas = modeloC3.listarPiezas();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaPiezas.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaPiezas.get(i).getCodigo();
            columna[1]= listaPiezas.get(i).getDescripcion();
            columna[2]= listaPiezas.get(i).getMarca();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTabla(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("MARCA");
        modelT.addColumn("PARTE");
        modelT.addColumn("LOCALIDAD");
        modelT.addColumn("ESTATUS");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[5];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Parte> listaPartes = modeloP.listarPartes();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaPartes.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaPartes.get(i).getSerie();
            columna[1]= listaPartes.get(i).getMarca();
            columna[2]= listaPartes.get(i).getParte();
            columna[3]= listaPartes.get(i).getLocalidad();
            columna[4]= listaPartes.get(i).getEstatus();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosA(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosA();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosH(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosH();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2020(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2020();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2021(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
     //tablas 2020
    public void llenarTablaTrabajosAño2021Enero(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021Enero();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2021Febrero(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021Febrero();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2021Marzo(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021Marzo();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2021Abril(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021Abril();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2021Mayo(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021Mayo();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2021Junio(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021Junio();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2021Julio(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021Julio();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2021Agosto(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021Agosto();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2021Septiembre(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021Septiembre();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2021Octubre(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021Octubre();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2021Noviembre(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021Noviembre();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    public void llenarTablaTrabajosAño2021Diciembre(JTable tablaD) {
        //crear el ojeto defaultablemodel
        modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        //agregar encabezados
        modelT.addColumn("SERIE");
        modelT.addColumn("NOMBRE");
        modelT.addColumn("APELLIDO");
        modelT.addColumn("FECHA");
        //crear un arreglo de object, que represente las 4 columnas de la tabla
        Object[] columna= new Object[4];
        //crear un arraylist<profesor> que reciba la informacion de la BD
        ArrayList<Trabajo> listaTrabajos = modeloc4.listarTrabajosAño2021Diciembre();
        //determinar el numero de registros que reciba la informacion de la BD
        int numRegistros= listaTrabajos.size();
        //en un ciclo leer el arraylist, usar el objeto object y agregarlo a JTable
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= listaTrabajos.get(i).getSerie();
            columna[1]= listaTrabajos.get(i).getNombre();
            columna[2]= listaTrabajos.get(i).getApellido();
            columna[3]= listaTrabajos.get(i).getFecha();
            modelT.addRow(columna);
        }
    }
    
    //tablas 2020
    
    public void editarFilaSoli(){
        if (vistasoli.tablasolicitudes.getSelectedRow() != -1) {
            int codigo = (int) modelT.getValueAt(vistasoli.tablasolicitudes.getSelectedRow(), 0);
            String nombre = String.valueOf(modelT.getValueAt(vistasoli.tablasolicitudes.getSelectedRow(), 1));
            int cantidad = (int) modelT.getValueAt(vistasoli.tablasolicitudes.getSelectedRow(), 2);
            String estatus = String.valueOf(modelT.getValueAt(vistasoli.tablasolicitudes.getSelectedRow(), 3));
            vistasoli.textfieldestatus.setText(estatus);
            claveeditar = codigo;
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione la solicitud a modificar","Error De Selección",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void actualizarFilaSoli(){
//        String codigo1 = String.valueOf(vistapartes.txtserie.getText());
//        String marca = String.valueOf(vistapartes.txtmarca.getText());
//        String parte = String.valueOf(vistapartes.txtparte.getText());
        String estatus = String.valueOf(vistasoli.textfieldestatus.getText());
        if (estatus != null && !"".equals(estatus)) {
            modeloS = new SolicitudDAO();
            modeloS.editarsolicitud(estatus, claveeditar);
            llenarTablaSoli(vistasoli.tablasolicitudes); 
            vistasoli.textfieldestatus.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Llene el campo de estatus","Error De Inserción",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void editarFila(){
        if (vistapartes.jTable1.getSelectedRow() != -1) {
            int serie = (int) modelT.getValueAt(vistapartes.jTable1.getSelectedRow(), 0);
            String marca = String.valueOf(modelT.getValueAt(vistapartes.jTable1.getSelectedRow(), 1));
            String parte = String.valueOf(modelT.getValueAt(vistapartes.jTable1.getSelectedRow(), 2));
            String localidad = String.valueOf(modelT.getValueAt(vistapartes.jTable1.getSelectedRow(), 3));
            String estatus = String.valueOf(modelT.getValueAt(vistapartes.jTable1.getSelectedRow(), 4));
            vistapartes.txtserie.setText(""+serie);
            vistapartes.txtmarca.setText(marca);
            vistapartes.txtparte.setText(parte);
            vistapartes.txtlocalidad.setText(localidad);
            claveeditar = serie;
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione la parte a modificar","Error De Selección",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void actualizarFila(){
        String serie1 = String.valueOf(vistapartes.txtserie.getText());
        String marca = String.valueOf(vistapartes.txtmarca.getText());
        String parte = String.valueOf(vistapartes.txtparte.getText());
        String localidad = String.valueOf(vistapartes.txtlocalidad.getText());
        if (serie1 != null && !"".equals(serie1) && marca != null && !"".equals(marca) && parte != null && !"".equals(parte) && localidad != null && !"".equals(localidad)) {
            modeloP = new ParteDAO();
            int serie = Integer.parseInt(serie1);
            modeloP.editarparte(serie, marca, parte, localidad, claveeditar);
            llenarTabla(vistapartes.jTable1); 
            vistapartes.txtserie.setText("");
            vistapartes.txtmarca.setText("");
            vistapartes.txtparte.setText("");
            vistapartes.txtlocalidad.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Llene todos los campos correctamente","Error De Inserción",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarFila(){
        int filaInicio= vistapartes.jTable1.getSelectedRow();
           int numFS= vistapartes.jTable1.getSelectedRowCount();
           ArrayList<String> listaDni= new ArrayList();
           String dni="";
           if(filaInicio>0){
               for (int i = 0; i < numFS; i++) {
                   dni=String.valueOf(vistapartes.jTable1.getValueAt(i+filaInicio, 0));
                   listaDni.add(dni);
               }
               
               for (int i = 0; i < numFS; i++) {
                   int rptaUsuario= JOptionPane.showConfirmDialog(null, "Quiere eliminar registro con "+ dni + " ?");
                   if(rptaUsuario==0){
                       modeloP.eliminarParte(Integer.parseInt(dni));
                   }
               }
               
               llenarTabla(vistapartes.jTable1);
           }else{
               JOptionPane.showMessageDialog(null, "Seleccione al menos una fila a eliminar");
           }
    }
    
    public void ingresarFila(){
            int Serie= Integer.parseInt(vistapartes.txtserie.getText());
            String Marca= vistapartes.txtmarca.getText();
            String Parte= vistapartes.txtparte.getText();
            String Localidad= vistapartes.txtlocalidad.getText();
            String Estatus=(String)vistapartes.comboestatus.getSelectedItem();
            String rptaRegistro= modeloP.insertarParte(Serie, Marca, Parte, Localidad, Estatus);
            if(rptaRegistro!=null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "Registro Erroneo");
            }
            limpiarelementos();
    }
    
    public void exportar(){
        JFileChooser archivos= new JFileChooser();
        archivos.setDialogTitle("Seleccionar Carpeta");
        archivos.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int guardarscript=archivos.showSaveDialog(null);
        if(guardarscript==JFileChooser.APPROVE_OPTION){
            String ruta=archivos.getSelectedFile().getPath();
            vistaex.jtxtexpor.setText(ruta);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaInicio.ingresar_btn) {
            validarcampo1();
        }
        if (e.getSource() == vistamenu.regres_btn) {
            vistamenu.setVisible(false);
            vistaInicio.setVisible(true);
        }
        
        if (e.getSource() == vistamenu.registrop_btn) {
            vistamenu.setVisible(false);
            vistapartes.setVisible(true);
        }
        
        if (e.getSource() == vistamenu.btn_cata) {
            vistamenu.setVisible(false);
            vistacatalogos.setVisible(true);
        }
        
        if (e.getSource() == vistamenu.btntraba) {
            vistamenu.setVisible(false);
            vistat.setVisible(true);
        }
        
        if (e.getSource() == vistamenu.btn_resp) {
            vistamenu.setVisible(false);
            vistaex.setVisible(true);
        }
        
        if (e.getSource() == vistapartes.regresarp_btn) {
            vistapartes.setVisible(false);
            vistamenu.setVisible(true);
        }
        
        if (e.getSource() == vistasoli.regresarvs_btn) {
            vistasoli.setVisible(false);
            vistamenu.setVisible(true);
        }
        
        if (e.getSource() == vistamenu.solicitudp_btn) {
            vistamenu.setVisible(false);
            vistasoli.setVisible(true);
        }
        
        //botones solicitud
        if (e.getSource() == vistasoli.actualizare_btn) {
            actualizarFilaSoli();
        }
        
        if (e.getSource() == vistasoli.buscars_btn) {
            llenarTablaSoli(vistasoli.tablasolicitudes);
        }
        
        if (e.getSource() == vistasoli.cambiare_btn) {
            editarFilaSoli();
        }
        
        //botones partes
        
        if (e.getSource() == vistapartes.btnListar) {
            llenarTabla(vistapartes.jTable1);
        }
        
        
        if (e.getSource() == vistapartes.btnEliminar) {
           eliminarFila();
            
        }
           

        if (e.getSource() == vistapartes.btnIngresar) {
            if(vistapartes.txtserie.getText().isEmpty() || vistapartes.txtmarca.getText().isEmpty() || vistapartes.txtparte.getText().isEmpty() || vistapartes.txtlocalidad.getText().isEmpty()){
                JOptionPane.showMessageDialog(vistapartes, "Ingresar todos los campos correctos");
            }else{
                 ingresarFila();
            }
          
        }

        
        if (e.getSource() == vistapartes.btnEditar) {
          editarFila();

        }

        if (e.getSource() == vistapartes.btnactualizar) {
           actualizarFila();
        }
        
        //botones piezas
        if (e.getSource() == vistacatalogos.btn_diebold) {
            llenarTablaPiezas(vistacatalogos.tablapiezas);
        }
        
        if (e.getSource() == vistacatalogos.btn_ccdm) {
            llenarTablaPiezas2(vistacatalogos.tablapiezas);
        }
        
        if (e.getSource() == vistacatalogos.btn_ncr) {
            llenarTablaPiezas3(vistacatalogos.tablapiezas);
        }
        
         if (e.getSource() == vistacatalogos.btn_rgre) {
            vistacatalogos.setVisible(false);
            vistamenu.setVisible(true);
        }
         
         //botones trabajos
         if (e.getSource() == vistat.btn_tra) {
            llenarTablaTrabajosA(vistat.tablatrabajos);
        }
        
        if (e.getSource() == vistat.btn_trh) {
            llenarTablaTrabajosH(vistat.tablatrabajos);
        }
        
        if (e.getSource() == vistat.btn_tra2020) {
            llenarTablaTrabajosAño2020(vistat.tablatrabajos);
        }
        
        if (e.getSource() == vistat.btn2021t) {
            llenarTablaTrabajosAño2021(vistat.tablatrabajos);
        }
        
        if (e.getSource() == vistat.btnregresart) {
            vistat.setVisible(false);
            vistamenu.setVisible(true);
        }
        
        //comboboxtrabajos2020
        if (e.getSource() == vistat.combobox2020) {
           String seleccion = (String) vistat.combobox2020.getSelectedItem();
            if (seleccion.equals("Enero")){
                llenarTablaTrabajosAño2021Enero(vistat.tablatrabajos);
            } else if (seleccion.equals("Febrero")){
                llenarTablaTrabajosAño2021Febrero(vistat.tablatrabajos);
            } else if (seleccion.equals("Marzo")){
                llenarTablaTrabajosAño2021Marzo(vistat.tablatrabajos);
            } else if (seleccion.equals("Abril")){
                llenarTablaTrabajosAño2021Abril(vistat.tablatrabajos);
            } else if (seleccion.equals("Mayo")){
                llenarTablaTrabajosAño2021Mayo(vistat.tablatrabajos);
            } else if (seleccion.equals("Junio")){
                llenarTablaTrabajosAño2021Junio(vistat.tablatrabajos);
            } else if (seleccion.equals("Julio")){
                llenarTablaTrabajosAño2021Julio(vistat.tablatrabajos);
            } else if (seleccion.equals("Agosto")){
                llenarTablaTrabajosAño2021Agosto(vistat.tablatrabajos);
            } else if (seleccion.equals("Septiembre")){
                llenarTablaTrabajosAño2021Septiembre(vistat.tablatrabajos);
            } else if (seleccion.equals("Octubre")){
                llenarTablaTrabajosAño2021Octubre(vistat.tablatrabajos);
            } else if (seleccion.equals("Noviembre")){
                llenarTablaTrabajosAño2021Noviembre(vistat.tablatrabajos);
            } else if (seleccion.equals("Diciembre")){
                llenarTablaTrabajosAño2021Diciembre(vistat.tablatrabajos);
            } 
        }
        
        //botones exportarbd
        if (e.getSource() == vistaex.btn_selecc) {
            exportar();
        }
        if (e.getSource() == vistaex.btn_respald) {
            String laruta=vistaex.jtxtexpor.getText();
            String nombrescripsql="\\Casuni.sql";
            String backup="";
            try {
                if(laruta.trim().length()>0){
                    backup="C:\\xampp\\mysql\\bin\\mysqldump --routines -u"+conexion.getUsuario()+" -p"+conexion.getPassword()+" -B "+conexion.getBD()+" -r "+laruta+nombrescripsql;
                    Runtime rtime=Runtime.getRuntime();
                    rtime.exec(backup);
                    JOptionPane.showMessageDialog(null, "La copia de seguridad de la base de datos es exitosa","Mensaje del Sistema",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccione una carpeta para guardar la copia de seguridad de la BD","Mensaje del sistema", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex)  {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == vistaex.btn_cancel) {
            
        }
        if (e.getSource() == vistaex.btn_regrere) { 
            vistaex.setVisible(false);
            vistamenu.setVisible(true);
        }
        
    }
}


    


