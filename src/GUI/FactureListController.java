/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Campi.Entity.Commande;
import Campi.Entity.Facture;
import Campi.Service.ServiceFacture;
import Campi.Utils.JfreeChartApi;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class FactureListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField TotalFactureTf;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<Facture> FactureTab;
    @FXML
    private TableColumn<Facture, Integer> IDFactureTab;
    @FXML
    private TableColumn<Facture, Integer> NuméroFactureTab;
    @FXML
    private TableColumn<Facture, Float> TotalFactureTab;
    @FXML
    private TableColumn<Facture, LocalDateTime> DateFactureTab;
    @FXML
    private TextField TFSearch;
    @FXML
    private Label NombreFacture;
    @FXML
    private TextField numFactTf;
    @FXML
    private Button Statistique;
    @FXML
    private DatePicker DateFact;

    /**
     * Initializes the controller class.
     */
    
    ServiceFacture sf =new ServiceFacture();
    int id;
    Facture f ;
    ObservableList<Facture> data=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                try {
            // TODO
            NombreFacture.setText(Integer.toString(sf.nbFacture()));
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void AjouterFacture(ActionEvent event) {
                    try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/GUI/AjouterFacture.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(sf.afficher());
            //System.out.println(data);
            FilteredList<Facture> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    else if(String.valueOf(p.getDate_fact()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(p.getTotal_fact()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(p.getNum_fact()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }                          
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Facture> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(FactureTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		FactureTab.setItems(sortedData);   
        }
     
         public void refreshlist() throws SQLException{
            data.clear();
            data = FXCollections.observableArrayList(sf.afficher());
            FactureTab.setEditable(true);
            FactureTab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            IDFactureTab.setCellValueFactory(new PropertyValueFactory<>("id"));
            NuméroFactureTab.setCellValueFactory(new PropertyValueFactory<>("num_fact"));
            TotalFactureTab.setCellValueFactory(new PropertyValueFactory<>("total_fact"));
            DateFactureTab.setCellValueFactory(new PropertyValueFactory<>("date_fact"));
            FactureTab.setItems(data);
         
         }
    @FXML
    private void DeleteFacture(ActionEvent event) {
        int Id;
        Id=FactureTab.getSelectionModel().getSelectedItem().getId();
        try {
            sf.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public void updateFacture() throws SQLException{
        Facture facture = new Facture(Integer.parseInt(numFactTf.getText()),
                                      Float.parseFloat(TotalFactureTf.getText()),
                                      DateFact.getValue().atStartOfDay());         
        sf.modifier(id, facture);
        refreshlist(); 
    }
       
    @FXML
    private void EditFacture(ActionEvent event) {
                 try {
            updateFacture();
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fillforum(MouseEvent event) {
        Facture f=FactureTab.getSelectionModel().getSelectedItem();
        id=f.getId();
        numFactTf.setText(Integer.toString(f.getNum_fact()));
        TotalFactureTf.setText(Float.toString(f.getTotal_fact()));
        DateFact.setValue(f.getDate_fact().toLocalDate());
    }

    @FXML
    private void Statistique(ActionEvent event) {
        HashMap<String, Double> data = sf.StatistiqueParNom();
        JfreeChartApi chart = new JfreeChartApi(data);
        chart.afficherStatistique();
    }
    
}
