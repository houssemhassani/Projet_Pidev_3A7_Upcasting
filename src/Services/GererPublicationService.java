/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Publication;
import Utils.DBConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ghassen-pc
 */
public class GererPublicationService {
    private static Connection cnx=DBConnexion.getConnexion();

    public GererPublicationService() {
    } 
    
    public static void ajouterPublication(Publication p)
    {
        
         ResultSet resultat;
         PreparedStatement insert;
         try {
            insert=cnx.prepareStatement("insert into publication (status,photo,confirm_publication,id_citoyen) values(?,?,?,?)");
            insert.setString(1, p.getStatus());
            insert.setBoolean(3, false);
            insert.setString(2,p.getPhoto());
            insert.setInt(4,p.getCitoyen_id());
           // insert.setInt(3,p.getCitoyen_id() );
           // insert.setBoolean(4, false);
            insert.executeUpdate();
             System.out.println("publication ajoutee");
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
        
    }
    public static void modifierStatusPublication(int id,String status)
    {
        ResultSet resultat;
         PreparedStatement update;
         PreparedStatement select;
         try {
            select=cnx.prepareStatement("select * from publication where id=?");
            select.setInt(1, id);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("update publication set status=? where id=?");
                update.setString(1, status);
                update.setInt(2, id);
                update.executeUpdate();
                System.out.println("status modifiee");
            }
            else
                 System.err.println("publication introuvable");
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
    }
    
    
    
    public  void modifierPhotoPublication(Publication p)
    {
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;
        try {
            select=cnx.prepareStatement("select * from publication where id ="+p.getId());
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("update publication set status='"+p.getStatus()+"' where id="+p.getId());
                update.executeUpdate();
                System.out.println("photo modifiee");
            }
            else
                System.err.println("publication introuvable");
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
     public  void ConfirmerPublication(Publication p)
    {
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;
        try {
            select=cnx.prepareStatement("select * from publication where id ="+p.getId());
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("update publication set confirm_publication="+true+" where id="+p.getId());
                update.executeUpdate();
                System.out.println("photo modifiee");
            }
            else
                System.err.println("publication introuvable");
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public  void supprimerPublication(int id)
    {
        PreparedStatement  delete;
        PreparedStatement  delete2;
        PreparedStatement select;
        ResultSet resultat;
        try {
            select=cnx.prepareStatement("select * from publication where id="+id);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                delete2=cnx.prepareStatement("delete from commantaire where id_publication="+id);
                delete2.executeUpdate();
                delete=cnx.prepareStatement("delete from publication where id="+id);
                
                
                delete.executeUpdate();
                System.out.println("publication supprimeeeee");
            }
            else
                System.err.println("publication non trouvable");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public  ArrayList<Publication>  getPublicationsConfirmee()
    {
        PreparedStatement select;
        PreparedStatement select2;
        ResultSet resultat2;
        ResultSet resultat;
        ArrayList<Publication> pubs=new ArrayList<Publication>();
        try {
            select=cnx.prepareStatement("select * from publication inner join citoyen on publication.id_citoyen=citoyen.id    where publication.confirm_publication=?");
            select2=cnx.prepareStatement("select * from publication inner join employee on publication.id_employee=employee.id    where publication.confirm_publication=?");
            select.setBoolean(1, true);
            select2.setBoolean(1, true );
            resultat=select.executeQuery();
            resultat2=select2.executeQuery();
            while(resultat.next())
            {
                Publication pub=new Publication();
                pub.setId(resultat.getInt("id"));
                pub.setStatus(resultat.getString("status"));
                pub.setPhoto(resultat.getString("photo"));
                pub.setUtilisateur_nom(resultat.getString("nom")+" "+resultat.getString("prenom")); 
                pubs.add(pub);
                
            }
             while(resultat2.next())
            {
                Publication pub=new Publication();
              
                pub.setStatus(resultat2.getString("status"));
                pub.setPhoto(resultat2.getString("photo"));
                pub.setUtilisateur_nom(resultat2.getString("nom")+" "+resultat2.getString("prenom")); 
                pubs.add(pub);
                
            }
           
            
        } catch (SQLException e) {
            return null;
        }
         return pubs;
    }
    public  ArrayList<Publication>  getPublications()
    {
        PreparedStatement select;
        PreparedStatement select2;
        ResultSet resultat2;
        ResultSet resultat;
        ArrayList<Publication> pubs=new ArrayList<Publication>();
        try {
            select=cnx.prepareStatement("select * from publication where publication.confirm_publication=?");
            //select2=cnx.prepareStatement("select * from publication inner join employee on publication.id_employee=employee.id    where publication.confirm_publication=?");
            select.setBoolean(1, true);
            //select2.setBoolean(1, true );
            resultat=select.executeQuery();
            //resultat2=select2.executeQuery();
            while(resultat.next())
            {
                Publication pub=new Publication();
                pub.setId(resultat.getInt("id"));
                pub.setStatus(resultat.getString("status"));
                pub.setPhoto(resultat.getString("photo"));
               // pub.setUtilisateur_nom(resultat.getString("nom")+" "+resultat.getString("prenom")); 
                pubs.add(pub);
                
            }
            /* while(resultat2.next())
            {
                Publication pub=new Publication();
              
                pub.setStatus(resultat2.getString("status"));
                pub.setPhoto(resultat2.getString("photo"));
                pub.setUtilisateur_nom(resultat2.getString("nom")+" "+resultat2.getString("prenom")); 
                pubs.add(pub);
                
            }*/
           
            
        } catch (SQLException e) {
            return null;
        }
         return pubs;
    }
    public  ArrayList<Publication> getPublicationsNonConfirmee()
    {
        PreparedStatement select;
        PreparedStatement select2;
        ResultSet resultat;
        ResultSet resultat2;
        ArrayList<Publication> pubs=new ArrayList<Publication>();
        try {
            select=cnx.prepareStatement("select * from publication inner join citoyen on publication.id_citoyen=citoyen.id    where publication.confirm_publication=?");
           // select2=cnx.prepareStatement("select * from publication inner join employee on publication.id_employee=employee.id    where publication.confirm_publication=?");
            select.setBoolean(1, false);
           // select2.setBoolean(1, false);
            resultat=select.executeQuery();
          //  resultat2=select2.executeQuery();
            while(resultat.next())
            {
                Publication pub=new Publication();
                pub.setId(resultat.getInt("id"));
                pub.setStatus(resultat.getString("status"));
                pub.setPhoto(resultat.getString("photo"));
                pub.setUtilisateur_nom(resultat.getString("nom")+" "+resultat.getString("prenom")); 
                pubs.add(pub);
                
                
            }
            
           
            
        } catch (SQLException e) {
            return pubs;
            
        }
         return pubs;
        
    }
}
