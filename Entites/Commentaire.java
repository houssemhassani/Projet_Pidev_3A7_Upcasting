/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author Ghassen-pc
 */
public class Commentaire {
    private int id;
    private String content;
   
    private int id_citoyen;
    private int id_employee;
    private int id_publication;
    private String nom_user;

    public Commentaire() {
    }

    public Commentaire( String content,  int id_publication, int id_citoyen, int id_employee) {
       
        this.content = content;
        this.id_citoyen=id_citoyen;
        this.id_employee=id_employee;
        this.id_publication = id_publication;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

   

    public int getId_citoyen() {
        return id_citoyen;
    }

    public void setId_citoyen(int id_citoyen) {
        this.id_citoyen = id_citoyen;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "content=" + content + ", id_publication=" + id_publication + ", nom_user=" + nom_user + '}';
    }

    
    
    
    
}
