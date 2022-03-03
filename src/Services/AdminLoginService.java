/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Admin;
import Entities.Citoyen;
import Utils.DBConnexion;
import impl.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class AdminLoginService {
    private static Connection cnx= DBConnexion.getConnexion();

    public AdminLoginService() {
    }
    
    public static void modifierMotDePasse(String cin,String nouveau_mot_de_passe)
    {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Citoyen cit=new Citoyen();
        try {
            select=cnx.prepareStatement("Select * from admin where cin='"+cin+"'");
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
            
           
               // System.err.println("lancien mot de passe est incorrect");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
         
    }
     public static void modifierEmail(String cin,String mot_de_passe,String email)
    {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Admin admin=new Admin();
        try {
            
            select=cnx.prepareStatement("Select * from admin where cin='"+cin+"'");
            resultat=select.executeQuery();
            while (resultat.next()) {
            admin.setNom(resultat.getString("nom"));
            admin.setPrenom(resultat.getString("prenom"));
            admin.setEmail(resultat.getString("email"));
            admin.setCin(resultat.getString("cin"));
            admin.setMot_de_passe(resultat.getString("mot_de_passe"));
            }
            if(BCrypt.checkpw(mot_de_passe,admin.getMot_de_passe()))
            {
                update=cnx.prepareStatement("Update admin set email='"+email+"' Where cin='"+cin+"'");
                update.executeLargeUpdate();
                System.out.println("email modifiee");
            }
            else
            {
                System.err.println(" mot de passe est incorrect");
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        catch (NullPointerException e) {
            System.err.println(e.getMessage());
            
        } 
        
         
    }
     public  boolean seConnecter(String cin,String mot_de_passe)
     {
         PreparedStatement select;
         ResultSet resultat;
         Admin cit=new Admin();
        try {
            select=cnx.prepareStatement("Select * from admin where cin='"+cin+"'");
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
                {System.out.println("admin connectee");
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
     public String RechercherUsertByName(String p) throws SQLException {
        try {
            PreparedStatement pt;
            String query = "select password from user where username='"+p+"'";
            pt=cnx.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
        String res;
            if (rs.next()) {
               
                
                res=rs.getString(1);
               
                return res;
            }}
            catch (SQLException ex) {
               // System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return null;
        

     
     
     
     }
    
}
