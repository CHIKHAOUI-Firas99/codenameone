/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import com.codename1.components.MultiButton;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Coach;
import entities.Entrainement;
import entities.User;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.Statice;

/**
 *
 * @author 21652
 */
public class ServiceEntrainements {
    public static ServiceEntrainements instance = null;
    public static boolean ResultOK;
    
    private ConnectionRequest req;
    
    
  public static ServiceEntrainements getInstance(){
      if(instance == null)
        instance = new ServiceEntrainements();  
        return instance;
    }
  
  public ServiceEntrainements(){
      
  
  }
  
  public void AjoutEntrainnement(String Jour,String Heure,String Titre,String Meet){
      req = new ConnectionRequest();
      String url = Statice.BASE_URL+"/entrainement/coach/mobile/1/"+Jour+"/"+Heure+"/"+Titre+"/"+Meet;
      req.setUrl(url);
      req.addResponseListener((e) ->{ 
          String str = new String(req.getResponseData());    
          System.out.print("data"+str); 
      });
        NetworkManager.getInstance().addToQueueAndWait(req);
      


}
    
  public ArrayList<Entrainement> showentrainements() {
        req = new ConnectionRequest();
        Coach coach = new Coach();
        coach.setId(1);
        ArrayList<Entrainement> result = new ArrayList<>();
      String url = Statice.BASE_URL+"/entrainement/mobile/"+coach.id;
      req.setUrl(url);        
  
      req.addResponseListener(new ActionListener<NetworkEvent>() {
          @Override
          public void actionPerformed(NetworkEvent evt) {
              try {       
                  JSONParser jsonp;
                  jsonp = new JSONParser();
                  System.out.println("okok11");
                  Map<String,Object>mapEntrainements = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                  System.out.println("okok22");
                  List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapEntrainements.get("root");
                  System.out.println("okok33");
                  if(mapEntrainements==null){
                      System.out.println("okkk");
                        }
                  for(Map<String,Object> obj : listOfMaps){
                      //System.out.println("okok44");
                      Entrainement ent = new Entrainement();
                      float id = Float.parseFloat(obj.get("id").toString());
                      String titre = obj.get("titre").toString();
                      String jour = obj.get("jour").toString();
                      float heure = Float.parseFloat(obj.get("heure").toString());
                      String type = obj.get("type").toString();
                      String meet = obj.get("meet").toString();                   
                      ent.setId((int)id);
                      ent.setCoach_id(1);
                      ent.setTitre(titre);
                      ent.setJour(jour);
                      ent.setHeure((int) heure);
                      ent.setType(type);
                      ent.setMeet(meet);  
                      
                      result.add(ent);
                        
                  }
              } catch (IOException ex) {
                  ex.printStackTrace();
              }
              
          }
  
          });
       
      NetworkManager.getInstance().addToQueueAndWait(req);
      System.out.println(result);
      return result;
      
  }
  
  public Entrainement DetailEntrainement(int id,Entrainement ent){
  
      req = new ConnectionRequest();
      String url = Statice.BASE_URL+"/entrainement/coach/"+id;
      req.setUrl(url);        
      req.addResponseListener(new ActionListener<NetworkEvent>() {
          @Override
          public void actionPerformed(NetworkEvent evt) {
              try {       
                  JSONParser jsonp;
                  jsonp = new JSONParser();
                  Map<String,Object>mapEntrainements = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                      float id = Float.parseFloat(mapEntrainements.get("id").toString());
                      ent.setId((int) id);
                      ent.setTitre(mapEntrainements.get("titre").toString()); 
                      ent.setJour(mapEntrainements.get("jour").toString());
                      float heure = Float.parseFloat(mapEntrainements.get("heure").toString());
                      ent.setHeure((int)heure );
                      ent.setType(mapEntrainements.get("type").toString());
                      ent.setMeet(mapEntrainements.get("meet").toString());                   
                       
              } catch (IOException ex) {
                  ex.printStackTrace();
              }
              
            System.out.println("data : "+req.getResponseData());
              
          }
  
          });
       
      NetworkManager.getInstance().addToQueueAndWait(req);
      System.out.println(ent);
      return ent;
  
  
  
  
  }
  
    public Entrainement UpEntrainement(Entrainement ent){
        req = new ConnectionRequest();
        String url = Statice.BASE_URL+"/entrainement/coach/mobile/"+ent.getId()+"/edit?titre="+ent.getTitre()+"&jour="+ent.getJour()+"&heure="+ent.getHeure()+"&type="+ent.getType()+"&meet="+ent.getMeet();
        req.setUrl(url);        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
          @Override
          public void actionPerformed(NetworkEvent evt) {
              ResultOK = req.getResponseCode()== 200;
              req.removeResponseListener(this);                 
        }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);
      return ent;
        
        
}
        public Entrainement SUPPEntrainement(Entrainement ent){
        req = new ConnectionRequest();
        String url = Statice.BASE_URL+"/entrainement/coach/mobile/delete/"+ent.getId();
        req.setUrl(url);        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
          @Override
          public void actionPerformed(NetworkEvent evt) {
              ResultOK = req.getResponseCode()== 200;
              req.removeResponseListener(this);                 
        }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);
      return ent;
      
}
        
     public Coach Profile(){
              Coach coach = new Coach(); 
              User user = new User();
              coach.setId(1);
              req = new ConnectionRequest(); 
              String url = Statice.BASE_URL+"/entrainement/coach/mobile/profile/"+coach.id;
              req.setUrl(url);
              req.addResponseListener(new ActionListener<NetworkEvent>() {
          @Override
          public void actionPerformed(NetworkEvent evt) {
              try {       
                  JSONParser jsonp;
                  jsonp = new JSONParser();
                  Map<String,Object>mapCoach = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                      float id = Float.parseFloat(mapCoach.get("age").toString());
                      float poid = Float.parseFloat(mapCoach.get("poid").toString());
                      float hauteur = Float.parseFloat(mapCoach.get("hauteur").toString());
                      coach.setAge((int) id);
                      coach.setPoid((int) poid );
                      coach.setHauteur((int)hauteur );
                      coach.setFacebook(mapCoach.get("facebook").toString()); 
                      coach.setInsta(mapCoach.get("insta").toString());
                      coach.setGmail(mapCoach.get("gmail").toString());
                      if(mapCoach.get("bio") == null){
                         coach.setBio(""); 
                      }
                      else{
                      coach.setBio(mapCoach.get("bio").toString());
                            }
                      coach.setImage(mapCoach.get("image").toString());                 

                      
              
              } catch (IOException ex) {
                  ex.printStackTrace();
              }
             
          }
          
        });
      NetworkManager.getInstance().addToQueueAndWait(req);
      System.out.println(coach);
      return coach;           
           
        }
     public Coach EditProfile(Coach coach){
              req = new ConnectionRequest(); 
              String url = Statice.BASE_URL+"/entrainement/coach/mobile/profile/edit/"+coach.id+"?Age="+coach.age+"&Poid="+coach.poid+"&Hauteur="+coach.hauteur+"&facebook="+coach.facebook+"&insta="+coach.insta+"&gmail="+coach.gmail+"&bio="+coach.bio+"&image="+coach.image;
              req.setUrl(url);
              req.addResponseListener(new ActionListener<NetworkEvent>() {
          @Override
          public void actionPerformed(NetworkEvent evt) {
              ResultOK = req.getResponseCode()== 200;
              req.removeResponseListener(this);                 
        }
        });
      NetworkManager.getInstance().addToQueueAndWait(req);
      return coach;
              
     }        


     
}        
    
/*
        public boolean Classement(){
        req = new ConnectionRequest();
        String url = Statice.BASE_URL+"/classement";
        req.setUrl(url); 
        req.addResponseListener(new ActionListener<NetworkEvent>() {
          @Override
          public void actionPerformed(NetworkEvent evt) {
              ResultOK = req.getResponseCode()== 200;
              req.removeResponseListener(this);                 
        }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);
      return ResultOK;
     
     
            }
     */

        






  
                 
      /*      
      Coach coach = new Coach();
      coach.setId(1);
      String url = Statice.BASE_URL+"/entrainement/1";
      req.setUrl(url);
      req.addResponseListener((NetworkEvent e)->{
          ArrayList str = new ArrayList();
          JSONParser jsonp;
          jsonp = new JSONParser();      
      });
            NetworkManager.getInstance().addToQueueAndWait(req);
  
}
}
    */      
