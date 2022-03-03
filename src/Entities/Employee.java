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
public class Employee {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String cin;
    private String photo;
     private int equipe_id;
    private int service_id;
    private int role;
    private String mot_de_passe;

    public Employee() {
    }

    public Employee( String nom, String prenom, String email, String cin, int equipe_id, int service_id, String mot_de_passe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.cin = cin;
        this.equipe_id = equipe_id;
        this.service_id = service_id;
        this.mot_de_passe = mot_de_passe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public int getEquipe_id() {
        return equipe_id;
    }

    public void setEquipe_id(int equipe_id) {
        this.equipe_id = equipe_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }
    public int getRole() {
        return service_id;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", cin=" + cin + ", equipe_id=" + equipe_id + ", service_id=" + service_id +'}';
    }
    
   
}
