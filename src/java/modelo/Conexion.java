
package modelo;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    public static final String url = "jdbc:mysql://localhost:3306/contactosUcoltis?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrival=true\";";
    public static final String usuario = "root";
    public static final String contrasena = "admin";
    
    public Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url,usuario,contrasena);
            JOptionPane.showMessageDialog(null, "Conexión exitosa");
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println("Error "+ e);
        }
        return con;
    }
}
