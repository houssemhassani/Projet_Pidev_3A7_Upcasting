/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Responsable;
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
public class ResponsableLoginService {
    private static Connection cnx=DBConnexion.getConnexion();
    
    public ResponsableLoginService() {
    }
    
    public  boolean modifierMotDePasse(String cin,String nouveau_mot_de_passe)
    {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Responsable cit=new Responsable();
        try {
            select=cnx.prepareStatement("Select * from employee where cin='"+cin+"' and role="+1);
            resultat=select.executeQuery();
            while (resultat.next()) {
            cit.setNom(resultat.getString("nom"));
            cit.setPrenom(resultat.getString("prenom"));
            cit.setEmail(resultat.getString("email"));
            cit.setCin(resultat.getString("cin"));
            cit.setMot_de_passe(resultat.getString("mot_de_passe"));
            }
            
                update=cnx.prepareStatement("Update admin set mot_de_passe='"+BCrypt.hashpw(nouveau_mot_de_passe,BCrypt.gensalt())+"' Where cin='"+cin+"'");
                update.executeUpdate();
                System.out.println("mot de passe modifiee");
            
           return true;
               // System.err.println("lancien mot de passe est incorrect");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());return false;
        }
        
        
         
    }
     public boolean modifierEmail(String cin,String email)
    {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Responsable cit=new Responsable();
        try {
            
            select=cnx.prepareStatement("Select * from employee where cin='"+cin+"'");
            resultat=select.executeQuery();
            if (resultat.isBeforeFirst())
            {
                while (resultat.next()) {
                    cit.setNom(resultat.getString("nom"));
                    cit.setPrenom(resultat.getString("prenom"));
                    cit.setEmail(resultat.getString("email"));
                    cit.setCin(resultat.getString("cin"));
                    cit.setMot_de_passe(resultat.getString("mot_de_passe"));
            }
               
                    update=cnx.prepareStatement("Update employee set email='"+email+"' Where cin='"+cin+"'");
                    update.executeUpdate();
                    JavaMailUtil.sendEmail(email, "Bienvenue Mr "+cit.getNom()+" "+cit.getPrenom()+"\n Login :"+cit.getCin());
                    System.out.println("email modifiee");
                    
                return true;
            }
            else
            {
                System.out.println("Compte introuvable");return false;}
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); return false;
        }
        
         
    }
     public  boolean seConnecter(String cin,String mot_de_passe)
     {
         PreparedStatement select;
         ResultSet resultat;
         Responsable cit=new Responsable();
        try {
            select=cnx.prepareStatement("Select *  from employee where cin='"+cin+"' and role="+0);
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
                {System.out.println("responsable connectee");
                return true;}
                else 
                {System.err.println("mdp incorrecte");return false;}
            }
            else
            { System.err.println("Tu n'est pas un responsable"); return false;}
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
     } 
}
