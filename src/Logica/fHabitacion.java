/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import Datos.Habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ANDERSON
 */
public class fHabitacion {
   
   private Conexion mysql = new Conexion();
   private Connection cn = mysql.conectar();
   private String sSQL = "";
   public Integer totalregistros;
   
   
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","Número","Descripción","Ubicacion","Precio","Estado","Tipo habitación"};
       
       String [] registro = new String [7];
       
       totalregistros = 0;
       modelo = new DefaultTableModel(null,titulos);
       
       sSQL = "select idHabitacion,numero,descripcion,ubicacion,precio_diario,estado,"
               + "tipo_habitacion from habitacion where ubicacion like '%"+ buscar + "%' and estado='Disponible' order by idHabitacion";
       
       try {
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sSQL);
           
           while(rs.next()){
               
               registro [0]=rs.getString("idHabitacion");
               registro [1]=rs.getString("numero");
               registro [2]=rs.getString("descripcion");
               registro [3]=rs.getString("ubicacion");
               registro [4]=rs.getString("precio_diario");
               registro [5]=rs.getString("estado");
               registro [6]=rs.getString("tipo_habitacion");
               
               totalregistros = totalregistros+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
     } 
   
   
   public DefaultTableModel mostrar2(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","Número","Descripción","Ubicacion","Precio","Estado","Tipo habitación"};
       
       String [] registro = new String [7];
       
       totalregistros=0;
       modelo = new DefaultTableModel(null,titulos);
       
       sSQL = "select idHabitacion,numero,descripcion,ubicacion,precio_diario,estado,"
               + "tipo_habitacion from habitacion inner join persona on arrendador=usuario"
               + "where ubicacion like '%"+ buscar + "%' order by idHabitacion";
       
       try {
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("idHabitacion");
               registro [1]=rs.getString("numero");
               registro [2]=rs.getString("descripcion");
               registro [3]=rs.getString("ubicacion");
               registro [4]=rs.getString("precio_diario");
               registro [5]=rs.getString("estado");
               registro [6]=rs.getString("tipo_habitacion");
               
               totalregistros=totalregistros+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
    }
   public boolean insertar (Habitacion dts){
       sSQL="insert into habitacion (numero,descripcion,ubicacion,precio_diario,estado,tipo_habitacion,arrendador)" +
               "values (?,?,?,?,?,?,?)";
       try {
           
           PreparedStatement pst = cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNumero());
           pst.setString(2, dts.getDescripcion());
           pst.setString(3, dts.getUbicacion());
           pst.setDouble(4, dts.getPrecio_diario());
           pst.setString(5, dts.getEstado());
           pst.setString(6, dts.getTipo_habitacion());
           pst.setString(7, dts.getArrendador());
           
           int n = pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }       
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
   
   public boolean editar (Habitacion dts){
       sSQL="update habitacion set numero=?,descripcion=?,ubicacion=?,precio_diario=?,estado=?,tipo_habitacion=?,arrendador=? "+
               " where idHabitacion=?";
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNumero());
           pst.setString(2, dts.getDescripcion());
           pst.setString(3, dts.getUbicacion());
           pst.setDouble(4, dts.getPrecio_diario());
           pst.setString(5, dts.getEstado());
           pst.setString(6, dts.getTipo_habitacion());
           pst.setString(7, dts.getArrendador());
           pst.setInt(8, dts.getIdhabitacion());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   } 
  
   public boolean desocupar (Habitacion dts){
       sSQL="update habitacion set estado='Disponible'"+
               " where idHabitacion=?";
           //alt + 39
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
          
           pst.setInt(1, dts.getIdhabitacion());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   } 
  
   
   public boolean ocupar (Habitacion dts){
       sSQL="update habitacion set estado='Ocupado'"+
               " where idHabitacion=?";
           //alt + 39
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
          
           pst.setInt(1, dts.getIdhabitacion());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
    } 

   public boolean eliminar (Habitacion dts){
       sSQL="delete from habitacion where idHabitacion=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdhabitacion());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
    }
   public double costoDia(int buscar){
       sSQL = "select precio_diario from habitacion where idHabitacion like '%"+ buscar + "%'";
       double costo = 0; 
       try {
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sSQL);
           costo = Double.parseDouble(rs.getString("precio_diario"));            
           return costo;
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return costo;
       }
   }
}
