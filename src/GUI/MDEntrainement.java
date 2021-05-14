/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Entrainement;
import services.ServiceEntrainements;

/**
 *
 * @author 21652
 */
public class MDEntrainement extends BaseForm {
    Form current;
    public MDEntrainement(Resources res,Entrainement ent){
        
        super("NewsFeed",BoxLayout.y());
        current = this;

        
        TextField Titre = new TextField(ent.titre,"Titre",20, TextField.ANY);
        Titre.setUIID("NewsTopLine");
        Titre.setSingleLineTextArea(true);
        //addStringValue("Titre",Titre);

        TextField Type = new TextField(ent.type,"Type",20, TextField.ANY);
        Type.setUIID("NewsTopLine");
        Type.setSingleLineTextArea(true);
        //addStringValue("Type",Type);
        
        TextField Meet = new TextField(ent.meet,"Meet",20, TextField.ANY);
        Meet.setUIID("NewsTopLine");
        Meet.setSingleLineTextArea(true);
        //addStringValue("Meet",Meet);

          ComboBox combo = new ComboBox(); 
          combo.addItem("6.00am - 8.00am");
          combo.addItem("10.00am - 12.00am");
          combo.addItem("5.00pm - 7.00pm");
          combo.addItem("7.00pm - 9.00pm");
          //combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
          //add(combo);
          ComboBox comboj = new ComboBox();
          comboj.addItem("Lundi");
          comboj.addItem("Mardi");
          comboj.addItem("Mercredi");
          comboj.addItem("Jeudi");
          comboj.addItem("Vendredi");
          comboj.addItem("Samedi");
          comboj.addItem("Dimanche");
          //add(comboj);
          
          if(ent.heure == 8){
          combo.setSelectedItem("6.00am - 8.00am");   
          }
          else if(ent.heure == 12){
          combo.setSelectedItem("10.00am - 12.00am");   
          }
          else if(ent.heure == 16){
          combo.setSelectedItem("5.00pm - 7.00pm");   
          }
          else if(ent.heure == 20){
          combo.setSelectedItem("7.00pm - 9.00pm");   
          }
          
          comboj.setSelectedItem(ent.jour);
          
          
        Button Modif = new Button("Modifer"); 
        //addStringValue("",Modif);  
        
        Modif.addActionListener(e ->{ 
        InfiniteProgress pi  = new InfiniteProgress();
        final Dialog iDialog = pi.showInfiniteBlocking();               
        if(combo.getSelectedItem()== "6.00am - 8.00am"){
               ent.setJour(comboj.getSelectedItem().toString());
               ent.setHeure(8);
               ent.setType(Type.getText().toString());
               ent.setMeet(Meet.getText().toString());
              ent.setTitre( Titre.getText().toString());
              ServiceEntrainements.getInstance().UpEntrainement(ent);
               }
               else if(combo.getSelectedItem()== "10.00am - 12.00am"){
               ent.setJour(comboj.getSelectedItem().toString());
               ent.setHeure(12);
               ent.setType(Type.getText().toString());
               ent.setMeet(Meet.getText().toString());
              ent.setTitre( Titre.getText().toString());
              ServiceEntrainements.getInstance().UpEntrainement(ent);
                   
               }
               if(combo.getSelectedItem()== "5.00pm - 7.00pm"){
               ent.setJour(comboj.getSelectedItem().toString());
               ent.setHeure(16);
               ent.setType(Type.getText().toString());
               ent.setMeet(Meet.getText().toString());
              ent.setTitre( Titre.getText().toString());
              ServiceEntrainements.getInstance().UpEntrainement(ent);
               }
                if(combo.getSelectedItem()== "7.00pm - 9.00pm"){
               ent.setJour(comboj.getSelectedItem().toString());
               ent.setHeure(20);
               ent.setType(Type.getText().toString());
               ent.setMeet(Meet.getText().toString());
              ent.setTitre( Titre.getText().toString());
              ServiceEntrainements.getInstance().UpEntrainement(ent);                    
               }
               iDialog.dispose();
                refreshTheme(); 
                new ListEntrainements(res).show(); 
        });
        Button Supp = new Button("Supprimer");
        
        Supp.addActionListener(e ->{
            ServiceEntrainements.getInstance().SUPPEntrainement(ent);
                InfiniteProgress pi  = new InfiniteProgress();
                final Dialog iDialog = pi.showInfiniteBlocking();               
                iDialog.dispose();
                refreshTheme(); 
                new ListEntrainements(res).show(); 
        });
        
        Container cnt = BoxLayout.encloseY(
                new FloatingHint(Titre),
                new FloatingHint(Type),
                new FloatingHint(Meet),
                combo,
                comboj,             
                Modif,
                Supp
                
        );
        add(cnt);
        show();


   
    
    
    }
    

    
    
    
        public void addStringValue(String s,Component v){
    
        add(BorderLayout.west(new Label(s,"paddedLabel")).add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
        
}
        

