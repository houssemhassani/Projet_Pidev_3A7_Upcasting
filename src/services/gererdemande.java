/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author hamza
 */

import Entities.demande;
import Utils.DBConnexion;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class gererdemande {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    private static Connection cnx=DBConnexion.connecterDB();

    
    public gererdemande() {
    }
    
   public static void ajouterdemande(int num_demande,String type_demande,String date_demande,int id_citoyen,int id_service)
    {
        PreparedStatement select;
         ResultSet resultat;
         PreparedStatement insert;
         try {
            select= cnx.prepareStatement("Select * from demande where num_demande="+num_demande+"");
            resultat=select.executeQuery();
            if(!resultat.isBeforeFirst())
            {
                insert=cnx.prepareStatement("INSERT INTO demande (num_demande,type_demande,date_demande,id_service,id_citoyen) values (?,?,?,?,?)");
                insert.setInt(1, num_demande);
                 insert.setString(2, type_demande);  
                insert.setString(3, date_demande);
                insert.setInt(4, id_citoyen);
                insert.setInt(5, id_service);
                
                
                insert.executeUpdate();
                System.out.println("demande ajoutee");
            }
            else
                 System.err.println("demande existe");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
   
    
   public static void modifierdemande(int num_demande,String type_demande)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement update;
        try {
             select= cnx.prepareStatement("Select * from demande where num_demande="+num_demande+"");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("Update demande set type_demande=? where num_demande=?" );
                update.setInt(2,num_demande);
                update.setString(1,type_demande);
           
               ;
                
                update.executeUpdate();
                System.out.println("modification terminée avec succes");
            }
            else
                System.err.println("erreur ");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
   
   
   
     public static void supprimerdemande(int num_demande )
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement delete;
         try {
            select= cnx.prepareStatement("Select * from demande where num_demande="+num_demande+"");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                delete=cnx.prepareStatement("Delete from demande where num_demande='"+num_demande+"'");
                delete.executeUpdate();
                System.out.println("demande supprimee");
            }
            else
                 System.err.println("failed");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
 
      
    public static ArrayList<demande> afficher() {
        ArrayList<demande> personnes = new ArrayList<>();
        String req = "SELECT * FROM `demande`";
        
        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();
            Statement 
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                demande p = new demande();
                p.setId_demande(rs.getInt("ID"));
                p.setNum_demande(rs.getInt("num_demande"));
                p.setType_demande(rs.getString("type_demande"));
                p.setDate_demande(rs.getString("date_demande"));
                  p.setId_citoyen(rs.getInt("id_citoyen"));
                    p.setId_service(rs.getInt("id_service"));
                personnes.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(gererdemande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return personnes;
    }

     public static List<demande> Recherchedemande(int num_demande) throws SQLException {
      List<demande> demandes = new ArrayList<>();
        String req = "select * from demande WHERE num_demande=" + num_demande;
         Statement 
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);

        while (rs.next()) {
            demande d = new demande(
                    rs.getInt("num_demande"),
                    rs.getString("type"),
                    rs.getString("date"),
                    rs.getInt("id_citoyen"),
                    rs.getInt("id_service")
                    

            );
            demandes.add(d);
        }
        return demandes;
    }
        public int countDemEncours()
            {
                int i =0; 
                try {
                    
                PreparedStatement pst;
                String etat="en cours";
                String sql="select * from demande where etat='"+etat+"'";
                pst=cnx.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                
                    while (rs.next()){
                        i+=1;   
                    }
                } catch(SQLException ex) {
                    System.out.println(ex.getMessage());
                }  
                return i;
            }
        
        public int countDemTr()
            {
                int i =0; 
                try {
                    
                PreparedStatement pst;
                String etat="traitée";
                String sql="select * from demande where etat='"+etat+"'";
                pst=cnx.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                
                    while (rs.next()){
                        i+=1;   
                    }
                } catch(SQLException ex) {
                    System.out.println(ex.getMessage());
                }  
                return i;
            }
        
        public int countDemNtr()
            {
                int i =0; 
                try {
                    
                PreparedStatement pst;
                String etat="non traitée";
                String sql="select * from demande where etat='"+etat+"'";
                pst=cnx.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                
                    while (rs.next()){
                        i+=1;   
                    }
                } catch(SQLException ex) {
                    System.out.println(ex.getMessage());
                }  
                return i;
            }

    }
    

   

     
     
     
     
     
     
     
     
     
     
     
   
  

   
   
   
   
   


 
    