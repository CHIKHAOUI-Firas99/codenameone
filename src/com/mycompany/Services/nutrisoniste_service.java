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
import com.mycompany.Models.nutisoniste;

import com.mycompany.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aziz
 */
public class nutrisoniste_service {
    public ArrayList<nutisoniste> nutisonistes;
    public static nutrisoniste_service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public nutrisoniste_service() {
        req = new ConnectionRequest();
    }

    public static nutrisoniste_service getInstance() {
        if (instance == null) {
            instance = new nutrisoniste_service();
        }
        return instance;
    }
        public ArrayList<nutisoniste> parsenutisoniste(String jsonText) {
        try {
            nutisonistes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                nutisoniste nu = new nutisoniste();

                float id = Float.parseFloat(obj.get("id").toString());
                nu.setId((int) id);
                 
                   

               
nu.setNom(obj.get("nom").toString());
                nu.setPrenom(obj.get("prenom").toString());
              
           
        
             
              
                
                // questionnaire q =new questionnaire();
                // q.setDescription_cat_qst((String) map.get("description_cat_qst"));
             
                nutisonistes.add(nu);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return nutisonistes;
    }

    public ArrayList<nutisoniste> findAll() {
        String url = Statics.BASE_URL + "nutritionnistes_Mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                nutisonistes = parsenutisoniste(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nutisonistes;
    }

  public void Add_program(int id_u,int id_a,int id_n , String Objectif,String Blessure,String ne_manger_pas,String supp , String malade,String age , String taille,String poids,String sexe) {
        String url = Statics.BASE_URL + "info/user/nutrition/add_info?id_u="+id_u+"&id_a="+id_a+"&id_n="+id_n+"&Objectif="+Objectif+"&Blessure="+Blessure+"&ne_manger_pas="+ne_manger_pas+"&supplement="+supp+"&maladie="+malade+"&age="+age+"&taille="+taille+"&poids="+poids+"&sexe="+sexe;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                nutisonistes = parsenutisoniste(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
}