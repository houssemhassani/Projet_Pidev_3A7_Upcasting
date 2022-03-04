/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DBConnexion;
import Utils.JavaMailUtil;
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
public class MotDePasseOublieeService {
    private static Connection cnx=DBConnexion.getConnexion();

    public MotDePasseOublieeService() {
    }
    
    public String motDePasseOublieeCitoyen(String cin)
    {
        try {
            System.out.println(cin);
            ResultSet resultat;
            PreparedStatement select;
            select=cnx.prepareStatement("select email from citoyen where cin='"+cin+"'");
            resultat=select.executeQuery();
            String s=null;
            while(resultat.next())
            {
                System.out.println(resultat.getString("email"));
                s=this.reandomCode();
                JavaMailUtil.sendEmail(resultat.getString("email"),"Vérification de mot de passe oubliée \n "+ "Le code de vérification : "+s);   
            }
            return s;
            
        } catch (SQLException ex) {
            //Logger.getLogger(MotDePasseOublieeService.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
            return null;
        }
    } 
    public String motDePasseOublieeResponsable(String cin)
    {
        try {
            System.out.println(cin);
            ResultSet resultat;
            PreparedStatement select;
            select=cnx.prepareStatement("select email from employee where cin='"+cin+"' and role="+0);
            resultat=select.executeQuery();
            String s="non";
            while(resultat.next())
            {
                System.out.println(resultat.getString("email"));
                s=this.reandomCode();
                JavaMailUtil.sendEmail(resultat.getString("email"),"Vérification de mot de passe oubliée \n "+ "Le code de vérification : "+s);   
            }
            return s;
            
        } catch (SQLException ex) {
            //Logger.getLogger(MotDePasseOublieeService.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
            return null;
        }
    }
    public String motDePasseOublieeEmployee(String cin)
    {
        try {
            System.out.println(cin);
            ResultSet resultat;
            PreparedStatement select;
            select=cnx.prepareStatement("select * from employee where cin='"+cin+"' and role="+1);
            resultat=select.executeQuery();
            String s=null;
            while(resultat.next())
            {
                System.out.println(resultat.getString("email"));
                s=this.reandomCode();
                JavaMailUtil.sendEmail(resultat.getString("email"),"Vérification de mot de passe oubliée \n "+ "Le code de vérification : "+s);   
            }
            return s;
            
        } catch (SQLException ex) {
            //Logger.getLogger(MotDePasseOublieeService.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
            return null;
        }
    }
    public String motDePasseOublieeAdmin(String cin)
    {
        try {
            System.out.println(cin);
            ResultSet resultat;
            PreparedStatement select;
            select=cnx.prepareStatement("select email from admin where cin='"+cin+"'");
            resultat=select.executeQuery();
            String s=null;
            while(resultat.next())
            {
                System.out.println(resultat.getString("email"));
                s=this.reandomCode();
                JavaMailUtil.sendEmail(resultat.getString("email"),"Vérification de mot de passe oubliée \n "+ "Le code de vérification : "+s);   
            }
            return s;
            
        } catch (SQLException ex) {
            //Logger.getLogger(MotDePasseOublieeService.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
            return null;
        }
    }
   private String reandomCode()
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(8);
  
        for (int i = 0; i < 8; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
        
    }

}
