/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Campi.Entity.Commande;
import Campi.Service.CommandeService;
import Campi.Utils.MailerApi;
import Campi.Utils.MailerApiCommande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AjouterCommandeController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField NumCommandeTf;
    @FXML
    private ImageView image_view;
    @FXML
    private DatePicker datecmd;
    @FXML
    private TextField TotalcmdTf;

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
        if(NumCommandeTf.getText().trim().isEmpty()){
            erreurs+="Numero Commande vide\n";
        }
        if(datecmd.getValue()==null){
            erreurs+="date Commande vide\n";
        }
        if(TotalcmdTf.getText().trim().isEmpty()){
            erreurs+="Total Commande vide\n";
        }
            
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Commande");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
        CommandeService SCommande= new CommandeService();
        Commande commande = new Commande(Integer.parseInt(NumCommandeTf.getText()),
                                         datecmd.getValue().atStartOfDay(), 
                                      Float.parseFloat(TotalcmdTf.getText()));      
                  SCommande.ajouter(commande);        
                                                                                 //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Commande créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(10000));
        
        // SEND MAIL
        MailerApiCommande mailer = new MailerApiCommande();
        mailer.SendMail("feriel.mahfoudhi@esprit.tn", "Admin.");

    }
    }
    

    @FXML
    private void afficherCommande(ActionEvent event) {
    }
    
}
