/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Message;
import Utils.DBConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class GererMessageService {
    private static Connection cnx=DBConnexion.getConnexion();
    public static void envoyerMessage(String message,int from_id,int to_id)
    {
        PreparedStatement insert;
        ResultSet resultat;
        try {
            insert=cnx.prepareStatement("INSERT INTO message( message, from_id, to_id) VALUES (?,?,?)");
            insert.setString(1, message);
            insert.setInt(2, from_id);
            insert.setInt(3, to_id);
            insert.executeUpdate();
            System.out.println("message envoyée");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static  void SupprimerMessage(int id)
    {
        PreparedStatement delete;
        ResultSet resultat;
        PreparedStatement select;
        try {
            select=cnx.prepareStatement("select * from message where id="+id);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                delete=cnx.prepareStatement("delete from message where id="+id);
                delete.executeUpdate();
                System.out.println("message supprimee");
            }
            else{System.err.println("non supprimee");}
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static ArrayList<Message> getMessageParRecept(int to_id)
    {
        PreparedStatement select;
        ResultSet resultat;
        
        ArrayList<Message> messages=new ArrayList<>();
        try {
            select=cnx.prepareStatement("select * from message full join employee where  employee.id="+to_id);
            resultat=select.executeQuery();
            while(resultat.next())
            {
                Message m=new Message();
                m.setMessage(resultat.getString("message"));
                m.setTo_nom(resultat.getString("nom"));
                //m.setTo_id(to_id);
                messages.add(m);
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return messages;
    }
    public static ArrayList<Message> getMessageParEmetteur(int from_id)
    {
        PreparedStatement select;
        ResultSet resultat;
        
        ArrayList<Message> messages=new ArrayList<>();
        try {
            select=cnx.prepareStatement("select * from message full join employee where  from_id="+from_id+" and employee.id="+from_id);
            resultat=select.executeQuery();
            while(resultat.next())
            {
                Message m=new Message();
                m.setMessage(resultat.getString("message"));
                m.setTo_nom(resultat.getString("nom"));
                //m.setTo_id(to_id);
                messages.add(m);
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return messages;
    }
    public static ArrayList<Message> getDiscussion(int from_id,int to_id)
    {
        PreparedStatement select;
        ResultSet resultat;
        
        ArrayList<Message> messages=new ArrayList<>();
        try {
            select=cnx.prepareStatement("select * from message full join employee where  from_id="+from_id+" and employee.id="+to_id+" or to_id="+from_id);
            resultat=select.executeQuery();
            while(resultat.next())
            {
                Message m=new Message();
                m.setMessage(resultat.getString("message"));
                m.setTo_nom(resultat.getString("nom")+" "+resultat.getString("prenom"));
                //m.setTo_id(to_id);
                messages.add(m);
            }  
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return messages;
    }
    
    
}
