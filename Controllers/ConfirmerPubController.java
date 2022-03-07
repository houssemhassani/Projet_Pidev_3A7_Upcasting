/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entites.Publication;
import Services.GererPublicationService;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumnBuilder;
import javafx.scene.control.TableView;
import javafx.scene.control.TableViewBuilder;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ghassen-pc
 */
public class ConfirmerPubController implements Initializable {

    @FXML
    private CheckBox myCheckBox;
    @FXML
    private ImageView myImageView;
static String s;
 int i=0;
    
    Image myImage1= new Image(getClass().getResourceAsStream("/images/lampe.png"));
    Image myImage2= new Image(getClass().getResourceAsStream("/images/lampe2.png"));
    
    @FXML
    private Label MyLabel;
    String[] publication ={"publication1","publication2","publication3"};
    String currentPub;
    private TableColumn<Publication, Integer> id;
    private TableColumn<Publication, String> status;
    private TableColumn<Publication, Image> image;
    private TableColumn<Publication, String> nomcitoyen;
    private TableView<Publication> publicationss;
    public TableColumn<Publication, String >image1;
    @FXML
    private Button Delete;
    @FXML
    private ListView<Publication> ListViewdata;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        GererPublicationService gr = new GererPublicationService();
        ListViewdata.getItems().addAll(gr.getPublicationsNonConfirmee());
        
            
 //this.image.setCellValueFactory(new PropertyValueFactory<Publication,Image>("image"));
      //publicationss.setItems(observablelist);  
        // TODO
    }
         

    @FXML
    private void change(ActionEvent event) {
        
        if(myCheckBox.isSelected()==true){
             final Publication selectedIdx = ListViewdata.getSelectionModel().getSelectedItem();
             myImageView.setImage(myImage1);
            if (selectedIdx != null) {
                Publication itemToRemove = ListViewdata.getSelectionModel().getSelectedItem();
                

                

                boolean players = ListViewdata.getItems().remove(selectedIdx);
                ListViewdata.getSelectionModel().select(selectedIdx);
                //removes the player for the array
                System.out.println("selectIdx: " + selectedIdx);
                System.out.println("item: " + itemToRemove);
                GererPublicationService gr = new GererPublicationService();
                System.out.println(selectedIdx.toString());
                gr.ConfirmerPublication(selectedIdx);
                ListViewdata.refresh();
            
            
            
        } 
        else {
            
            myImageView.setImage(myImage2);
        }
    }}

    @FXML
    private void btnDelete(ActionEvent event) {
           
       final Publication selectedIdx = ListViewdata.getSelectionModel().getSelectedItem();
            if (selectedIdx != null) {
                Publication itemToRemove = ListViewdata.getSelectionModel().getSelectedItem();

                

                boolean players = ListViewdata.getItems().remove(selectedIdx);
                ListViewdata.getSelectionModel().select(selectedIdx);
                //removes the player for the array
                System.out.println("selectIdx: " + selectedIdx);
                System.out.println("item: " + itemToRemove);
                GererPublicationService gr = new GererPublicationService();
                System.out.println(selectedIdx.toString());
                gr.supprimerPublication(selectedIdx.getId());
                ListViewdata.refresh();
    }
    }
    
}
