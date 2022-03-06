/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author hamza
 */
public class notification {
    int id_notification;
    int num_notificatione;
    String type_notification;
    String email_notification;
    int id_demande ;
    String demande;

    public int getId_demande() {
        return id_demande;
    }

    public notification(int id_notification, int num_notificatione, String type_notification, String email_notification, int id_demande) {
        this.id_notification = id_notification;
        this.num_notificatione = num_notificatione;
        this.type_notification = type_notification;
        this.email_notification = email_notification;
        this.id_demande = id_demande;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public notification(int id_notification, int num_notificatione, String type_notification, String email_notification) {
        this.id_notification = id_notification;
        this.num_notificatione = num_notificatione;
        this.type_notification = type_notification;
        this.email_notification = email_notification;
    }

   

    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public int getNum_notificatione() {
        return num_notificatione;
    }

    public void setNum_notificatione(int num_notificatione) {
        this.num_notificatione = num_notificatione;
    }

    public String getType_notification() {
        return type_notification;
    }

    public void setType_notification(String type_notification) {
        this.type_notification = type_notification;
    }

    public String getEmail_notification() {
        return email_notification;
    }

    public void setEmail_notification(String email_notification) {
        this.email_notification = email_notification;
    }

    

    public String getDemande() {
        return demande;
    }

    public void setDemande(String demande) {
        this.demande = demande;
    }

    @Override
    public String toString() {
        return "notification{" + "num_notificatione=" + num_notificatione + ", type_notification=" + type_notification + ", email_notification=" + email_notification + ", id_demande=" + id_demande + ", demande=" + demande + '}';
    }


    

    
    
    
    
    
}
