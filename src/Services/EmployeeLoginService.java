/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Employee;
import Entities.Responsable;
import Utils.DBConnexion;
import Utils.JavaMailUtil;
import impl.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class EmployeeLoginService {
    private static Connection cnx=DBConnexion.getConnexion();

    public EmployeeLoginService() {
    }
    public static boolean seConnecter(String cin,String mot_de_passe)
     {
         PreparedStatement select;
         ResultSet resultat;
         Responsable cit=new Responsable();
        try {
            select=cnx.prepareStatement("Select * from employee where cin='"+cin+"' and role="+1);
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
                {System.out.println("employee connectee");
                return true;}
                else 
                {System.err.println("mdp incorrecte");
                    return false;}
            }
            else
            {System.err.println("Tu n'est pas un employee");
            return false;}
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        
     }
   /* public static void modifierMotDePasse(String cin,String nouveau_mot_de_passe)
    {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Responsable cit=new Responsable();
        try {
            select=cnx.prepareStatement("Select * from employee where cin='"+cin+"'");
            resultat=select.executeQuery();
            while (resultat.next()) {
            cit.setNom(resultat.getString("nom"));
            cit.setPrenom(resultat.getString("prenom"));
            cit.setEmail(resultat.getString("email"));
            cit.setCin(resultat.getString("cin"));
            cit.setMot_de_passe(resultat.getString("mot_de_passe"));
            }
            if(BCrypt.checkpw(ancien_mot_de_passe,cit.getMot_de_passe()))
            {
                update=cnx.prepareStatement("Update employee set mot_de_passe='"+BCrypt.hashpw(nouveau_mot_de_passe,BCrypt.gensalt())+"' Where cin='"+cin+"'");
                update.executeUpdate();
                JavaMailUtil.sendEmail(cit.getEmail(),"Vous avez changée votre mot de passe \n Votre nouveau mot de passe est :"+nouveau_mot_de_passe);
                System.out.println("mot de passe modifiee");
            }
            else 
                System.err.println("lancien mot de passe est incorrect");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
         
    }*/
    public  boolean modifierMotDePasse(String cin,String nouveau_mot_de_passe)
    {
        try {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Employee cit=new Employee();
            select=cnx.prepareStatement("select * from employee where cin='"+cin+"'");
            resultat=select.executeQuery();
            while(resultat.next())
            {
                cit.setEmail(resultat.getString("email"));
            }
            update=cnx.prepareStatement("Update employee set mot_de_passe='"+BCrypt.hashpw(nouveau_mot_de_passe,BCrypt.gensalt())+"' Where cin='"+cin+"'");
            update.executeUpdate();
            JavaMailUtil.sendEmail(cit.getEmail(),"Vous avez changée votre mot de passe \n Votre nouveau mot de passe est :"+nouveau_mot_de_passe);   
            System.out.println("mot de passe modifiee");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeLoginService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
                    JavaMailUtil.sendEmail(cit.getEmail(),"Vous avez changée votre email !!  \n Votre nouvelle email est :"+email);
                    JavaMailUtil.sendEmail(email, "Bienvenue dans E-Citoyen avec votre nouvelle email ");
                    System.out.println("email modifiee");
               
                    return true;
                }else
            {
                return false;
            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); return false;
        }
    }
    
    
}
