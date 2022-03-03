/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Asus
 */
public class Citoyen {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String cin;
    private long num_tel;
    private String ville;
    private String photo;
    private String mot_de_passe;
    private boolean email_confirmed;

    public Citoyen(String nom, String prenom, String email, String cin, long num_tel, String ville, String photo,String mot_de_passe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.cin = cin;
        this.num_tel = num_tel;
        this.ville = ville;
        this.photo=photo;
        this.mot_de_passe = mot_de_passe;
    }

    public Citoyen() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getCin() {
        return cin;
    }

    public long getNum_tel() {
        return num_tel;
    }

    public String getVille() {
        return ville;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public boolean isEmail_confirmed() {
        return email_confirmed;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNum_tel(long num_tel) {
        this.num_tel = num_tel;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setEmail_confirmed(boolean email_confirmed) {
        this.email_confirmed = email_confirmed;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Citoyen{" + "id= " + id + ", nom= " + nom + ", prenom= " + prenom + ", email= " + email + ", cin= " + cin + ", num_tel= " + num_tel + ", ville= " + ville + '}';
    }
    
    
    
}
