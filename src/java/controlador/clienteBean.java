package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import modelo.Conexion;
import java.sql.*;

@Named(value = "clienteBean")
@RequestScoped
public class clienteBean {

    public clienteBean() {
    }

    private int idcliente;
    private String nombrecliente;
    private String apellidocliente;
    private String direccioncliente;

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getApellidocliente() {
        return apellidocliente;
    }

    public void setApellidocliente(String apellidocliente) {
        this.apellidocliente = apellidocliente;
    }

    public String getDireccioncliente() {
        return direccioncliente;
    }

    public void setDireccioncliente(String direccioncliente) {
        this.direccioncliente = direccioncliente;
    }

    public List<clienteBean> getTablaClientes() {
        List<clienteBean> data = new ArrayList<>();
        Conexion con = new Conexion();
        try {
            Connection conexion = con.getConnection();
            Statement sql = conexion.createStatement();
            ResultSet rs = sql.executeQuery("SELECT * FROM cliente;");

            while (rs.next()) {
                clienteBean obj = new clienteBean();
                obj.setIdcliente(rs.getInt("idcliente"));
                obj.setNombrecliente(rs.getString("nombrecliente"));
                obj.setApellidocliente(rs.getString("apellidocliente"));
                obj.setDireccioncliente(rs.getString("direccioncliente"));
                data.add(obj);
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error al leer datos" + e);
        }
        return data;
    }
    
    public void agregarCliente(){
        Conexion conex = new Conexion();
        
        try {
            Connection con = conex.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO cliente (nombrecliente, apellidocliente, direccioncliente) VALUES (?,?,?);");
            ps.setString(1, nombrecliente);
            ps.setString(2, apellidocliente);
            ps.setString(3, direccioncliente);
            ps.executeUpdate();
            System.out.println("Se agregó exitosamente");
            con.close();
        } catch (Exception e) {
            System.out.println("Error al agregar cliente " + e);
        }
    }
    
    public void modificarCliente(){
        Conexion conex = new Conexion();
        
        try {
            Connection con = conex.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE cliente SET nombrecliente=?, apellidocliente=?, direccioncliente=? where idcliente=?;");
            ps.setString(1, nombrecliente);
            ps.setString(2, apellidocliente);
            ps.setString(3, direccioncliente);
            ps.setInt(4, idcliente);
            ps.executeUpdate();
            System.out.println("Se modifico el cliente exitosamente!!");
            con.close();
        } catch (Exception e) {
            System.out.println("Error al modificar cliente");
        }
    }
    
    public void eliminarCliente(){
        Conexion conex = new Conexion();
        
        try {
            Connection con = conex.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM cliente WHERE idcliente=?;");
            ps.setInt(1, idcliente);
            ps.executeUpdate();
            System.out.println("Se elimino exitosamente!");
            con.close();
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente " + e);
        }
    }
    
}
