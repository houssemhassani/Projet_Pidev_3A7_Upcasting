/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Admin;
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
public class GererAdminService {
    private static Connection cnx=DBConnexion.getConnexion();
    public boolean ajouterAdmin(Admin admin)
    {
        PreparedStatement select;
        PreparedStatement select2;
        ResultSet resultat2;
         ResultSet resultat;
         PreparedStatement insert;
         try {
            select= cnx.prepareStatement("Select * from admin where cin='"+admin.getCin()+"'");
            //select2=cnx.prepareStatement("select * from service where nomService='"+admin.getNom_service()+"'");
            //resultat2=select2.executeQuery();
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst()) return false;
            if(!resultat.isBeforeFirst())
            {
                insert=cnx.prepareStatement("INSERT INTO admin (nom,prenom,email,cin,mot_de_passe) values (?,?,?,?,?)");
                insert.setString(1, admin.getNom());
                insert.setString(2, admin.getPrenom());
                insert.setString(3, admin.getEmail());
                insert.setString(4, admin.getCin());
                String mdp;
                mdp=BCrypt.hashpw(admin.getMot_de_passe(), BCrypt.gensalt());
                insert.setString(5, mdp);
                insert.executeUpdate();
                JavaMailUtil.sendEmail(admin.getEmail(), "Bienvenue dans notre application E-Citoyen \n Vous ajoutée comme admin \n  Login :"+admin.getCin()+"\n Mot de passe: "+admin.getMot_de_passe());
                System.out.println("Admin ajoutee");
                return true;
            }
            else
                 System.err.println("Admin existe");
            return false;
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
             return false;
        }
    }
    public boolean retirerAdmin(Admin cit)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement delete;
        
        try {
           
                delete=cnx.prepareStatement("delete from admin where cin=?");
               
                delete.setString(1, cit.getCin());
                JavaMailUtil.sendEmail(cit.getEmail(),"Vous n'êtes pas encore un admin de notre application \n Vous ne pouvez acceder pas à notre application");
                delete.executeUpdate();
                System.out.println("Admin retiree");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
        
    }
     public  ArrayList<Admin> getAdmins()
    {
        PreparedStatement select;
        ResultSet resultat;
        ArrayList<Admin> admins=new ArrayList<>();
        
        try
        {
            select=cnx.prepareStatement("select * from admin");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                while(resultat.next())
            {
                Admin e=new Admin();
                //e.set(resultat.getInt("id"));
                e.setNom(resultat.getString("nom"));
                e.setPrenom(resultat.getString("prenom"));
                e.setEmail(resultat.getString("email"));
                e.setCin(resultat.getString("cin"));
                admins.add(e);
            }
            }
            else 
                return null;
            
        }
        catch(SQLException ex)
        {
            return null;
        }
        return admins;
    }
     public String  genererQrCode(Admin cit)
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
            //Logger.getLogger(GererCitoyenService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            //Logger.getLogger(GererCitoyenService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
      public String getAdmin(String cin)
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
