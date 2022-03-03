/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_pidev;

import Entities.Citoyen;
import Entities.Employee;
import Entities.Message;
import Entities.Responsable;
import Services.EmployeeLoginService;
import Services.GererCitoyenService;
import Services.GererEmployeeService;
import Services.GererMessageService;
import Services.GererResponsableService;
import Services.MotDePasseOublieeService;
import Services.ResponsableLoginService;
import Utils.smsSerned;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class Project_Pidev   extends  Application{

    /**
     * @param args the command line arguments
     */
    @Override
    public void start(Stage primaryStage) { 
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/Views/ChoixUtilisateur.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("choix!");
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("choix");
        } 
        catch (IOException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args); 
       
        
    }
   /* public static void main(String[] args) {
        // TODO code application logic here
        MotDePasseOublieeService o=new MotDePasseOublieeService();
        
        System.out.println(o.motDePasseOublieee("houssemhassanii@gmail.com","06996868"));
        // CitoyenLoginService.inscrire("hassani","houssem","houssemhassanii@gmail.com","0699446869",92144965, "Monastir","photo","123456");
        //CitoyenLoginService.seConnecter("06996868", "123456");
        //CitoyenLoginService.modifierMotDePasse("06996868","123456", "1234567");
        //CitoyenLoginService.modifierPhoto("06996868","1234567","hhhhsddhh");
        //AdminLoginService.modifierEmail("06996868", "mot_de_passe", "email");
        //System.out.println( BCrypt.hashpw("mot_de_passe",BCrypt.gensalt()));
        //AdminLoginService.seConnecter("123456", "mot_de_passe");
        //AdminLoginService.modifierMotDePasse("123456", "mot_de_passe", "nouveau_mot_de_passe");
        //AdminLoginService.seConnecter("123456", "nouveau_mot_de_passe");
        //Responsable res=new Responsable("Hassani","Houssem","houssemhassanii@gmail.com","06996868",1,"mot_de_passe");
        //GererResponsableService.ajouterResponsable(res);
       // GererResponsableService.supprimerResponsable("06996868");
        //ResponsableLoginService.seConnecter("06996868", "nouveau_mot_de_passe");
        //ResponsableLoginService.modifierEmail("06996868", "mot_de_passe","houssem.hassani@esprit.tn");
        //ResponsableLoginService.modifierMotDePasse("06996868", "nouveau_mot_de_passe", "nouveau_mot_de_passe");
        //GererResponsableService.modifierServiceDeResponsable("06996868", 2);
        //GererResponsableService.supprimerResponsable("1234567778");
        //Employee e =new Employee("houssem", "hassani", "houssemhassanii@gmail.com", "069968868", 1, 1, "mot_de_passe");
        //GererEmployeeService.ajouterEmployee(e);
        //EmployeeLoginService.seConnecter("06996868", "mot_de_passe");
        //EmployeeLoginService.modifierEmail("06996868", "mot_de_passe", "houssem.hassani@esprit.tn");
        //EmployeeLoginService.modifierMotDePasse("06996868", "mot_de_passe", "nouveau_mot_de_passe");
        //GererEmployeeService.modifierEquipeEmployee("06996868", 2);
        //GererEmployeeService.modifierServiceEmployee("1235", 2);
        //GererEmployeeService.supprimerEmployee("06996868");
        /*ArrayList<Employee> empeq=GererEmployeeService.getEmployeesEquipe(2);
        ArrayList<Employee> empser=new ArrayList<Employee>();
        empser=GererEmployeeService.getEmployeesService(2);
        if(empser==null)
            System.err.println("Aucun employee appratient a ce service pour le moment");
        else
        {
            for (Employee employee : empser) {
            System.out.println(employee.toString()+"\n");
            }
        }*/
       // EmployeeLoginService.seConnecter("124435", "mot_de_passe");
        /*smsSerned s=new smsSerned();
        s.sendSms();
        //GererMessageService.envoyerMessage("message1",9,8);
        //GererMessageService.envoyerMessage("message2", 9, 8);
        ArrayList<Message> m=new ArrayList<>();
        m=GererMessageService.getDiscussion(9,8);
        for (Message message : m) {
            System.out.println("\n "+message.toString());
        }
    }
    
}*/
}

