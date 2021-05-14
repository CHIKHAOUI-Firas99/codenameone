/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Abonnement {
        public int id ;

    public int user_id ;
    public int nutritionist_id;
    public int coach_id ;
    public int duree ; 
    public Date date_debut ;

    public Abonnement() {
    }

    public Abonnement(int id, int user_id, int nutritionist_id, int coach_id, int duree, Date date_debut) {
        this.id = id;
        this.user_id = user_id;
        this.nutritionist_id = nutritionist_id;
        this.coach_id = coach_id;
        this.duree = duree;
        this.date_debut = date_debut;
    }

 

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNutritionist_id() {
        return nutritionist_id;
    }

    public void setNutritionist_id(int nutritionist_id) {
        this.nutritionist_id = nutritionist_id;
    }

    public int getCoach_id() {
        return coach_id;
    }

    public void setCoach_id(int coach_id) {
        this.coach_id = coach_id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "user_id=" + user_id + ", nutritionist_id=" + nutritionist_id + ", coach_id=" + coach_id + ", duree=" + duree + ", date_debut=" + date_debut + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
