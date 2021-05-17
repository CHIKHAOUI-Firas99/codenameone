/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Models;

/**
 *
 * @author Aziz
 */
public class abonnement {
   private int id;
   private int id_user;
   private int id_nutrisioniste;
   private int id_coach;
   private int duree;
   private int prix;
   private String Date;

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public abonnement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_nutrisioniste() {
        return id_nutrisioniste;
    }

    public void setId_nutrisioniste(int id_nutrisioniste) {
        this.id_nutrisioniste = id_nutrisioniste;
    }

    public int getId_coach() {
        return id_coach;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    
}
