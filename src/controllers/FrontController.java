/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.panier_produit_Service;
import Service.produit_Service;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entites.panier_produit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class FrontController implements Initializable {

    @FXML
    private Pane pnl_panier;
    @FXML
    private Label prixTotal;
    @FXML
    private TextField txt_quantite_change;
    @FXML
    private Button btn_Modifier;
    @FXML
    private TableView<panier_produit> tabpanier;
    @FXML
    private TableColumn<panier_produit, String> col_lib_produit;
    @FXML
    private TableColumn<panier_produit, Integer> col_quantite_produit;
 
    @FXML
    private Pane pnl_accessoire;
    @FXML
    private ScrollPane scrollpaneProduit;
    @FXML
    private HBox hboxProduit;
    @FXML
    private Label username;
    @FXML
    private Button btn_product;
    @FXML
    private Button btn_Panier;
        private TableColumn<panier_produit, String> col_btnDelet;
   static int indiceProduit = 0;
      private int tailleProduit=0;
         produit_Service service_Produit = new produit_Service();
        panier_produit_Service service = new panier_produit_Service();

            panier_produit panier = null;
    @FXML
    private Button PDF;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCellfromtabletotxt();
        prixTotal.setText(String.valueOf(service.prixtotal()) + " DT");
          col_lib_produit.setCellValueFactory(new PropertyValueFactory<>("labelle"));
        col_quantite_produit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
     
        try {
            tabpanier.setItems(service.Affichertout_user(1));
        } catch (SQLException ex) {
         }
        
        
        /*-----------------*/
                
        try {
            tailleProduit = service_Produit.Affichertaille();
        } catch (SQLException ex) {
          
        }
          Node[] nodes_accessoire = new Node[tailleProduit];
           scrollpaneProduit.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
           
           
        for (indiceProduit = 0; indiceProduit < tailleProduit; indiceProduit++) {
            try {

                nodes_accessoire[indiceProduit] = FXMLLoader.load(getClass().getResource("/GUI/Item_Produit.fxml"));

                hboxProduit.getChildren().add(nodes_accessoire[indiceProduit]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
           col_btnDelet = new TableColumn("Supprimer");
                javafx.util.Callback<TableColumn<panier_produit, String>, TableCell<panier_produit, String>> cellFactory
                = new Callback<TableColumn<panier_produit, String>, TableCell<panier_produit, String>>() {
            public TableCell call(final TableColumn<panier_produit, String> param) {
                final TableCell<panier_produit, String> cell = new TableCell<panier_produit, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                panier_produit u = getTableView().getItems().get(getIndex());

                          
                              
                                try {
                                    service.Supprimer(u.getId());
                                } catch (SQLException ex) {
                                 }
                               
                                AlertDialog.showNotification("suppression confirmée!", "suppression a été bien faite", AlertDialog.image_checked);

                                col_lib_produit.setCellValueFactory(new PropertyValueFactory<>("labelle"));
        col_quantite_produit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
                                try {
                                    tabpanier.setItems(service.Affichertout_user(1));
                                } catch (SQLException ex) {
                                    Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    prixTotal.setText(String.valueOf(service.prixtotal()) + " DT");
     

                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        col_btnDelet.setCellFactory(cellFactory);
        tabpanier.getColumns().add(col_btnDelet);
    
    }    

    @FXML
    private void Modifier_quantite(ActionEvent event) throws Exception {
           if (txt_quantite_change.getText().equals("")) {
            AlertDialog.showNotification("Error !", "champ vide", AlertDialog.image_cross);
        }
        else if (txt_quantite_change.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Error !", "quantite incorrect", AlertDialog.image_cross);
        }
           else
        {
                 panier_produit p = new panier_produit();
                 p.setQuantite(Integer.valueOf(txt_quantite_change.getText()));
            service.Modifier(p, panier.getId());
        col_lib_produit.setCellValueFactory(new PropertyValueFactory<>("labelle"));
        col_quantite_produit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
      
                tabpanier.setItems(service.Affichertout_user(1));
                prixTotal.setText(String.valueOf(service.prixtotal()) + " DT");
                     AlertDialog.showNotification("Modification !","Modification aves sucess",AlertDialog.image_checked);
            
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) throws SQLException {
         if (event.getSource() == btn_Panier) {
            pnl_panier.toFront();
            tabpanier.setItems(service.Affichertout_user(1));
            prixTotal.setText(String.valueOf(service.prixtotal()) + " DT");
         }
            if (event.getSource() == btn_product) {
            pnl_accessoire.toFront();
        }
    }
     private void setCellfromtabletotxt() {
        tabpanier.setOnMouseClicked(e -> {
            panier = tabpanier.getItems().get(tabpanier.getSelectionModel().getSelectedIndex());
            txt_quantite_change.setText(String.valueOf(panier.getQuantite()));
        }
        );

    }

    @FXML
    private void Fiare_PDF(ActionEvent event) {
           Document document = new Document() {};
        try {

            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/ikram/Desktop/projet.pdf"));
            document.open();
            Paragraph ph1 = new Paragraph( "Votre commande mr "+"nom ttbhdel b user"+"prenom ttbhdel b user"  + "!");
            Paragraph ph2 = new Paragraph("prix total : "+String.valueOf(service.prixtotal()) + " DT");
               Paragraph ph3 = new Paragraph(".");
            PdfPTable table = new PdfPTable(2);
         
  
   
            //On créer l'objet cellule.
            PdfPCell cell;
           
            //contenu du tableau.
            table.addCell("labelle : ");
            table.addCell("quantite : ");
     
          
            
   
       
            service.Affichertout_user(1).forEach(e
                    -> {
                 table.addCell(e.getLabelle());
             
		table.setHorizontalAlignment(Element.ALIGN_CENTER);

               
                table.addCell(String.valueOf(e.getQuantite()));
        
     
   
    //Scale to new height and new width of image

    //Add to document

             
            }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
                    document.add(ph3);
            document.addAuthor("projet");
            AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();
    }
}
