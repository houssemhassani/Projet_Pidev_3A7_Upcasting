package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entities.demande;
import Utils.DBConnexion;
import com.sun.xml.internal.bind.v2.model.core.ID;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.gererdemande;
import Entities.demande;
import Utils.api;

import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author hamza
 */
public class DemandeController implements Initializable {

   @FXML
    private TextField tfNUM;

    @FXML
    private TextField tfTYPE;

    @FXML
    private TextField tfDATE;

    private TextField tfCITOYEN;

    @FXML
    private ComboBox<String> tfSERVICE;

    @FXML
    private Button btnajouter;

   @FXML
    private Button btnmodifier;
    @FXML
    private TextField tfID;
      @FXML
    private TextField filterFiled;

    
    @FXML
    private TableView<demande> table_demande;
    @FXML
    private TableColumn<demande, Integer> col_ID;
   
    @FXML
    private TableColumn<demande, Integer> col_numero;

    @FXML
    private TableColumn<demande, String> col_type;

    @FXML
    private TableColumn<demande, String> col_date;

    @FXML
    private TableColumn<demande, Integer> col_citoyen;

    @FXML
    private TableColumn<demande, Integer> col_service;
     
   
    
    /**
     * Initializes the controller class.
     */
   
    ObservableList<demande> oblist;
     ObservableList<demande> data;
    int index =-1;
    Connection conn =null;
    PreparedStatement pst =null;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Label idcitoyen;
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView Exit;
    @FXML
    private Button gestiondemande;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuBack;
    @FXML
    private ImageView MenuClose;
    @FXML
    private AnchorPane slider;
    @FXML
    private Button btnprofil;
    @FXML
    private Button btnreclamation;
    @FXML
    private Button btnpublication;

   

    
    public void UpdateTable(){
    
       col_ID.setCellValueFactory(new PropertyValueFactory<demande,Integer>("id_demande"));  
       col_numero.setCellValueFactory(new PropertyValueFactory<demande,Integer>("num_demande"));
       col_type.setCellValueFactory(new PropertyValueFactory<demande,String>("type_demande"));
       col_date.setCellValueFactory(new PropertyValueFactory<demande,String>("date_demande"));
       col_citoyen.setCellValueFactory(new PropertyValueFactory<demande,Integer>("id_citoyen"));
       col_service.setCellValueFactory(new PropertyValueFactory<demande,Integer>("id_service"));
        oblist=DBConnexion.getDatademande();
        table_demande.setItems(oblist);
    
    
    
    
    
    
    
    
    
    }
    void search_demande(){
    col_ID.setCellValueFactory(new PropertyValueFactory<demande,Integer>("id_demande"));  
       col_numero.setCellValueFactory(new PropertyValueFactory<demande,Integer>("num_demande"));
       col_type.setCellValueFactory(new PropertyValueFactory<demande,String>("type_demande"));
       col_date.setCellValueFactory(new PropertyValueFactory<demande,String>("date_demande"));
       col_citoyen.setCellValueFactory(new PropertyValueFactory<demande,Integer>("id_citoyen"));
       col_service.setCellValueFactory(new PropertyValueFactory<demande,Integer>("id_service"));
    
    
       data=DBConnexion.getDatademande();
       table_demande.setItems(data);
       
       FilteredList<demande> filteredData = new FilteredList<>(data,b -> true);
       filterFiled.textProperty().addListener((ObservableList,oldValue,newValue)->{
           
       filteredData.setPredicate(demande->{
       if (newValue == null || newValue.isEmpty()){
       return true;
       }
       String lowerCaseFilter = newValue.toLowerCase();
       if(demande.getType_demande().indexOf(lowerCaseFilter)!=-1){
      return true ; // Filter matches password  
      }
           return false;
      
       
       }) ;        
           
       });
       
    SortedList<demande>sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(table_demande.comparatorProperty());
    table_demande.setItems(sortedData);
       
    
    
    
    
    
    
    
    
    }
    
    
    
    
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection conn =DBConnexion.connecterDB();
        ResultSet resultat;
        String sql="select * from service";
        ArrayList<String> services=new ArrayList<>();
        try {
            pst=conn.prepareStatement(sql);
            resultat=pst.executeQuery();
            while(resultat.next())
            {
                services.add(resultat.getString("nomService"));
            }
            
        } catch (Exception e) {
        }
        this.tfSERVICE.getItems().addAll(services);
      tfID.setEditable(false);
      UpdateTable(); 
      search_demande();
      
    
      
    }
    
   
    
    @FXML    
    void savedemande(ActionEvent event) {
    PreparedStatement p2;
    ResultSet resultat;
      Connection conn =DBConnexion.connecterDB();
      String sql="insert into demande(num_demande,type_demande,date_demande,id_citoyen,id_service)values(?,?,?,?,?)";
        try {
            
            pst =conn.prepareStatement(sql);
            int num_demande = Integer.parseInt(tfNUM.getText());  
            String type_demande =tfTYPE.getText();
            String date_demande =tfDATE.getText();
     int id_citoyen = Integer.parseInt(this.idcitoyen.getText());
    String sql2="select * from service where nomService='"+this.tfSERVICE.getValue().toString()+"'";
    p2=conn.prepareStatement(sql2);
    resultat=p2.executeQuery();
    int id = 0;
    while(resultat.next())
    {
        id=resultat.getInt("id");
    }
    // int id_service = Integer.parseInt(tfSERVICE.getText());
     pst.setString(1, tfNUM.getText());
     pst.setString(2, tfTYPE.getText());
     pst.setString(3, tfDATE.getText());
     pst.setInt(4, id_citoyen);
     pst.setInt(5, id);
     api ap= new api();
     ap.sms(this.col_numero.getText(),this.tfDATE.getText());
     pst.execute();
     JOptionPane.showMessageDialog(null, "demande ajoutée");
        UpdateTable(); 
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);        
        }
       
   
    
    }
    
           
    
    
   @FXML
    public void Edit(){
      
        try {
         
          Connection conn =DBConnexion.connecterDB();
         
            int value1 =Integer.parseInt(tfID.getText());
            
            
            String value3 =tfTYPE.getText();
            String value4 =tfDATE.getText();
            //String value5 =tfCITOYEN.getText();
           // String value6 =tfSERVICE.getValue().toString();
            
   String sql="update demande set type_demande='"+value3+"',date_demande='"+value4+"' where ID="+value1;        
           
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Update");
             UpdateTable(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        
    }
    
   
    
        
    }
    
    @FXML
   public void Delete(){
   
   Connection conn =DBConnexion.connecterDB();
   String sql ="delete from demande where ID=?";
       try {
           pst = conn.prepareStatement(sql);
           pst.setString(1, tfID.getText());
           pst.executeUpdate();
           JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
   
   
   }

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
         index =table_demande.getSelectionModel().getSelectedIndex();
    if (index<=-1){
    
    return;
    }
    tfID.setText(col_ID.getCellData(index).toString());
    tfNUM.setDisable(true);
    
    tfSERVICE.setDisable(true);
    tfNUM.setText(col_numero.getCellData(index).toString());
    tfTYPE.setText(col_type.getCellData(index).toString());
    tfDATE.setText(col_date.getCellData(index).toString());
   // tfCITOYEN.setText(col_citoyen.getCellData(index).toString());
   // tfSERVICE.setText(col_service.getCellData(index).toString());
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void gerernotification(ActionEvent event) {
        try {
            
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/sms.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    
    }
    
    


    @FXML
    private void nonselected(javafx.scene.input.MouseEvent event) {
        
        tfID.setText("");
    tfNUM.setDisable(false);
    
    tfSERVICE.setDisable(false);
    tfNUM.setText("");
    tfTYPE.setText("");
    tfDATE.setText("");
        
        
    }

    @FXML
    private void Gererdemande(ActionEvent event) {
    
     try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/demande.fxml"));
            Parent root = loader.load();
            DemandeController controller =  loader.getController();
            this.gestiondemande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void gererprofil(ActionEvent event) {
    
    
    
    
    }

    @FXML
    private void btnreclamation(ActionEvent event) {
        
        
    }

    @FXML
    private void gererpublication(ActionEvent event) {
        
        
        
        
        
        
    }

    
    
    
    }
  
    
   

   
    
    
    
    
    

     
        

   
    
     
     

     
     
     
         
        
    

