/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class CodeCouponListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField TotalFactureTf;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<?> FactureTab;
    @FXML
    private TableColumn<?, ?> IDFactureTab;
    @FXML
    private TableColumn<?, ?> NuméroFactureTab;
    @FXML
    private TableColumn<?, ?> TotalFactureTab;
    @FXML
    private TableColumn<?, ?> DateFactureTab;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterFacture(ActionEvent event) {
    }

    @FXML
    private void DeleteFacture(ActionEvent event) {
    }

    @FXML
    private void EditFacture(ActionEvent event) {
    }

    @FXML
    private void fillforum(MouseEvent event) {
    }

    @FXML
    private void Statistique(ActionEvent event) {
    }
    
}
