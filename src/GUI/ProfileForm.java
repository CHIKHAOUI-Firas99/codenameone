/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package GUI;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MalformedURLException;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Coach;
import java.io.IOException;
import com.codename1.io.Util;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.util.ImageIO;
import entities.Entrainement;
import java.io.OutputStream;
import services.ServiceEntrainements;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {
    Image m;
    

    public ProfileForm(Resources res) throws IOException {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
       
        
        
        Image img = res.getImage("breadcrumb-bg.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("", res.getImage("facebook-logo.png"), "BottomPad");
        facebook.addPointerPressedListener(l ->
        {
       execute(String.valueOf(ServiceEntrainements.getInstance().Profile().facebook));
             });
        Label instagram = new Label("", res.getImage("twitter-logo.png"), "BottomPad");
                instagram.addPointerPressedListener(l ->
        {
       execute(String.valueOf(ServiceEntrainements.getInstance().Profile().insta));
             });
        facebook.setTextPosition(BOTTOM);
        instagram.setTextPosition(BOTTOM);
         
           Coach coach = new Coach();
           coach.setId(1);
           
            m = Image.createImage(FileSystemStorage.getInstance().getAppHomePath()+"/pi/public/uploads/images/"+ServiceEntrainements.getInstance().Profile().image);
           
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label(m, "")),
                            instagram
                    )
                )
        ));


        TextField Age = new TextField(String.valueOf(ServiceEntrainements.getInstance().Profile().age));
        Age.setUIID("TextFieldBlack");
        addStringValue("Age", Age);
        
        TextField Poid = new TextField(String.valueOf(ServiceEntrainements.getInstance().Profile().poid));
        Poid.setUIID("TextFieldBlack");
        addStringValue("Poid", Poid);
    
       TextField Hauteur = new TextField(String.valueOf(ServiceEntrainements.getInstance().Profile().hauteur));
        Hauteur.setUIID("TextFieldBlack");
        addStringValue("Hauteur", Hauteur);
 
        TextField email = new TextField("sandeep@gmail.com", ServiceEntrainements.getInstance().Profile().gmail, 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        TextField FB = new TextField(String.valueOf(ServiceEntrainements.getInstance().Profile().facebook));
        FB.setUIID("TextFieldBlack");
        addStringValue("FaceBook", FB);
        
        TextField Insta = new TextField(String.valueOf(ServiceEntrainements.getInstance().Profile().insta));
        Insta.setUIID("TextFieldBlack");
        addStringValue("Instagram", Insta);
        
        TextField Bio = new TextField(String.valueOf(ServiceEntrainements.getInstance().Profile().bio));
        Bio.setUIID("TextFieldBlack");
        addStringValue("Bio", Bio);
        
        TextField ImageCoach = new TextField(String.valueOf(ServiceEntrainements.getInstance().Profile().image));
        ImageCoach.setUIID("NewsTopLine");
        addStringValue("Image", ImageCoach);
         ImageCoach.setEditable(false); 
        
        /*
        TextField password = new TextField("sandeep", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);

        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));
        */
   ActionListener callback = e->{
   if (e != null && e.getSource() != null) {
       String filePath = (String)e.getSource();

       //  Now do something with this file
   }
};
   
       Button AMG = new Button("Image");
       AMG.addActionListener(e ->{    
        String filePath = Capture.capturePhoto();
        if (filePath != null) {
            try {
                String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath()+ "pi/public/uploads/images/" + System.currentTimeMillis() +  ".jpg";
                Image image = Image.createImage(filePath);
                OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored );
                ImageIO.getImageIO().save(image, os, ImageIO.FORMAT_JPEG, 0.9f);
                os.close(); 
                ImageCoach.setText(pathToBeStored.substring(pathToBeStored.lastIndexOf("/")+ 1));
                 int height = Display.getInstance().convertToPixels(11.5f);
                 int width = Display.getInstance().convertToPixels(14f);
                Label imagelab = new Label();
                imagelab.setIcon(image.fill(width, height));
                add(imagelab);
                super.revalidate();     
        } catch (Exception ex) {
                System.out.println(ex);
            }
        }
               }); 
       add(AMG); 
   
   
   
   
   
       Button Upload = new Button("Modifier");
       Upload.addActionListener(e ->{  
            try {
                InfiniteProgress pi  = new InfiniteProgress();
                final Dialog iDialog = pi.showInfiniteBlocking();
                float id = Float.parseFloat(Age.getText().toString());
                float poid = Float.parseFloat(Poid.getText().toString());
                float hauteur = Float.parseFloat(Hauteur.getText().toString());
                coach.setAge((int) id);
                coach.setPoid((int) poid );
                coach.setHauteur((int)hauteur );
                coach.setFacebook(FB.getText().toString());
                coach.setInsta(Insta.getText().toString());
                coach.setGmail(email.getText().toString());
                coach.setBio(Bio.getText().toString());
                coach.setImage(ImageCoach.getText().toString());
                ServiceEntrainements.getInstance().EditProfile(coach);
                iDialog.dispose();
                refreshTheme();
                new ProfileForm(res).show();
                
                System.out.println("ok");
            } catch (IOException ex) {
                System.out.println(ex);
            }
       
       });
        add(Upload);

    }
           
          
           
           /*
       System.out.println(fs.getAppHomePath());
       String File = Capture.capturePhoto();
       String fileName ="Image";
       String FilePath = ImageDire +fileName; 
       System.out.println(fs.getAppHomePath()+File);
            try {
                Util.copy(fs.openInputStream(File), fs.openOutputStream(FilePath));
               
                System.out.println("okkkkk");
                
                String fileName = "test";
                String FilePath = ImageDire + fileName;
                System.out.println(fs.getAppHomePath() + File);
                try {
                Util.copy(fs.openInputStream(fileName), fs.openOutputStream(FilePath));
                } catch (IOException ex) {
                System.out.println(ex);
                }
               } catch (IOException ex) {
                System.out.println(ex);
            }
                */

    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }
    private void changeimage(Image m, String s){
        
        try {
            m.createImage(s);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}

/*
        Button image = new Button("choisir votre image");
        image.addActionListener(e->{
        //String path = Capture.capturePhoto(LEFT, RIGHT);
        String path = Capture.capturePhoto();
        System.out.println(path);  
         File src = new File (path);
        });
        add(image);

*/