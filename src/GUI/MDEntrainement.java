/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
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
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        addTab(swipe, res.getImage("breadcrumb-bg.jpg"), spacer1);
           
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
                ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        Component.setSameSize(radioContainer, spacer1);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        
 

        
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
            private void addTab(Tabs swipe, Image img, Label spacer) {
        
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
            
                   
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
            
            }
        
        
}
        

