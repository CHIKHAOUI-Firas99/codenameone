/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Views;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Models.abonnement;
import com.mycompany.Services.Abonnement_Service;


import com.mycompany.myapp.MyApplication;

/**
 *
 * @author Aziz
 */
public class Abo_NutrisionisteForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    public Abo_NutrisionisteForm(Form previous)
    {
           super("Abo Nutisioniste",BoxLayout.y());
            this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
             for (abonnement c : new Abonnement_Service().findAll_nutri()) {

            this.add(addItem_nutri(c));

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
    
       public MultiButton  addItem_nutri(abonnement c) {

      MultiButton m = new MultiButton();
     m.setTextLine1(String.valueOf(c.getDuree())+" Moins");
        m.setTextLine2(String.valueOf(c.getPrix())+" DT");
    
         m.setEmblem(theme.getImage("round.png"));
      
        m.addActionListener(l
                -> {
                 new Nutrisoniste_Form(this,c.getDuree()).show();
        
           
      

          

             
        
        });
        return m;

    }  
}