/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Citoyen;
import Utils.DBConnexion;
import Utils.JavaMailUtil;
import impl.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Asus
 */
public class CitoyenLoginService {
    private static Connection cnx=DBConnexion.getConnexion();
    
    
    public CitoyenLoginService(){}
    public static boolean inscrire(Citoyen citoyen)
    {
         PreparedStatement select;
         ResultSet resultat;
         PreparedStatement insert;
        try {
            //Citoyen citoyen=new Citoyen(nom, prenom, email, cin, num_tel, ville,photo ,mot_de_passe);
            select=cnx.prepareStatement("select * from citoyen where cin=?");
            select.setString(1, citoyen.getCin());
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                System.err.println("Utilisateur existe");
                return false;
            }
            else
            {
                insert=cnx.prepareCall("INSERT INTO citoyen(nom,prenom,email,cin,num_tel,ville,mot_de_passe) VALUES(?,?,?,?,?,?,?)");
                insert.setString(1,citoyen.getNom());
                insert.setString(2,citoyen.getPrenom());
                insert.setString(3,citoyen.getEmail());
                insert.setString(4,citoyen.getCin());
                insert.setLong(5,citoyen.getNum_tel());
                insert.setString(6,citoyen.getVille());
                
                insert.setString(7,BCrypt.hashpw(citoyen.getMot_de_passe(),BCrypt.gensalt()));
                insert.executeUpdate();
                
                    JavaMailUtil.sendEmail(citoyen.getEmail(), "Bienvenue sur E-Citoyen \n"+" Attendez la confimation de l'Admin pour accéder à notre application \n Vous serez notifiés via une mail de confirmation \n Merci !! ");
                   
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        catch (Exception exx) {
            System.err.println(exx.getMessage());
            return false;
            
        }
        
        System.out.println("citoyen ajoutee");
        return  true;
    }
    public  boolean modifierMotDePasse(String cin,String nouveau_mot_de_passe)
    {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Citoyen cit=new Citoyen();
        try {
            select=cnx.prepareStatement("Select * from citoyen where cin='"+cin+"'");
            resultat=select.executeQuery();
            while (resultat.next()) {
            cit.setNom(resultat.getString("nom"));
            cit.setPrenom(resultat.getString("prenom"));
            cit.setEmail(resultat.getString("email"));
            cit.setCin(resultat.getString("cin"));
            cit.setMot_de_passe(resultat.getString("mot_de_passe"));
            }
            
                update=cnx.prepareStatement("Update citoyen set mot_de_passe='"+BCrypt.hashpw(nouveau_mot_de_passe,BCrypt.gensalt())+"' Where cin='"+cin+"'");
                update.executeUpdate();
                System.out.println("mot de passe modifiee");
                return true;
            
            
                
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return  false;
        }
        
         
    }
     public boolean modifierEmail(String cin,String photo)
    {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Citoyen cit=new Citoyen();
        try {
            
            select=cnx.prepareStatement("Select * from citoyen where cin='"+cin+"'");
            resultat=select.executeQuery();
            while (resultat.next()) {
            cit.setNom(resultat.getString("nom"));
            cit.setPrenom(resultat.getString("prenom"));
            cit.setEmail(resultat.getString("email"));
            cit.setCin(resultat.getString("cin"));
            cit.setMot_de_passe(resultat.getString("mot_de_passe"));
            }
            
                update=cnx.prepareStatement("Update citoyen set email='"+photo+"' Where cin='"+cin+"'");
                update.executeUpdate();
                System.out.println("photo modifiee");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        
         
    }
     public  boolean seConnecter(String cin,String mot_de_passe)
     {
          PreparedStatement select;
         ResultSet resultat;
         Citoyen cit=new Citoyen();
        try {
            select=cnx.prepareStatement("Select * from citoyen where cin='"+cin+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                while (resultat.next()) {
                    cit.setNom(resultat.getString("nom"));
                    cit.setPrenom(resultat.getString("prenom"));
                    cit.setEmail(resultat.getString("email"));
                    cit.setCin(resultat.getString("cin"));
                    cit.setMot_de_passe(resultat.getString("mot_de_passe"));
                }
                if(BCrypt.checkpw(mot_de_passe,cit.getMot_de_passe()))
                {System.out.println("citoyen connectee");
                return true;}
                else 
                {System.err.println("mdp incorrecte");
                    return false;}
            }
            else
            {System.err.println("Tu n'est pas un citoyen");
            return false;}
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
     }
    
}
