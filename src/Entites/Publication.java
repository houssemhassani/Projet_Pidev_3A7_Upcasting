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
public class Publication {
    private int id;
    private String status;
    private String photo;
    private int citoyen_id;
    private int employee_id;
    private String utilisateur_nom;
    private boolean confirm_publication; 

    public Publication() {
    }

    public Publication( String status, String photo, String utilisateur_nom) {
        this.status = status;
        this.photo = photo;
        this.utilisateur_nom = utilisateur_nom;
        this.confirm_publication = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    

   
    public String getUtilisateur_nom() {
        return utilisateur_nom;
    }

    public void setUtilisateur_nom(String utilisateur_nom) {
        this.utilisateur_nom = utilisateur_nom;
    }

    public boolean isConfirm_publication() {
        return confirm_publication;
    }

    public void setConfirm_publication(boolean confirm_publication) {
        this.confirm_publication = confirm_publication;
    }

    public int getCitoyen_id() {
        return citoyen_id;
    }

    public void setCitoyen_id(int citoyen_id) {
        this.citoyen_id = citoyen_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "Publication{" + "status=" + status + ", photo=" + photo + ", utilisateur_nom=" + utilisateur_nom + ", confirm_publication=" + confirm_publication + '}';
    }

    public Object toURI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getAbsolutePath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
    
    
}
