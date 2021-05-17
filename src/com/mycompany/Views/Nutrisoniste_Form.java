/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Views;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Models.nutisoniste;
import com.mycompany.Services.Abonnement_Service;
import com.mycompany.Services.nutrisoniste_service;

import com.mycompany.myapp.MyApplication;

/**
 *
 * @author Aziz
 */
public class Nutrisoniste_Form extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    public Nutrisoniste_Form(Form previous,int duree)
    {
           super("Nutrisonistes",BoxLayout.y());
            this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
             for (nutisoniste c : new nutrisoniste_service().findAll()) {

            this.add(addItem_nutri(c,duree));

        }
               this.revalidate();
            });
        });

        this.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : this.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : this.getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    mb.setUIIDLine1("libC");
                    mb.setUIIDLine2("btn");
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);
        
               this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });
    }
    
       public MultiButton  addItem_nutri(nutisoniste c,int duree) {

      MultiButton m = new MultiButton();
     m.setTextLine1(c.getPrenom());
        m.setTextLine2(c.getNom());
         String  url = "http://127.0.0.1:8000/theme_frontend/img/team/team-1.jpg";
         m.setEmblem(theme.getImage("arrow.png"));
        Image imge;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
        m.setIcon(imge);
    
         m.setEmblem(theme.getImage("round.png"));
      
        m.addActionListener(l
                -> {
            Abonnement_Service as = new Abonnement_Service();
            as.AddAbonnment(9, c.getId(), duree);
            Dialog.show("Achat de abonnment", "Achat de abonnment", "OK", null);
               
                    Form f2 = new Form("programme de nutrition",BoxLayout.y());
           
          TextField Objectif = new TextField("", "Objectif", 20, TextArea.ANY);
           TextField Blessure = new TextField("", "Blessure", 20, TextArea.ANY);
            TextField ne_manger_pas = new TextField("", "ne_manger_pas", 20, TextArea.ANY);
             TextField supplement = new TextField("", "supplement", 20, TextArea.ANY);
             TextField maladie = new TextField("", "maladie", 20, TextArea.ANY);
             
        TextField age = new TextField("", "age", 20, TextArea.NUMERIC);
           TextField taille = new TextField("", "taille", 20, TextArea.NUMERIC);
      TextField poids = new TextField("", "poids", 20, TextArea.NUMERIC);
      Button btn_valider = new Button("Valider");

           ComboBox sexe = new ComboBox();
   sexe .addItem("Masculin");
      sexe .addItem("Feminin");
      
      btn_valider.addActionListener(az->{
      
          if(Objectif.getText().equals(""))
          {
            Dialog.show("Erreur", "Champ vide de Objectif ", "OK", null);    
          }
          else if(Blessure.getText().equals(""))
          {
            Dialog.show("Erreur", "Champ vide de Blessure ", "OK", null);    
          }
           else if(ne_manger_pas.getText().equals(""))
          {
            Dialog.show("Erreur", "Champ vide de ne_manger_pas ", "OK", null);    
          }
           else if(supplement.getText().equals(""))
          {
            Dialog.show("Erreur", "Champ vide de supplement ", "OK", null);    
          }
          else if(maladie.getText().equals(""))
          {
            Dialog.show("Erreur", "Champ vide de maladie ", "OK", null);    
          }
           else if(age.getText().equals(""))
          {
            Dialog.show("Erreur", "Champ vide de age ", "OK", null);    
          }
            else if(taille.getText().equals(""))
          {
            Dialog.show("Erreur", "Champ vide de taille ", "OK", null);    
          }
          
           else if(poids.getText().equals(""))
          {
            Dialog.show("Erreur", "Champ vide de poids ", "OK", null);    
          }
          else
           {
               nutrisoniste_service ns = new nutrisoniste_service();
               
               
               ns.Add_program(9, 20, c.getId(), Objectif.getText(), Blessure.getText(), ne_manger_pas.getText(), supplement.getText(), maladie.getText(), age.getText(), taille.getText(), poids.getText(),sexe.getSelectedItem().toString());
                Dialog.show("Ajout", "Ajout", "OK", null);    
                new MyApplication().start();
           }
         
            
        
        
      
      });
      
      f2.add(Objectif).add(Blessure).add(ne_manger_pas).add(supplement).add(maladie).add(age).add(taille).add(poids).add(sexe).add(btn_valider);
               
            
            
            
            f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });     
 f2.show();
        
        });
        return m;

    }  
}