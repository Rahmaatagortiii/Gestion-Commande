/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Campi.Entity.Commande;
import Campi.Service.CommandeService;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class CommandeListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField NumCommandeTf;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<Commande> CommandeTab;
    @FXML
    private TableColumn<Commande, Integer> IDCommandeTab;
    @FXML
    private TableColumn<Commande, Integer> NumeroCommandeTab;
    @FXML
    private TableColumn<Commande, LocalDateTime> DateTab;
    @FXML
    private TableColumn<Commande, Float> TotalcmdTab;
    @FXML
    private TextField TFSearch;
    @FXML
    private Label NombreCommande;
    @FXML
    private TextField TotalcmdTf;
    @FXML
    private Button Statistique;
    @FXML
    private DatePicker datecmd;

    /**
     * Initializes the controller class.
     */
    CommandeService Cs =new CommandeService();
    int id;
    Commande c ;
    ObservableList<Commande> data=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            NombreCommande.setText(Integer.toString(Cs.nbCommande()));
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void AjouterCommande(ActionEvent event) {
        
    }
    
 public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(Cs.afficher());
            //System.out.println(data);
            FilteredList<Commande> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    else if(String.valueOf(p.getDate_cmd()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(p.getNum_cmd()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(p.getTotal_cmd()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }                          
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Commande> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(CommandeTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		CommandeTab.setItems(sortedData);   
        }
     
         public void refreshlist() throws SQLException{
            data.clear();
            data = FXCollections.observableArrayList(Cs.afficher());
            CommandeTab.setEditable(true);
            CommandeTab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            IDCommandeTab.setCellValueFactory(new PropertyValueFactory<>("id"));
            NumeroCommandeTab.setCellValueFactory(new PropertyValueFactory<>("num_cmd"));
            DateTab.setCellValueFactory(new PropertyValueFactory<>("date_cmd"));
            TotalcmdTab.setCellValueFactory(new PropertyValueFactory<>("total_cmd"));
            CommandeTab.setItems(data);
    }
    @FXML
    private void DeleteCommande(ActionEvent event) {
                int Id;
        Id=CommandeTab.getSelectionModel().getSelectedItem().getId();
        try {
            Cs.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       public void updateCommande() throws SQLException{
    Commande commande = new Commande(Integer.parseInt(NumCommandeTf.getText()),
                                         datecmd.getValue().atStartOfDay(), 
                                      Float.parseFloat(TotalcmdTf.getText()));       
        Cs.modifier(id, commande);
        refreshlist(); 
    }
       
    @FXML
    private void EditCommande(ActionEvent event) {
         try {
            updateCommande();
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fillforum(MouseEvent event) {
        Commande c=CommandeTab.getSelectionModel().getSelectedItem();
        id=c.getId();
        NumCommandeTf.setText(Integer.toString(c.getNum_cmd()));
        datecmd.setValue(c.getDate_cmd().toLocalDate());
        TotalcmdTf.setText(Float.toString(c.getTotal_cmd()));
    }

    @FXML
    private void Statistique(ActionEvent event) {
    }
    
}
