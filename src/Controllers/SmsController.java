/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Utils.api;
import static java.lang.ProcessBuilder.Redirect.to;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import static jdk.nashorn.internal.objects.NativeJava.to;
/**
 * FXML Controller class
 *
 * @author hamza
 */
public class SmsController implements Initializable {

    @FXML
    private TextField tftext;
    @FXML
    private TextField tfnum;
    @FXML
    private Button btnok;
    @FXML
    private ComboBox  comb;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          ObservableList<String> list=FXCollections.observableArrayList("demande traitée","demande cours de traitement","demande non traitée");
      comb.setItems(list);
       
    }    

    @FXML
    private void envoyer(ActionEvent event) {

     api ap= new api();
     ap.sms(this.tfnum.getText(),this.tftext.getText());


    }

    @FXML
    void Select(ActionEvent event) {

        String s =comb.getSelectionModel().getSelectedItem().toString();
        label.setText(s);
        
        
        
    }
    
}
