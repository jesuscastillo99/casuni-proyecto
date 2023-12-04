
package Modelo;
import java.sql.DriverManager;
import java.sql.Connection;
public class ConexionBD {
    Connection conexion;
    static String bd="proyectobd";
    static String user="root";
    static String password="";
    static String server="jdbc:mysql://localhost:3306/"+bd;
    
    public Connection establecerConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection(server, user, password);
            System.out.println("Conexión exitosa!!");
        
        }catch(Exception e){
            System.out.println("No se puede realizar la conexion a la BD\n"+e);
            
        }
        return conexion;
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    public void desconectar(){
        conexion=null;
    }
    
    public String getUsuario(){
        return user;
    }
    public String getPassword(){
        return password;
    }
    public String getBD(){
        return bd;
    }
}
