 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import Entites.Commentaire;
import Entites.Publication;
import Services.GererCommentaireService;
import Services.GererPublicationService;
import Utils.DBConnexion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.applet.Main;

/**
 *
 * @author Ghassen-pc
 */
public class Projet extends Application{

    @Override
    public void start(Stage primaryStage) { 
        
        try {
            Parent root = null;
          //  System.out.println(root.toString());
            root = FXMLLoader.load(Main.class.getResource("/Views/publications.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("choix!");
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("choix");
        } catch (IOException ex) {
            System.err.println(ex.getMessage().toString());
        }
        
        
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args); 
    }
        //GererPublicationService.modifierStatusPublication(1, "status");
        //GererPublicationService.modifierPhotoPublication(1, "photooooooooooooooo");
        //GererPublicationService.supprimerPublication(1);
        //GererCommentaireService.ajouterCitoyenCommentaire("zezaezae", 1, 1);
        //GererCommentaireService.modifierContentCommentaire(1, "eeeeee");
        //GererCommentaireService.supprimerCommentaire(1);
        /*ArrayList<Commentaire> pubs=new ArrayList<>();
                //pubs=GererPublicationService.getPublicationsConfirmee();
                pubs=GererCommentaireService.getCommentaires();
        if(pubs.isEmpty())
            System.err.println("Emptyyyyy!!!");
        else
        {
            pubs.forEach((e) -> {
                System.out.println(e.toString()+"\n");
            });
        }*/
        
    }
    

