/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import Entities.demande;
import Entities.notification;
import Utils.DBConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author hamza
 */
public class gerernotification {
    
     private static Connection cnx=DBConnexion.connecterDB();
    public gerernotification() {
    }
    
   public static void ajouternotification(int num_notification,String type_notification,String email_notificaction)
    {
        PreparedStatement select;
         ResultSet resultat;
         PreparedStatement insert;
         try {
            
            select= cnx.prepareStatement("Select * from notification where num_notification="+num_notification+"");
            resultat=select.executeQuery();
            if(!resultat.isBeforeFirst())
            {
                insert=cnx.prepareStatement("INSERT INTO notification (num_notification,type_notification,email_notification) values (?,?,?)");
                insert.setInt(1, num_notification);
                insert.setString(2, type_notification);
                insert.setString(3, email_notificaction);
               
                
                
                insert.executeUpdate();
                System.out.println("notification ajoutee");
            }
            else
                 System.err.println("existe deja");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
   
     public static void modifiernotification(int num_notification,String type_notification)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement update;
        try {
             select= cnx.prepareStatement("Select * from notification where num_notification="+num_notification+"");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("Update notification set type_notification=? where num_notification=?" );
                update.setInt(2,num_notification);
                update.setString(1,type_notification);
           
               
                
                update.executeUpdate();
                System.out.println("modification terminée avec succes");
            }
            else
                System.err.println("erreur ");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
   
   
     
     
     
   
   
     public static void supprimerdemande(int num_notification)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement delete;
         try {
            select= cnx.prepareStatement("Select * from notification where num_notification="+num_notification+"");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                delete=cnx.prepareStatement("Delete from notification where num_notification='"+num_notification+"'");
                delete.executeUpdate();
                System.out.println("notification supprimee");
            }
            else
                 System.err.println("failed");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
      
   public static ArrayList<notification> afficher() {
        ArrayList<notification> notifications = new ArrayList<>();
        String req = "SELECT * FROM notification full join demande where id_demande=demande.ID";
        
        try {
  //   pste = conn.prepareStatement(req);
  //            ResultSet rs = pste.executeQuery();
            Statement 
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
               notification n = new notification(
                       rs.getInt("id_notification"), 
                       rs.getInt("num_notificatione"), 
                       rs.getString("type_notification"), 
                       rs.getString("email_notification"), 
                       rs.getInt("id_demande"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(gererdemande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return notifications;
    }
    public static List<notification> Recherchenotification(int num_notification) throws SQLException {
      List<notification> notifications = new ArrayList<>();
        String req = "select * from notification WHERE num_notification=" + num_notification;
         Statement 
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);

        while (rs.next()) {
            notification n = new notification(
                    rs.getInt("id_notification"),
                    rs.getInt("num_notification"),
                    rs.getString("type_notification"),
                    rs.getString("email_demande"),
                    rs.getInt("id_demande"));
                    
                    

            
            notifications.add(n);
        }
        return notifications;
    }

   
   
   
   
   
   
   
   
   
   
   
    }
   
     
     
     
     
     
     
     
     
     
     
   

