/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDERSON
 */
public class fReserva {
   private Conexion mysql=new Conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   
   
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","IdHabitacion","Numero","Cliente","Fecha Ingreso","Fecha Salida","Costo","Estado"};
       
       String [] registro =new String [8];
       
       totalregistros=0;
       modelo = new DefaultTableModel(null,titulos);
       
       sSQL="select r.idReserva,r.idHabitacion,h.numero,"+
               "(select nombre from persona where usuario=r.cliente)as clienten,"+
               "(select apaterno from persona where usuario=r.cliente)as clienteap,"+
               "r.fecha_ingreso,r.fecha_salida,"+
               "r.costo_reserva,r.estado from reserva r inner join habitacion h on r.idHabitacion=h.idHabitacion "
               + "where r.fecha_ingreso like '%"+ buscar + "%' order by idReserva desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("idReserva");
               registro [1]=rs.getString("idHabitacion");
               registro [2]=rs.getString("numero");
               registro [3]=rs.getString("clienten") + " " + rs.getString("clienteap") ;
               registro [4]=rs.getString("fecha_ingreso");
               registro [5]=rs.getString("fecha_salida");
               registro [6]=rs.getString("costo_alojamiento");
               registro [7]=rs.getString("estado");
               
               totalregistros=totalregistros+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }   
   } 
   
   public boolean insertar (Reserva dts){
       sSQL="insert into reserva (idHabitacion,cliente,fecha_ingreso,fecha_salida,costo_reserva,estado)" +
               "values (?,?,?,?,?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdHabitacion());
           pst.setString(2, dts.getCliente());
           pst.setDate(3, dts.getFecha_ingreso());
           pst.setDate(4, dts.getFecha_salida());
           pst.setDouble(5, dts.getCosto_reserva());
           pst.setString(6, dts.getEstado());
           
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
   
   public boolean editar (Reserva dts){
       sSQL="update reserva set idHabitacion=?,cliente=?,fecha_ingreso=?,fecha_salida=?,costo_reserva=?,estado=?"+
               " where idreserva=?";
           
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdHabitacion());
           pst.setString(2, dts.getCliente());
           pst.setDate(3, dts.getFecha_ingreso());
           pst.setDate(4, dts.getFecha_salida());
           pst.setDouble(5, dts.getCosto_reserva());
           pst.setString(6, dts.getEstado());
           
           pst.setInt(7, dts.getIdReserva());
           
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
   
   public boolean pagar (Reserva dts){
       sSQL="update reserva set estado='Pagada'"+
               " where idReserva=?";
           //alt + 39
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
             
           
           pst.setInt(1, dts.getIdReserva());
           
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
   
   public boolean eliminar (Reserva dts){
       sSQL="delete from reserva where idReserva=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdReserva());
           
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
}
