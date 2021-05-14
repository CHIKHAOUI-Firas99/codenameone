/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.util.Resources;
import entities.Entrainement;
import java.util.HashMap;
import java.util.Map;
import services.ServiceEntrainements;

/**
 *
 * @author 21652
 */

public class AjoutEnt extends BaseForm {
    Form current;
    private Resources theme;
    public AjoutEnt(Resources res){
        super("NewsFeed",BoxLayout.y());
        current = this;
        
        TextField Titre = new TextField("","Titre d'entrainement");
        Titre.setUIID("TextFieldBlack");
        addStringValue("Jour",Titre);

          ComboBox combo = new ComboBox(); 
          combo.addItem("6.00am - 8.00am");
          combo.addItem("10.00am - 12.00am");
          combo.addItem("5.00pm - 7.00pm");
          combo.addItem("7.00pm - 9.00pm");
          //combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
          add(combo);
          ComboBox comboj = new ComboBox();
          comboj.addItem("Lundi");
          comboj.addItem("Mardi");
          comboj.addItem("Mercredi");
          comboj.addItem("Jeudi");
          comboj.addItem("Vendredi");
          comboj.addItem("Samedi");
          comboj.addItem("Dimanche");
          add(comboj);
          
          
        
        
        
        Button Ajout = new Button("Ajouter"); 
        addStringValue("",Ajout);   
        Ajout.addActionListener(e ->{         
        if(Titre.getText()==""){
            System.out.println("Saisir vos donne");   
            System.out.println(combo.getSelectedItem());
        }
        else{
               InfiniteProgress pi  = new InfiniteProgress();
               final Dialog iDialog = pi.showInfiniteBlocking();
               if(combo.getSelectedItem()== "6.00am - 8.00am"){
               ServiceEntrainements.getInstance().AjoutEntrainnement(
               comboj.getSelectedItem().toString(), 
               "8",
               Titre.getText().toString());
               }
               else if(combo.getSelectedItem()== "10.00am - 12.00am"){
               ServiceEntrainements.getInstance().AjoutEntrainnement(    
               comboj.getSelectedItem().toString(), 
               "12",
               Titre.getText().toString());
                   
               }
               if(combo.getSelectedItem()== "5.00pm - 7.00pm"){
               ServiceEntrainements.getInstance().AjoutEntrainnement(    
               comboj.getSelectedItem().toString(), 
               "16",
               Titre.getText().toString());                   
               
               }
                if(combo.getSelectedItem()== "7.00pm - 9.00pm"){
               ServiceEntrainements.getInstance().AjoutEntrainnement(    
               comboj.getSelectedItem().toString(), 
               "20",
               Titre.getText().toString());                   
               }

               iDialog.dispose();
               refreshTheme();
               new ListEntrainements(res).show(); 
               
               
                             
                        }
        
     
        
        });
        
             
    }
    
    public void addStringValue(String s,Component v){
        
        add(BorderLayout.west(new Label(s,"paddedLabel")).add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
    
    
}

    /*
    private Map<String, Object> createListEntry(String name) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line2", name);
    return entry;
}
*/
    //{Line2=7.00pm - 9.00pm}

               /*
               ServiceEntrainements.getInstance().AjoutEntrainnement(
                       Jour.getText().toString(), 
                       Heure.getText().toString(),
                       Titre.getText().toString());
               System.out.println("ok");
               System.out.println(Jour.getText());
               System.out.println(Heure.getText());
               */

        /*
        TextField Jour = new TextField("","Jour d'entrainement");
        Jour.setUIID("extFieldBlack");
        addStringValue("Jour",Jour);
        
        TextField Heure = new TextField("","Heure"); 
        Heure.setUIID("extFieldBlack");
        addStringValue("Heure",Heure);  
        */