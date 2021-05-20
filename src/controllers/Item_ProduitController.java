/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.panier_produit_Service;
import Service.produit_Service;
import static controllers.FrontController.indiceProduit;
import entites.panier_produit;
import entites.produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class Item_ProduitController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Button btn_acheter;
    @FXML
    private Label type;
    @FXML
    private Label prix;
    @FXML
    private TextField txt_quantite;
    @FXML
    private ImageView image_acc;
    @FXML
    private Label libelle;
        produit acc = null; 
    produit_Service ser = new produit_Service();
    panier_produit_Service service = new panier_produit_Service();
    @FXML
    private Label quantite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            acc=ser.get_produit_affichage(indiceProduit);
  libelle.setText(acc.getLabelle());
      type.setText(acc.getType());
   
      prix.setText(String.valueOf(acc.getPrix()) + " DT");
   quantite.setText(String.valueOf(acc.getQuantite()));
        String ImageUrl = "http://localhost/images/";
        Image image = new Image(ImageUrl + acc.getImage());
      
        image_acc.setImage(image);
    }    

    @FXML
    private void acheter(ActionEvent event) throws SQLException {
           if (txt_quantite.getText().equals("")) {
       AlertDialog.showNotification("Error !","Champ vide de quantite",AlertDialog.image_cross);
        } 
                else if (txt_quantite.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Error !","quantite incorrect",AlertDialog.image_cross);
        }
              else
                {
                    
                    ser.decrementer(Integer.valueOf(txt_quantite.getText()), acc.getId());
                   
                    panier_produit p = new panier_produit(acc.getId(),1,Integer.valueOf(txt_quantite.getText()),acc.getLabelle());
                     service.Ajouter(p);
                    AlertDialog.showNotification("Achat !","Achat aves sucess",AlertDialog.image_checked);
            
                }
    }
    
}
