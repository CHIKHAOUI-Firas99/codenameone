/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
 import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Alert.AlertDialog;
import Service.produit_Service;
import Utils.MyConnexion;
import entites.produit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class home_ProduitController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_affichage;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<produit> tabview;
    @FXML
    private TableColumn<produit, String> col_libelle;
    @FXML
    private TableColumn<produit, String> col_type;
    @FXML
    private TableColumn<produit, Integer> col_prix;
    @FXML
    private TableColumn<produit, Integer> col_quantite;

    @FXML
    private TextField txt_libelle;
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField txt_quantite;
    @FXML
    private TextField txt_type;
    @FXML
    private Button btn_ajout;
    private ImageView imageview;
    @FXML
    private ImageView Image_Produit;
    produit_Service service = new produit_Service();
     private TableColumn<produit, String> col_btnDelet;
          private String nomImage = "";
    @FXML
    private Button excel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         setCellfromtabletoImage();
        Modifier();
        tabview.setEditable(true);  
                search();
        try {
            refreche();
        } catch (SQLException ex) {
        }
           col_btnDelet = new TableColumn("Supprimer");
                javafx.util.Callback<TableColumn<produit, String>, TableCell<produit, String>> cellFactory
                = new Callback<TableColumn<produit, String>, TableCell<produit, String>>() {
            public TableCell call(final TableColumn<produit, String> param) {
                final TableCell<produit, String> cell = new TableCell<produit, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                produit u = getTableView().getItems().get(getIndex());
 File f = new File("C:/wamp64/www/images/"+u.getImage());
                            
                                System.out.println(f.delete());                             

                          
                              
                                try {
                                    service.Supprimer(u.getId());
                                } catch (SQLException ex) {
                                 }
                               
                                AlertDialog.showNotification("suppression confirmée!", "suppression a été bien faite", AlertDialog.image_checked);

                                try {
                                    refreche();
                                } catch (SQLException ex) {
                                }

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
        tabview.getColumns().add(col_btnDelet);
        
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }


   
    @FXML
    private void ajouter_produit(ActionEvent event) throws SQLException {
        
          if (txt_libelle.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de libelle", AlertDialog.image_cross);
        } else if (txt_libelle.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_libelle !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } else if (txt_prix.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Prix", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_prix.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de Prix", AlertDialog.image_cross);
        } else if (txt_prix.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur Telephone !", "Prix incorrect", AlertDialog.image_cross);
        } else if (txt_quantite.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de quantite", AlertDialog.image_cross);
        } else if (txt_quantite.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur Telephone !", "quantite incorrect", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_quantite.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de quantite", AlertDialog.image_cross);
        } else if (txt_type.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Type", AlertDialog.image_cross);
        } else if (txt_type.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur Type !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } 
        else if (nomImage.equals("")) {
            AlertDialog.showNotification("Erreur Image !","Il faut inserer une photo",AlertDialog.image_cross);
        }
        
        produit p = new produit(Integer.parseInt(txt_prix.getText()),txt_type.getText(), txt_libelle.getText() ,Integer.parseInt(txt_quantite.getText()),nomImage);
            service.Ajouter(p);
             InputStream inStream = null;
    OutputStream outStream = null;

        try{

            File afile =new File("./src/images/"+nomImage);
            File bfile =new File("C:/wamp64/www/images/"+nomImage);
            

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[]buffer = new byte[1024];

            int length;
           //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");

        }catch(IOException e){
            e.printStackTrace();
        }
            
            
            
            
            
            
            
            refreche();
    }
    
               public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_libelle.setOnEditCommit((TableColumn.CellEditEvent<produit, String> event) -> {
            TablePosition<produit, String> pos = event.getTablePosition();
                
            String libelle = event.getNewValue();
                 
            int row = pos.getRow();
            produit ac = event.getTableView().getItems().get(row);
           
  
            ac.setLabelle(libelle);
                    try {
                        service.Modifier(ac,ac.getId());
                    } catch (SQLException ex) {
                    }
        });
                
                
          
            
              
              
                        col_type.setOnEditCommit((TableColumn.CellEditEvent<produit, String> event) -> {
            TablePosition<produit, String> pos = event.getTablePosition();
           
            String type = event.getNewValue();
                  
            int row = pos.getRow();
            produit ab = event.getTableView().getItems().get(row);
          
  
            ab.setType(type);
                    try {
                        service.Modifier(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        });       
              
     col_quantite.setOnEditCommit((TableColumn.CellEditEvent<produit, Integer> event) -> {
            TablePosition<produit, Integer> pos = event.getTablePosition();
           
            Integer Quantite_Ab = event.getNewValue();
                  
            int row = pos.getRow();
            produit ab = event.getTableView().getItems().get(row);
          
  
            ab.setQuantite(Quantite_Ab);
                    try {
                        service.Modifier(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        });  
     
     col_prix.setOnEditCommit((TableColumn.CellEditEvent<produit, Integer> event) -> {
            TablePosition<produit, Integer> pos = event.getTablePosition();
           
            Integer Prix = event.getNewValue();
                  
            int row = pos.getRow();
            produit ab = event.getTableView().getItems().get(row);
          
  
            ab.setPrix(Prix);
              
                    try {
                        service.Modifier(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
                  
        });
     
     
     
                
    }
               
                 public void refreche() throws SQLException {
        col_libelle.setCellValueFactory(new PropertyValueFactory<>("labelle"));
        col_libelle.setCellFactory(TextFieldTableCell.<produit> forTableColumn());
       
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_type.setCellFactory(TextFieldTableCell.<produit> forTableColumn());
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_prix.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
     col_quantite.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
         tabview.getItems().clear();

        tabview.setItems(service.Affichertout());

    }
                 private void setCellfromtabletoImage() {
        tabview.setOnMouseClicked(e -> {

            produit ac = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
            String ImageUrl ="http://localhost/images/";
        

        Image image = new Image(ImageUrl + ac.getImage());
        Image_Produit.setImage(image);
        }
        );

    }
 public void search() {
        txt_Seach.setOnKeyReleased(e
                -> {
            if (txt_Seach.getText().equals("") ) {

                try {
                    refreche();
                } catch (SQLException ex) {
                }

            } else {

                try {
               col_libelle.setCellValueFactory(new PropertyValueFactory<>("labelle"));
        col_libelle.setCellFactory(TextFieldTableCell.<produit> forTableColumn());
       
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_type.setCellFactory(TextFieldTableCell.<produit> forTableColumn());
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_prix.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
     col_quantite.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
             tabview.getItems().clear();

                    tabview.setItems(service.serach(txt_Seach.getText()));

                } catch (SQLException ex) {
                    }
        

            }
        }
        );

    }
  private Connection c = MyConnexion.getInsCon().getcnx();
    @FXML
    private void excel(ActionEvent event) {
            try {
                System.out.println("zzzzz");  
  String query = "SELECT * from produit";
  
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("produit Infos");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("id");
            header.createCell(1).setCellValue("prix");
            header.createCell(2).setCellValue("type");
            header.createCell(3).setCellValue("labelle");
            header.createCell(4).setCellValue("quantite");
        
        
           sheet.autoSizeColumn(0);

            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
          
           
            sheet.setColumnWidth(3, 256 * 25);
            sheet.setZoom(150);
            
            int index = 1;
            
            
            while (rs.next()) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getInt("id"));
                row.createCell(1).setCellValue(rs.getInt("prix"));
                row.createCell(2).setCellValue(rs.getString("type"));
                row.createCell(3).setCellValue(rs.getString("labelle"));
                row.createCell(4).setCellValue(rs.getInt("quantite"));
              
              
               
                
                index++;
            }

            FileOutputStream fileOut = new FileOutputStream("EListe produit" + index + ".xlsx");
            index++;
            wb.write(fileOut);
            fileOut.close();

           

            pst.close();
            rs.close();

        } catch (SQLException | FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    @FXML
    private void uploaad_image(ActionEvent event) {
          try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
   
       nomImage = f.getName();
             
         
        } catch (Exception ex) {
          }
        
         
    }
 
}
