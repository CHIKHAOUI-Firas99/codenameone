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
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Models.Produit;
import com.mycompany.Services.Panier_Service;
import com.mycompany.Services.Produit_Service;
import com.mycompany.myapp.MyApplication;

/**
 *
 * @author ikram
 */
public class Produit_Form  extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
      String data="";
    public Produit_Form(Form previous)
    {
           super("Produits",BoxLayout.y());
            this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
              
             for (Produit c : new Produit_Service().findAll()) {
   data += "<tr>"+
          "<td>" + c.getPrix() +"<td>"
           
           + 
           "<td>" + c.getType()+"<td>"
           +
             "<td>" + c.getLabelle()+"<td>"
           +
             "<td>" + c.getQuantite()+"<td>"
            + 
           
           
           
           "</tr>";

            this.add(addItem_Produit(c));

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
                 this.getToolbar().addCommandToOverflowMenu("PDF", null, ev -> {
     String urlab = "http://localhost/generate-pdf/ex.php";

                                ConnectionRequest cnreq = new ConnectionRequest();
                                cnreq.setPost(false);
                           
                                cnreq.addArgument("data", data);
                                cnreq.setUrl(urlab);

                                cnreq.addResponseListener(evx
                                        -> {
                                    String etat = new String(cnreq.getResponseData());
                                    System.out.println(etat);
                                
                                }
                                );
                                NetworkManager.getInstance().addToQueueAndWait(cnreq);
         Dialog.show("PDF", "PDF", "OK", null);
        });
    }
    
       public MultiButton  addItem_Produit(Produit c) {

      MultiButton m = new MultiButton();
     m.setTextLine1(c.getLabelle());
        m.setTextLine2(c.getType());
        String  url = "http://127.0.0.1:8000/uploads/images/"+c.getImage();
         m.setEmblem(theme.getImage("arrow.png"));
        Image imge;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
        m.setIcon(imge);
        m.addActionListener(l
                -> {
                    Form f2 = new Form("Detail",BoxLayout.y());
           
         
        
           
      

          

                    Button btn= new Button("Acheter");

              btn.addActionListener(xxx
                    -> {
               
                  Panier_Service pn = new Panier_Service();
                  pn.Addproduit(9, c.getId());

                  Dialog.show("Achat", "Achat", "OK", null);
                   new MyApplication().start();
                
            }
            );
                
            f2.add("Labelle : ").add(c.getLabelle()).add("Type : ").add(c.getType()).add("Quantite : ").add(String.valueOf(c.getQuantite())).add("Prix : ").add(String.valueOf(c.getPrix())).add(btn);
            
            
            
            f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });     
 f2.show();
        
        });
        return m;

    }  
}