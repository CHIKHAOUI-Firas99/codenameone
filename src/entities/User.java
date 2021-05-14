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
public class User {
    int id  ;
    String email ; 
    String roles ;
    String password ;
    String activation_token ;
    String nom ;
    String prenom ;
    String tel ; 
    Date datenaissance ; 

    public User() {
    }

    public User(int id, String email, String roles, String password, String activation_token, String nom, String prenom, String tel, Date datenaissance) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.activation_token = activation_token;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.datenaissance = datenaissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", roles=" + roles + ", password=" + password + ", activation_token=" + activation_token + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", datenaissance=" + datenaissance + '}';
    }
                
    
    
    
    
    
    
    
    
    
}
