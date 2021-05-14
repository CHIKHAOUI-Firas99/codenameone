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
public class Coach {
    public int id ;
    public int user_id ;
    public String speciality ;
    public double salary;
    public int age ;
    public int poid ;
    public int hauteur ;
    public String facebook ;
    public String insta;
    public String gmail;
    public String bio;
    public String image ;

    public Coach() {
    }

    public Coach(int id, int user_id, String speciality, double salary, int age, int poid, int hauteur, String facebook, String insta, String gmail, String bio, String image) {
        this.id = id;
        this.user_id = user_id;
        this.speciality = speciality;
        this.salary = salary;
        this.age = age;
        this.poid = poid;
        this.hauteur = hauteur;
        this.facebook = facebook;
        this.insta = insta;
        this.gmail = gmail;
        this.bio = bio;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Coach{" + "id=" + id + ", user_id=" + user_id + ", speciality=" + speciality + ", salary=" + salary + ", age=" + age + ", poid=" + poid + ", hauteur=" + hauteur + ", facebook=" + facebook + ", insta=" + insta + ", gmail=" + gmail + ", bio=" + bio + ", image=" + image + '}';
    }
    
    
    
    
    
    
    
}
