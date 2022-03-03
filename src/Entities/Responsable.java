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
public class Responsable {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String cin;
    private String email_confirmed;
    private String mot_de_passe;
    private int service_id;
    private String nomService;

    public Responsable() {
    }

    public Responsable( String nom, String prenom, String email, String cin,int service_id, String mot_de_passe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.cin = cin;
        this.service_id=service_id;
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

    public String getEmail_confirmed() {
        return email_confirmed;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
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

    public void setEmail_confirmed(String email_confirmed) {
        this.email_confirmed = email_confirmed;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getNom_service() {
        return this.nomService;
    }

    public void setNom_service(String nom_service) {
        this.nomService = nom_service;
    }

    @Override
    public String toString() {
        return "Responsable{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", cin=" + cin + ", nomService=" + nomService + '}';
    }
    

   
}
