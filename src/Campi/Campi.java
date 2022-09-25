/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Campi;

import Campi.Entity.Commande;
import Campi.Entity.Facture;
import Campi.Service.CommandeService;
import Campi.Service.ServiceFacture;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mahdi
 */
public class Campi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException  {
/******************************************************************************** Commande *********************************************************************/
       
        CommandeService SCommande = new CommandeService();
        Commande Facture =new Commande(1125,LocalDateTime.now(),3165465);
//        System.out.println(SCommande.SearchById(10));
       
/******************************* Ajouter Commande *********************************************/
//            SCommande.ajouter(Facture);
        
///******************************* Modifier Commande *********************************************/
//            SCommande.modifier(1, Facture);
/******************************* Supprimer Commande *********************************************/
//              SCommande.supprimer(1);
             
/******************************* Afficher Commande*********************************************/
//            System.out.println(SCommande.afficher());

/******************************* SearchById Commande *********************************************/
//            System.out.println(SCommande.SearchById(1));

/******************************* Number Commande *********************************************/
//            System.out.println(SCommande.nbCommande());
       

/******************************************************************************** Facture *********************************************************************/
        
        ServiceFacture sFacture = new ServiceFacture();
        Facture facture = new Facture(35655,645,LocalDateTime.now());

/******************************* Ajouter Facture *********************************************/
//            sFacture.ajouter(facture);

/******************************* Modifier Facture *********************************************/
//            sFacture.modifier(4, facture);

/******************************* Supprimer Facture *********************************************/
//              sFacture.supprimer(1);
             
/******************************* Afficher Facture *********************************************/
//            System.out.println(sFacture.afficher());

///******************************* SearchById Facture *********************************************/
//            System.out.println(sFacture.SearchById(1));

/******************************* Number Facture *********************************************/
//            System.out.println(sFacture.nbFacture());
    }
    
}
