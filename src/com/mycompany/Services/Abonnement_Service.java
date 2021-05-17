/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Models.abonnement;
import com.mycompany.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aziz
 */
public class Abonnement_Service {
      public ArrayList<abonnement> abonnements;
    public static Abonnement_Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public Abonnement_Service() {
        req = new ConnectionRequest();
    }

    public static Abonnement_Service getInstance() {
        if (instance == null) {
            instance = new Abonnement_Service();
        }
        return instance;
    }

    public ArrayList<abonnement> findAll_nutri() {
           abonnements = new ArrayList<>();
        abonnement a = new abonnement();
        a.setPrix(29);
        a.setDuree(1);
          abonnement a2 = new abonnement();
        a2.setPrix(84);
        a2.setDuree(3);
          abonnement a3 = new abonnement();
        a3.setPrix(159);
        a3.setDuree(6);
          abonnement a4 = new abonnement();
        a4.setPrix(299);
        a4.setDuree(12);
        
        abonnements.add(a);
         abonnements.add(a2);
          abonnements.add(a3);
           abonnements.add(a4);
        
        return abonnements;
    
    }
        public ArrayList<abonnement> parseAbo(String jsonText) {
        try {
            abonnements = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                abonnement a1 = new abonnement();

              
                   float valeur = Float.parseFloat(obj.get("1").toString());
                a1.setPrix((int) valeur);
   
                a1.setDate("1 MOIS");
                
                abonnements.add(a1);      abonnement a3 = new abonnement();

              
                   float valeur3 = Float.parseFloat(obj.get("3").toString());
                a3.setPrix((int) valeur3);
   
                a3.setDate("3 MOIS");
                
                abonnements.add(a3);
               
  abonnement a6 = new abonnement();

              
                   float valeur6 = Float.parseFloat(obj.get("6").toString());
                a6.setPrix((int) valeur6);
   
                a6.setDate("6 MOIS");
                
                abonnements.add(a6);
           
        
                            
  abonnement a12 = new abonnement();

              
                   float valeur12 = Float.parseFloat(obj.get("12").toString());
                a12.setPrix((int) valeur12);
   
                a12.setDate("12 MOIS");
                
                abonnements.add(a12);
              
             
            
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return abonnements;
    }

  public void AddAbonnment(int id_u,int id_n,int duree) {
        String url = Statics.BASE_URL + "abonnement_nutritionniste_Mobile/"+id_u+"/"+id_n+"/"+duree;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
     
    }
      public ArrayList<abonnement> StatAbo() {
        String url = Statics.BASE_URL + "Stat_Mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                abonnements = parseAbo(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return abonnements;
    }
    
  
}
