/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author LENOVO
 */
public class Entrainement {
    
    public int id ;
    public int coach_id ;
    public String titre;
    public String jour;
    public int heure;
    public String type;
    public String meet; 

    public Entrainement() {
    }

    public Entrainement(int id, int coach_id, String titre, String jour, int heure, String type, String meet) {
        this.id = id;
        this.coach_id = coach_id;
        this.titre = titre;
        this.jour = jour;
        this.heure = heure;
        this.type = type;
        this.meet = meet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoach_id() {
        return coach_id;
    }

    public void setCoach_id(int coach_id) {
        this.coach_id = coach_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeet() {
        return meet;
    }

    public void setMeet(String meet) {
        this.meet = meet;
    }

    @Override
    public String toString() {
        return "Entrainement{" + "id=" + id + ", coach_id=" + coach_id + ", titre=" + titre + ", jour=" + jour + ", heure=" + heure + ", type=" + type + ", meet=" + meet + '}';
    }
    
    
    
    
    
    
}
