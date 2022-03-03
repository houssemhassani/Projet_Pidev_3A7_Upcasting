/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Citoyen;
import Utils.DBConnexion;
import Utils.JavaMailUtil;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author Asus
 */
public class GererCitoyenService {
    private static Connection cnx=DBConnexion.getConnexion();
    public  boolean confirmerCitoyen(Citoyen cit)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement update;
        
        try {
           
                update=cnx.prepareStatement("Update citoyen set email_confirmed=? where cin=?");
                update.setBoolean(1, true);
                update.setString(2, cit.getCin());
                JavaMailUtil.sendEmail(cit.getEmail(),"Votre compte est confirmée \n Vous pouvez acceder à notre application");
                update.executeUpdate();
                System.out.println("Citoyen confirmee");
                                System.out.println("Citoyen confirmee");
                                

            
          
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } catch (Exception ex) {
            Logger.getLogger(GererCitoyenService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
        
    }
    public  boolean retirerCitoyen(Citoyen cit)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement delete;
        
        try {
           
                delete=cnx.prepareStatement("delete from citoyen where cin=?");
               
                delete.setString(1, cit.getCin());
                JavaMailUtil.sendEmail(cit.getEmail(),"Votre inscription est refusée \n Vous ne pouvez acceder pas à notre application");
                delete.executeUpdate();
                System.out.println("Citoyen retiree");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
        
    }
    public  ArrayList<Citoyen> getCitoyensNonConfirmee()
    {
        PreparedStatement select;
        ResultSet resultat;
        ArrayList<Citoyen> emps=new ArrayList<>();
        
        try
        {
            select=cnx.prepareStatement("select * from citoyen where email_confirmed="+false);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                while(resultat.next())
            {
                Citoyen e=new Citoyen();
                //e.set(resultat.getInt("id"));
                e.setNom(resultat.getString("nom"));
                e.setPrenom(resultat.getString("prenom"));
                e.setEmail(resultat.getString("email"));
                e.setCin(resultat.getString("cin"));
                e.setVille(resultat.getString("ville"));  
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
    public int countCitoyenConfirme()
    {
        int i =0; 
        try {
            PreparedStatement t ;
            String query= "Select * from citoyen where  email_confirmed="+true;
            t = cnx.prepareStatement(query);
            ResultSet rs = t.executeQuery();
            while (rs.next()){
                i+=1;   
            }
        } catch(SQLException ex) {
            System.out.println("erreur lors de la mise à jour de l'evenement " + ex.getMessage());
        }  
        return i;
    }
    public int countCitoyenNonConfirmee()
    {
        int i =0; 
        try {
            PreparedStatement t ;
            String query= "Select * from citoyen where  email_confirmed="+false;
            t = cnx.prepareStatement(query);
            ResultSet rs = t.executeQuery();
            while (rs.next()){
                i+=1;   
            }
        } catch(SQLException ex) {
            System.out.println("erreur lors de la mise à jour de l'evenement " + ex.getMessage());
        }  
        return i;
    }
    public int countCitoyen()
    {
        int i =0; 
        try {
            PreparedStatement t ;
            String query= "Select * from citoyen";
            t = cnx.prepareStatement(query);
            ResultSet rs = t.executeQuery();
            while (rs.next()){
                i+=1;   
            }
        } catch(SQLException ex) {
            System.out.println("erreur lors de la mise à jour de l'evenement " + ex.getMessage());
        }  
        return i;
    }
    public String  genererQrCode(Citoyen cit)
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
    
    
    
}
