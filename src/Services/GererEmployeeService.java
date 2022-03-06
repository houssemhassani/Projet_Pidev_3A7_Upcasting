/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Employee;
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
public class GererEmployeeService {
    private static Connection cnx=DBConnexion.getConnexion();

   
    public  boolean ajouterEmployee(Employee e)
    {
        PreparedStatement select;
        PreparedStatement insert;
        ResultSet resultat;
        if(e==null)
        {
            System.err.println("employee vide");
            return  false;
        }
        
        else 
        {
            try {
            select=cnx.prepareStatement("select * from employee where cin='"+e.getCin()+"'");
            resultat = select.executeQuery();
            if(!resultat.isBeforeFirst())
            {
                insert=cnx.prepareStatement("insert into employee (nom,prenom,email,cin,role,mot_de_passe) values(?,?,?,?,?,?)");
                insert.setString(1, e.getNom());
                insert.setString(2, e.getPrenom());
                insert.setString(3, e.getEmail());
                insert.setString(4, e.getCin());
                insert.setInt(5, 1);
                String mdp=BCrypt.hashpw(e.getMot_de_passe(), BCrypt.gensalt());
                insert.setString(6, mdp);
                insert.executeUpdate();
                JavaMailUtil.sendEmail(e.getEmail(), "Bienvenue dans notre application E-Citoyen \n Vous êtes um employée \n Login : "+e.getCin()+"\n Mot de passe "+e.getMot_de_passe());
                System.out.println("employee ajoutee");
                return true;
            }
            else
            {System.err.println("employee existe"); return false;}
        } 
            catch(SQLException ex)
            {
                System.err.println(ex.getMessage());
                return false;
            }
        }
    }
    public  void modifierEquipeEmployee(String cin, int equipe_id)
    {
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;
        try
        {
            select=cnx.prepareStatement("select * from employee where cin='"+cin+"'");
            resultat=select.executeQuery();
            Employee e=new Employee();
            while (resultat.next())
            {
                e.setEmail(resultat.getString("email"));
            }
                
            if(!resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("update employee set equipe_id="+equipe_id+" where cin='"+cin+"'");
                update.executeUpdate();
                JavaMailUtil.sendEmail(e.getEmail(), "Bienvenue dans notre application E-Citoyen \n Votre équipe a été changée \n Voici votre nouvelle équipe:"+equipe_id);
                System.out.println("equipe modifiee pour cet employee");
            }
            else
                System.err.println("employee non trouvee");
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public static void modifierServiceEmployee(String cin, int service_id)
    {
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;
        try
        {
            select=cnx.prepareStatement("select * from employee where cin='"+cin+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("update employee set service_id="+service_id+" where cin='"+cin+"'");
                update.executeLargeUpdate();
                System.out.println("equipe modifiee pour cet employee");
            }
            else
                System.err.println("employee non trouvee");
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public  boolean supprimerEmployee(Employee cit)
    {
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;
        try
        {
            select=cnx.prepareStatement("select * from employee where cin='"+cit.getCin()+"' and role="+1);
            resultat=select.executeQuery();
            Employee e=new Employee();
            while (resultat.next())
                e.setEmail(resultat.getString("email"));
            if(e.getEmail()!=null)
            {
                update=cnx.prepareStatement("delete from employee where cin='"+cit.getCin()+"'");
                update.executeUpdate();
                JavaMailUtil.sendEmail(cit.getEmail(),"l'Admin vous a retirer \n Vous ne pouvez pas accéder à notre application \n merci d'être compréhensif");
                System.out.println("employee supprimee avec succeess");
                return true;
            }
            else
            { System.err.println("employee non trouvee");
            return false;}
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
            return false;
        }
    }
    public static ArrayList<Employee> getEmployeesService(int service_id)
    {
        PreparedStatement select;
        ResultSet resultat;
        ArrayList<Employee> emps=new ArrayList<>();
        
        try
        {
            select=cnx.prepareStatement("select * from employee where service_id="+service_id);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                while(resultat.next())
            {
                Employee e=new Employee();
                e.setId(resultat.getInt("id"));
                e.setNom(resultat.getString("nom"));
                e.setPrenom(resultat.getString("prenom"));
                e.setEmail(resultat.getString("email"));
                e.setCin(resultat.getString("cin"));
                
                e.setService_id(resultat.getInt("service_id"));
                e.setEquipe_id(resultat.getInt("equipe_id"));
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
    public static ArrayList<Employee> getEmployeesEquipe(int equipe_id)
    {
        PreparedStatement select;
        ResultSet resultat;
        ArrayList<Employee> emps=new ArrayList<>();
        
        try
        {
            select=cnx.prepareStatement("select * from employee where equipe_id="+equipe_id);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                while(resultat.next())
            {
                Employee e=new Employee();
                e.setId(resultat.getInt("id"));
                e.setNom(resultat.getString("nom"));
                e.setPrenom(resultat.getString("prenom"));
                e.setEmail(resultat.getString("email"));
                e.setCin(resultat.getString("cin"));
                e.setPhoto(resultat.getString("photo"));
                
                e.setService_id(resultat.getInt("service_id"));
                e.setEquipe_id(resultat.getInt("equipe_id"));
                emps.add(e);
            }
            }
            else 
                emps= null;
            
        }
        catch(SQLException ex)
        {
            return null;
        }
        
        return emps;
    }
    public  ArrayList<Employee> getEmployees()
    {
        PreparedStatement select;
        ResultSet resultat;
        ArrayList<Employee> emps=new ArrayList<>();
        
        try
        {
            select=cnx.prepareStatement("select * from employee where role="+1);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                while(resultat.next())
            {
                Employee e=new Employee();
                e.setId(resultat.getInt("id"));
                e.setNom(resultat.getString("nom"));
                e.setPrenom(resultat.getString("prenom"));
                e.setEmail(resultat.getString("email"));
                e.setCin(resultat.getString("cin"));
                emps.add(e);
            }
            }
            else 
                emps= null;
            
        }
        catch(SQLException ex)
        {
            return null;
        }
        
        return emps;
    }
    public String  genererQrCode(Employee cit)
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
     public String getEmployee(String cin)
     {
         String nom=null;
        try {
            PreparedStatement select;
            ResultSet resultat;
            select=cnx.prepareStatement("select * from employee where cin='"+cin+"'");
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
