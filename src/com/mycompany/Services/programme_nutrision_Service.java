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
import com.mycompany.Models.programme_nutrision;

import com.mycompany.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aziz
 */
public class programme_nutrision_Service {
       public ArrayList<programme_nutrision> programme_nutrisions;
    public static programme_nutrision_Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public programme_nutrision_Service() {
        req = new ConnectionRequest();
    }

    public static programme_nutrision_Service getInstance() {
        if (instance == null) {
            instance = new programme_nutrision_Service();
        }
        return instance;
    }
        public ArrayList<programme_nutrision> parseprogramme_nutrision(String jsonText) {
        try {
            programme_nutrisions = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                programme_nutrision pn = new programme_nutrision();

                float id = Float.parseFloat(obj.get("id").toString());
                pn.setId((int) id);
              
                   float duree = Float.parseFloat(obj.get("duree").toString());
                pn.setDuree((int) duree);
                pn.setRepas1(obj.get("repas1").toString());
                pn.setRepas2(obj.get("repas2").toString());
                pn.setRepas3(obj.get("repas3").toString());
                pn.setRepas4(obj.get("repas4").toString());
                pn.setRepas5(obj.get("repas5").toString());
                  pn.setJourrepot(obj.get("jourrepot").toString());
              
    

             
                programme_nutrisions.add(pn);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return programme_nutrisions;
    }

    public ArrayList<programme_nutrision> findAll(int id_u) {
        String url = Statics.BASE_URL + "programmenutrition/programmenutrition_Mobile/"+id_u;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                programme_nutrisions = parseprogramme_nutrision(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return programme_nutrisions;
    }

 
}