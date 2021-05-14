/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Entrainement;
import java.io.IOException;


/**
 *
 * @author 21652
 */
public class Music extends  BaseForm {

    
        Form current;
    private Resources theme;
    
    public Music(Resources res){
    super("Planning", BoxLayout.y());
           
                System.out.println("ok");
                
                Toolbar tb = new Toolbar(true);
                setToolbar(tb);
                getTitleArea().setUIID("Container");
                setTitle("Planning");
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
                
                
                ButtonGroup barGroup = new ButtonGroup();
                RadioButton all = RadioButton.createToggle("Planinng", barGroup);
                all.setUIID("SelectBar");
                RadioButton abbs = RadioButton.createToggle("liste des abonnés", barGroup);
                abbs.setUIID("SelectBar");
                Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
                
                add(LayeredLayout.encloseIn(
                        GridLayout.encloseIn(2, all, abbs),
                        FlowLayout.encloseBottom(arrow)
                ));
                
                all.setSelected(true);
                arrow.setVisible(false);
                addShowListener(e -> {
                    arrow.setVisible(true);
                    updateArrowPosition(all, arrow);
                });
                bindButtonSelection(all, arrow,res);
                bindButtonSelection(abbs, arrow,res);
                
                // special case for rotation
                addOrientationListener(e -> {
                    updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
                });
                FileSystemStorage fs = FileSystemStorage.getInstance();
                String recordingsDir = fs.getAppHomePath() + "recordings/";
                fs.mkdir(recordingsDir);
                
             try {
               Media m1 = MediaManager.createMedia(recordingsDir + "Music1.mp3", false); 
               Media m2 = MediaManager.createMedia(recordingsDir + "Music2.mp3", false);
               Media m3 = MediaManager.createMedia(recordingsDir + "Music3.mp3", false);
               Media m4 = MediaManager.createMedia(recordingsDir + "Music4.mp3", false);
                
                MultiButton mb1 = new MultiButton("Motivation 1");
                mb1.addActionListener((e) -> {
                m2.pause();
                m3.pause();
                m4.pause();
                m1.play();});
                
                MultiButton mb2 = new MultiButton("Motivation 2");
                mb2.addActionListener((e) -> {
                m1.pause();
                m3.pause();
                m4.pause();
                m2.play();});
                
                MultiButton mb3 = new MultiButton("Motivation 3");
                mb3.addActionListener((e) -> {
                m1.pause();    
                m2.pause();
                m4.pause();
                m3.play();});                

                MultiButton mb4 = new MultiButton("Motivation 4");
                mb4.addActionListener((e) -> {
                m1.pause();    
                m2.pause();
                m3.pause();
                m4.play();});
                        
                add(mb1);
                add(mb2);
                add(mb3);
                add(mb4);
                
            } catch (IOException ex) {
                System.out.println(ex);
            }
                      

            }
 
    
        
        private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
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
       private void addButton(Image img,String title,Entrainement ent,Resources res) {
       
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false); 
       
       //delete

       
       //update
       /*
       Label Lmodifier = new Label("");
       Lmodifier.setUIID("NewsTopLine");
       Style modifStyle = new Style(Lmodifier.getUnselectedStyle());
       modifStyle.setFgColor(0xf7ad02);
       FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT,modifStyle);
       Lmodifier.setIcon(mFontImage);
       Lmodifier.setTextPosition(RIGHT);
       Lmodifier.addPointerPressedListener(l ->{
       System.out.println("hello"); 
       } );
       */
       cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(ta));//BoxLayout.encloseX(Lmodifier)));
       add(cnt);
      image.addActionListener(e -> new MDEntrainement(res,ent).show());
   }
    
    private void bindButtonSelection(Button b, Label arrow,Resources res) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
                new Music(res).show();         
                
            }
        });
    }
}
