/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shuttle.fankaar;

import java.sql.*;
import javax.swing.JOptionPane;
public class DbConnection {
    public static Connection ConnectDb(){
        try{
            return DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6499104", "sql6499104", "TqaxF4snS7");
        }
        catch (com.mysql.cj.jdbc.exceptions.CommunicationsException e){
            JOptionPane.showMessageDialog(null, "No internet Connection");
            return null;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
