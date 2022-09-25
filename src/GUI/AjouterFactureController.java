/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Campi.Entity.Facture;
import Campi.Service.ServiceFacture;
import Campi.Utils.MailerApi;
import Campi.Utils.SMSApi;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AjouterFactureController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField numFactTf;
    @FXML
    private TextField TotalFactureTf;
    @FXML
    private ImageView image_view;
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
    private void Acceuil(ActionEvent event) {
    }

    @FXML
    private void Enregister(ActionEvent event) {
        String erreurs="";
        if(numFactTf.getText().trim().isEmpty()){
            erreurs+="Numero facture vide\n";
        }
        if(TotalFactureTf.getText().trim().isEmpty()){
            erreurs+="Total Facture vide\n";
        }
        if(DateFact.getValue()==null){
            erreurs+="date Debut vide\n";}
            
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Facture");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
        ServiceFacture SFacture = new ServiceFacture();
        Facture facture = new Facture(Integer.parseInt(numFactTf.getText()),
                                      Float.parseFloat(TotalFactureTf.getText()),
                                      DateFact.getValue().atStartOfDay());            

                  SFacture.ajouter(facture);        
                                                                                 //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Facture créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(10000));
        
        // SEND MAIL
        MailerApi mailer = new MailerApi();
        mailer.SendMail("feriel.mahfoudhi@esprit.tn", "Admin.");

       //SEND SMS
        SMSApi sms = new SMSApi();
        sms.sendSMS("+21629774931", "Admin.");
    }
    }
    
    
    @FXML
    private void afficherFacture(ActionEvent event) {
                    try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/GUI/FactureList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
