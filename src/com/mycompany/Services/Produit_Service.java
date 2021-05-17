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
import com.mycompany.Models.Produit;
import com.mycompany.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ikram
 */
public class Produit_Service{
        public ArrayList<Produit> Produits;
    public static Produit_Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public Produit_Service() {
        req = new ConnectionRequest();
    }

    public static Produit_Service getInstance() {
        if (instance == null) {
            instance = new Produit_Service();
        }
        return instance;
    }
        public ArrayList<Produit> parseProduit(String jsonText) {
        try {
            Produits = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                Produit produit = new Produit();

                float id = Float.parseFloat(obj.get("id").toString());
                produit.setId((int) id);
                  float prix = Float.parseFloat(obj.get("prix").toString());
                produit.setPrix((int) prix);
                   float quantite = Float.parseFloat(obj.get("quantite").toString());
                produit.setQuantite((int) quantite);

               
produit.setImage(obj.get("image").toString());
                produit.setType(obj.get("type").toString());
                produit.setLabelle(obj.get("labelle").toString());
           
        
             
              
                
                // questionnaire q =new questionnaire();
                // q.setDescription_cat_qst((String) map.get("description_cat_qst"));
             
                Produits.add(produit);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Produits;
    }

    public ArrayList<Produit> findAll() {
        String url = Statics.BASE_URL + "Mobile_Produit";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produits = parseProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produits;
    }
       public void faire_pdf() {
        String url = Statics.BASE_URL + "admin/produit/list/pdf";
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


}