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
public class Admin {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String cin;
    private String mot_de_passe;

    public Admin() {
    }

    public Admin( String nom, String prenom, String email, String cin, String mot_de_passe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.cin = cin;
        this.mot_de_passe = mot_de_passe;
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

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    @Override
    public String toString() {
        return "admin{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", cin=" + cin + '}';
    }
    
    
    
    
}
