/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Citoyen;
import Entities.Responsable;
import Utils.DBConnexion;
import Utils.JavaMailUtil;
import impl.BCrypt;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author Asus
 */
public class GererResponsableService {
    private static Connection cnx=DBConnexion.getConnexion();

    public GererResponsableService() {
    }
    
    public  boolean ajouterResponsable(Responsable responsable)
    {
        PreparedStatement select;
        PreparedStatement select2;
        ResultSet resultat2;
         ResultSet resultat;
         PreparedStatement insert;
         try {
            select= cnx.prepareStatement("Select * from employee where cin='"+responsable.getCin()+"'");
            select2=cnx.prepareStatement("select * from service where nomService='"+responsable.getNom_service()+"'");
            resultat2=select2.executeQuery();
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst()) return false;
            else
            {
            while(resultat2.next())
            {
                responsable.setService_id(resultat2.getInt("id"));
            }
            }
            if(!resultat.isBeforeFirst())
            {
                insert=cnx.prepareStatement("INSERT INTO employee (nom,prenom,email,cin,equipe_id,service_id,role,mot_de_passe) values (?,?,?,?,?,?,?,?)");
                insert.setString(1, responsable.getNom());
                insert.setString(2, responsable.getPrenom());
                insert.setString(3, responsable.getEmail());
                insert.setString(4, responsable.getCin());
                insert.setInt(6, responsable.getService_id());
                insert.setInt(5, 0);
                insert.setInt(7, 0);
                
                String mdp;
                mdp=BCrypt.hashpw(responsable.getMot_de_passe(), BCrypt.gensalt());
                insert.setString(8, mdp);
                insert.executeUpdate();
                JavaMailUtil.sendEmail(responsable.getEmail(), "Bienvenue dans notre application E-Citoyen \n Vous ajoutée comme responsable du service "+responsable.getService_id()+"\n Login :"+responsable.getCin()+"\n Mot de passe: "+responsable.getMot_de_passe());
                System.out.println("Responsable ajoutee");
                return true;
            }
            else
                 System.err.println("Responsable existe");
            return false;
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
             return false;
        }
        
    }
    public  boolean modifierResponsable(Responsable responsable)
    {
       PreparedStatement select;
        PreparedStatement select2;
        ResultSet resultat2;
         ResultSet resultat;
         PreparedStatement insert;
         try {
            select= cnx.prepareStatement("Select * from employee where cin='"+responsable.getCin()+"'");
            select2=cnx.prepareStatement("select * from service where nomService='"+responsable.getNom_service()+"'");
            resultat2=select2.executeQuery();
            resultat=select.executeQuery();
            if(!resultat.isBeforeFirst()) return false;
            else
            {
            while(resultat2.next())
            {
                responsable.setService_id(resultat2.getInt("id"));
                System.out.println(responsable.getService_id());
            }
            }
            if(resultat.isBeforeFirst())
            {
                insert=cnx.prepareStatement("update employee set  nom=?,prenom=?,email=?,service_id=? where cin=?");
                insert.setString(1, responsable.getNom());
                insert.setString(2, responsable.getPrenom());
                insert.setString(3, responsable.getEmail());
                insert.setInt(4, responsable.getService_id());
                insert.setString(5, responsable.getCin());
                insert.executeUpdate();
                //JavaMailUtil.sendEmail(responsable.getEmail(), "Bienvenue dans notre application E-Citoyen \n Vous ajoutée comme responsable du service "+responsable.getService_id()+"\n Login :"+responsable.getCin()+"\n Mot de passe: "+responsable.getMot_de_passe());
                System.out.println("Responsable modifiée");
                return true;
            }
            else
                 System.err.println("Responsable non modifié");
            return false;
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
             return false;
        }
    }
    public  void modifierServiceDeResponsable(String cin,int service_id)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement update;
        try {
            select = cnx.prepareStatement("Select * from employee where cin='"+cin+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("Update employee set service_id=? where cin=?");
                update.setInt(1, service_id);
                update.setString(2, cin);
                update.executeUpdate();
                System.out.println("Service modifiee pour ce responsable");
            }
            else
                System.err.println("Responsable n'exitse pas");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public  boolean supprimerResponsable(String cin)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement delete;
         try {
            select= cnx.prepareStatement("Select * from employee where cin='"+cin+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                delete=cnx.prepareStatement("Delete from employee where cin='"+cin+"'");
                delete.executeUpdate();
                System.out.println("Responsable supprimee");
                return true;
            }
            else
                 System.err.println("Responsable n'existe pas");
            return false;
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
             return false;
        }
        
    }
    public  ArrayList<Responsable> getResponsables()
    {
        PreparedStatement select;
        ResultSet resultat;
        ArrayList emps=new ArrayList<>();
        
        try
        {
            select=cnx.prepareStatement("select * from employee inner join service on employee.service_id=service.id where role="+0);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                while(resultat.next())
            {
                Responsable e=new Responsable();
                e.setNom(resultat.getString("nom"));
                e.setPrenom(resultat.getString("prenom"));
                e.setEmail(resultat.getString("email"));
                e.setCin(resultat.getString("cin"));
                e.setNom_service(resultat.getString("nomService"));
                emps.add(e);
            }
            }
            else 
                return null;
            
        }
        catch(SQLException ex)
        {
            return null;
        }
        return emps;
    }
     public  ArrayList<Responsable> getResponsablesByNom(String nom)
    {
        PreparedStatement select;
        ResultSet resultat;
        ArrayList emps=new ArrayList<>();
        
        try
        {
            select=cnx.prepareStatement("select * from employee inner join service on employee.service_id=service.id where role="+0+" and nom='"+nom+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                while(resultat.next())
            {
                Responsable e=new Responsable();
                e.setNom(resultat.getString("nom"));
                e.setPrenom(resultat.getString("prenom"));
                e.setEmail(resultat.getString("email"));
                e.setCin(resultat.getString("cin"));
                e.setNom_service(resultat.getString("nomService"));
                emps.add(e);
            }
            }
            else 
                return null;
            
        }
        catch(SQLException ex)
        {
            return null;
        }
        return emps;
    }
    public String  genererQrCode(Responsable cit)
    {
        FileOutputStream fos=null;
        try {
            ByteArrayOutputStream out=QRCode.from(cit.toString()).to(ImageType.JPG).stream();
            String path="C:\\Users\\Asus\\Documents\\NetBeansProjects\\Project_Pidev\\src\\images\\"+cit.getNom()+".jpg";
            File f=new File(path);
            fos = new FileOutputStream(f);
            fos.write(out.toByteArray());
            fos.flush();
            System.out.println(path);
            System.out.println(f.toPath());
            return cit.getNom();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GererCitoyenService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(GererCitoyenService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
        
        }
     public String getResponsable(String cin)
     {
         String nom=null;
        try {
            PreparedStatement select;
            ResultSet resultat;
            select=cnx.prepareStatement("select * from admin where cin='"+cin+"'");
            resultat=select.executeQuery();
            while (resultat.next())
            {
                nom=resultat.getString("nom")+" "+resultat.getString("prenom");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminLoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
     }
    
    
}
