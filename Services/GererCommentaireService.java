/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entites.Commentaire;
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
public class GererCommentaireService {
     private static Connection cnx=DBConnexion.getConnexion();

    public GererCommentaireService() {
    }
    public static void ajouterCitoyenCommentaire(Commentaire c)
    {
        
         ResultSet resultat;
         PreparedStatement insert;
         try {
            insert=cnx.prepareStatement("insert into commantaire (content,id_publication,id_citoyen) values(?,?,?)");
            insert.setString(1, c.getContent());
            insert.setInt(2, c.getId_publication());
            insert.setInt(3,c.getId_citoyen() );
            insert.executeUpdate();
             System.out.println("Commentaire Citoyen ajoutee");
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
        
    }
    public  void ajouterEmployeeCommentaire(Commentaire c)
    {
        
         ResultSet resultat;
         PreparedStatement insert;
         try {
            insert=cnx.prepareStatement("insert into commantaire (content,id_publication,id_employee) values(?,?,?)");
            insert.setString(1, c.getContent());
            insert.setInt(2, c.getId_publication());
            insert.setInt(3,c.getId_citoyen());
            insert.executeUpdate();
             System.out.println("Commentaire Employee ajoutee");
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
        
    }
    public static void modifierContentCommentaire(Commentaire c)
    {
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;
        try {
            select=cnx.prepareStatement("select * from commantaire where id ="+c.getId());
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("update commantaire set content='"+c.getContent()+"'");
                update.executeUpdate();
                System.out.println("content modifiee");
            }
            else
                System.err.println("Commentaire introuvable");
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void supprimerCommentaire(int id)
    {
        PreparedStatement  delete;
        PreparedStatement select;
        ResultSet resultat;
        try {
            select=cnx.prepareStatement("select * from commantaire where id="+id);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                delete=cnx.prepareStatement("delete from commantaire where id="+id);
                delete.executeUpdate();
                System.out.println("commantaire supprimeeeee");
            }
            else
                System.err.println("commantaire non trouvable");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
      public  ArrayList<Commentaire>  getCommentaires(Publication p)
    {
        PreparedStatement select;
        PreparedStatement select2;
        ResultSet resultat2;
        ResultSet resultat;
        ArrayList<Commentaire> pubs=new ArrayList<>();
        
       
        try {
            select=cnx.prepareStatement("select * from commantaire where id_publication="+p.getId());
            //select2=cnx.prepareStatement("select * from commantaire inner join employee on commantaire.id_employee=employee.id    ");
           
            resultat=select.executeQuery();
           // resultat2=select2.executeQuery();
            while(resultat.next())
            {
                Commentaire pub=new Commentaire();
               
                pub.setContent(resultat.getString("content"));
            
               
                pub.setId_citoyen(resultat.getInt("id_citoyen"));
                System.out.println(pub.toString());
               // pub.setNom_user(resultat.getString("nom")+" "+resultat.getString("prenom"));
                pubs.add(pub);
                
            }
             /*while(resultat2.next())
            {
                Commentaire pub=new Commentaire();
              
                
                pub.setContent(resultat2.getString("content"));
                pub.setId(resultat2.getInt("id_publication"));
                pub.setNom_user(resultat2.getString("nom")+" "+resultat2.getString("prenom"));
                pubs.add(pub);
                
            }*/
           
            
        } catch (SQLException e) {
            return null;
        }
         return pubs;
    }
}
