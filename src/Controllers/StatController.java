/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.GererAdminService;
import Services.GererCitoyenService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author lv
 */
public class StatController implements Initializable {

    @FXML
    private PieChart pie;
    @FXML
    private Hyperlink retour;
    @FXML
    private Label cinlabel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int i,j,k;
        GererCitoyenService a = new GererCitoyenService();
        i= a.countCitoyen();
        j=a.countCitoyenConfirme();
        k= a.countCitoyenNonConfirmee();
        ObservableList<PieChart.Data> pieE=
             FXCollections.observableArrayList(
             new PieChart.Data("Tous Citoyens", i),
             new PieChart.Data("Citoyens Confirmes", j),
             new PieChart.Data("Citoyens Non Confirmee", k)
        );
        
        pie.setData(pieE);
        
// TODO
    }    

    @FXML
    private void retour(ActionEvent event) {
         try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Citoyens.fxml"));
            Parent root = loader.load();
            CitoyensController controller =  loader.getController();
            this.retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
