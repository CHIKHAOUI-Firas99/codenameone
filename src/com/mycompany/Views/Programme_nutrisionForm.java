/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Views;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
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
import com.mycompany.Models.programme_nutrision;
import com.mycompany.Services.programme_nutrision_Service;

import com.mycompany.myapp.MyApplication;

/**
 *
 * @author Aziz
 */
public class Programme_nutrisionForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    public Programme_nutrisionForm(Form previous)
    {
           super("Nutrisonistes",BoxLayout.y());
            this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
             for (programme_nutrision c : new programme_nutrision_Service().findAll(9)) {

            this.add(addItem_programme_nutrision(c));

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
    String nom_image="";
       public MultiButton  addItem_programme_nutrision(programme_nutrision c) {

      MultiButton m = new MultiButton();
     m.setTextLine1(c.getJourrepot());
        m.setTextLine2(String.valueOf(c.getDuree()));
      
     
    
         m.setEmblem(theme.getImage("round.png"));
      
        m.addActionListener(l
                -> {
          
               
                    Form f2 = new Form("programme de nutrition",BoxLayout.y());
           
       
           String urlab = "http://localhost/qr/qrcode.php";

                                ConnectionRequest cnreq = new ConnectionRequest();
                                cnreq.setPost(false);
                           String data = "Repas 1 : "+c.getRepas1() + " Repas 2 : "+c.getRepas2()+" Repas 3 : "+c.getRepas3()+" Repas 4 : "+c.getRepas4()+" Repas 5 : "+c.getRepas5()+" Jour repot : "+c.getJourrepot()+" Duree t :"+c.getDuree();
                                cnreq.addArgument("data", data);
                                cnreq.setUrl(urlab);

                                cnreq.addResponseListener(evx
                                        -> {
                                     nom_image = new String(cnreq.getResponseData());
                                     System.out.println(nom_image);
                                
                                
                                }
                                );
                                NetworkManager.getInstance().addToQueueAndWait(cnreq);
            
          String  url = "http://localhost/qr/"+nom_image;
      
        Image imge;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
      
        
      
  
      
      f2.add("Repas 1 "+c.getRepas1()).add("Repas 2 "+c.getRepas2()).add("Repas 3 "+c.getRepas3()).add("Repas 4 "+c.getRepas4()).add("Repas 5 "+c.getRepas5()).add("Jour repas : "+c.getJourrepot()).add("Duree : "+c.getDuree());
               
      f2.add(imge);
      
      
      
            
            
            
            f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });     
 f2.show();
        
        });
        return m;

    }  
}