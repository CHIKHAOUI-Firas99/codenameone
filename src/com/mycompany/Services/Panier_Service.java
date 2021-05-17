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
import com.mycompany.Models.Panier;

import com.mycompany.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author ikram
 */
public class Panier_Service {
        public ArrayList<Panier> Paniers;
    public static Panier_Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public Panier_Service() {
        req = new ConnectionRequest();
    }

    public static Panier_Service getInstance() {
        if (instance == null) {
            instance = new Panier_Service();
        }
        return instance;
    }
        public ArrayList<Panier> parsePanier(String jsonText) {
        try {
            Paniers = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                Panier panier = new Panier();

                float id = Float.parseFloat(obj.get("id").toString());
                panier.setId((int) id);
              
                   float quantite = Float.parseFloat(obj.get("quantite").toString());
                panier.setQuantite((int) quantite);
Map<String, Object> map1 = ((Map<String, Object>) obj.get("produit"));
                for (Entry<String, Object> entry : map1.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();

                    if(key.equals("prix"))
                    {
                       float prix = Float.parseFloat(value.toString());
                panier.setPrix((int) prix);  
                    }
                     if(key.equals("labelle"))
                    {
                      panier.setLabelle(value.toString());  
                    }
                      if(key.equals("image"))
                    {
                          panier.setImage(value.toString());     
                    }
                    
                }
               
    

             
                Paniers.add(panier);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Paniers;
    }

    public ArrayList<Panier> findAll(int id_u) {
        String url = Statics.BASE_URL + "panier_Mobile/"+id_u;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Paniers = parsePanier(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Paniers;
    }

  public void Addproduit(int id_u,int id_p) {
        String url = Statics.BASE_URL + "panier_add_Mobile/"+id_u+"/"+id_p;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             //   Paniers = parsePanier(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
     
    }
    public void Removeproduit(int id) {
        String url = Statics.BASE_URL + "panier_Delete_Mobile/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             //   Paniers = parsePanier(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
    }
    public int total(int id)
    {
        int total=0;
        Paniers=findAll(id);
        for (Panier Panier1 : Paniers) {
            total+=(Panier1.getPrix()*Panier1.getQuantite());
            
        }
        return total;
    }
}