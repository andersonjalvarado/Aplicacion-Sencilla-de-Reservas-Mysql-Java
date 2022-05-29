/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ANDERSON
 */
public class Conexion {
    Connection link;
    public String db="baseunibnb";
    //public String url="jdbc:mysql://127.0.0.1/" +db;
    //public String url="jdbc:mysql://localhost:3306/baseunibnb" +db;
    public String url="jdbc:mysql://localhost/" +db;
    public String user="root";
    public String pass="";

    public Conexion() {
    }
    
    public Connection conectar(){
        link=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            link=DriverManager.getConnection(this.url, this.user, this.pass);
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            
        }
        
        return link;
    }
    
    public void desconectar(){
        try{
            this.link.close();
        }catch(Exception e){}
    }
}
