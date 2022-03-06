/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.demande;
import Utils.DBConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.Document;
import services.gererdemande;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class DemanderesponsableController implements Initializable {
     ObservableList<demande> oblist;
     ObservableList<demande> data;
    int index =-1;
    Connection conn =null;
    PreparedStatement pst =null;

    @FXML
    private TextField tfNUM;
    @FXML
    private TextField tfTYPE;
    @FXML
    private TextField tfDATE;
    @FXML
    private Button bttraitee;
    @FXML
    private Button btnnontraitee;
    @FXML
    private TextField tfID;
    @FXML
    private Button btnencours;
    @FXML
    private ComboBox<String> tfSERVICE;
    @FXML
    private Label idresponsable;
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
    private TextField filterFiled;
    @FXML
    private TableColumn<demande, String> etat;
    @FXML
    private Button btncalculer;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuBack;
    @FXML
    private ImageView MenuClose;
    @FXML
    private ImageView Exit;
    @FXML
    private AnchorPane slider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conn =DBConnexion.connecterDB();
            PreparedStatement pst2;
          
            ResultSet resultat;
            ResultSet resultat2;
            
            String sql2="select * from employee where cin='"+this.idresponsable.getText()+"'";
            pst2=conn.prepareStatement(sql2);
            resultat2=pst2.executeQuery();
            int service_id=0;
            while(resultat2.next())
            {
                service_id=resultat2.getInt("service_id");
                
            }
            String sql="select * from demande where id_service="+service_id;
            ArrayList<demande> services=new ArrayList<>();
            try {
                pst=conn.prepareStatement(sql);
                resultat=pst.executeQuery();
                while(resultat.next())
                {
                    demande d=new demande();
                    d.setId_demande(resultat.getInt("ID"));
                    d.setType_demande(resultat.getString("type_demande"));
                    d.setNum_demande(resultat.getInt("num_demande"));
                    d.setId_citoyen(resultat.getInt("id_citoyen"));
                    d.setDate_demande(resultat.getString("date_demande"));
                    d.setEtat(resultat.getString("etat"));
                    services.add(d);
                }
                
            } catch (Exception e) {
            }
            ObservableList observableList=FXCollections.observableArrayList(services);
            table_demande.setItems(observableList);
            col_ID.setCellValueFactory(new PropertyValueFactory<demande,Integer>("id_demande"));  
       col_numero.setCellValueFactory(new PropertyValueFactory<demande,Integer>("num_demande"));
       col_type.setCellValueFactory(new PropertyValueFactory<demande,String>("type_demande"));
       col_date.setCellValueFactory(new PropertyValueFactory<demande,String>("date_demande"));
       col_citoyen.setCellValueFactory(new PropertyValueFactory<demande,Integer>("id_citoyen"));
       etat.setCellValueFactory(new PropertyValueFactory<demande,String>("etat"));
            
            tfID.setEditable(false);
           // UpdateTable();
            //search_demande();
            // TODO
        } catch (SQLException ex) {
             Logger.getLogger(DemanderesponsableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void UpdateTable(){
    
       col_ID.setCellValueFactory(new PropertyValueFactory<demande,Integer>("id_demande"));  
       col_numero.setCellValueFactory(new PropertyValueFactory<demande,Integer>("num_demande"));
       col_type.setCellValueFactory(new PropertyValueFactory<demande,String>("type_demande"));
       col_date.setCellValueFactory(new PropertyValueFactory<demande,String>("date_demande"));
       col_citoyen.setCellValueFactory(new PropertyValueFactory<demande,Integer>("id_citoyen"));
       etat.setCellValueFactory(new PropertyValueFactory<demande,String>("etat"));
        oblist=DBConnexion.getDatademande();
        table_demande.setItems(oblist);
    
    
    
    
    
    
    
    
    
    }
    void search_demande(){
    col_ID.setCellValueFactory(new PropertyValueFactory<demande,Integer>("id_demande"));  
       col_numero.setCellValueFactory(new PropertyValueFactory<demande,Integer>("num_demande"));
       col_type.setCellValueFactory(new PropertyValueFactory<demande,String>("type_demande"));
       col_date.setCellValueFactory(new PropertyValueFactory<demande,String>("date_demande"));
       col_citoyen.setCellValueFactory(new PropertyValueFactory<demande,Integer>("id_citoyen"));
       etat.setCellValueFactory(new PropertyValueFactory<demande,String>("etat"));
    
    
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

    @FXML
    private void getSelected(MouseEvent event) {
    }

    private void demandetraitee(ActionEvent event) {
        ObservableList<demande>r,f;
        f=this.table_demande.getItems();
        r=this.table_demande.getSelectionModel().getSelectedItems();
        for(demande d:r)
        {
            try {
                PreparedStatement pst;
                String etat="traitée";
                String sql="update demande set etat='"+etat+"' where ID="+d.getId_demande();
                pst=conn.prepareStatement(sql);
                pst.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(DemanderesponsableController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        UpdateTable();
    }

    @FXML
    private void demandenontraitee(ActionEvent event) {
        ObservableList<demande>r,f;
        f=this.table_demande.getItems();
        r=this.table_demande.getSelectionModel().getSelectedItems();
        for(demande d:r)
        {
            try {
                PreparedStatement pst;
                String etat="non traitée";
                String sql="update demande set etat='"+etat+"' where ID="+d.getId_demande();
                pst=conn.prepareStatement(sql);
                pst.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(DemanderesponsableController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        UpdateTable();
    }

    @FXML
    private void demandeencours(ActionEvent event) {
        ObservableList<demande>r,f;
        f=this.table_demande.getItems();
        r=this.table_demande.getSelectionModel().getSelectedItems();
        for(demande d:r)
        {
            try {
                PreparedStatement pst;
                String etat="en cours";
                String sql="update demande set etat='"+etat+"' where ID="+d.getId_demande();
                pst=conn.prepareStatement(sql);
                pst.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(DemanderesponsableController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        UpdateTable();
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
    private void resultat(ActionEvent event) {
        
        int a,z,e;
        gererdemande gd = new gererdemande();
        
        a=gd.countDemEncours();
        z=gd.countDemTr();
        e=gd.countDemNtr();
        
        JOptionPane.showMessageDialog(null, "demande traitée : "+z + "\n"+"demande non traitée : "+e+"\n"+"demande en cours : "+a);
        
        
        
    }

    @FXML
    private void bttraitee(ActionEvent event) {
    }

   

    
}
