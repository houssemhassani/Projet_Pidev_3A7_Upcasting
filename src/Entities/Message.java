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
public class Message {
    private int id;
    private String message;
    private String from_nom;
    private String to_nom;
    private int from_id;
    private int to_id;

    public Message() {
    }

    public Message( String message, int from_id, int to_id) {
        this.message = message;
        this.from_id = from_id;
        this.to_id = to_id;
    }

    public int getId() {
        return id;
    }
   
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }

    public String getFrom_nom() {
        return from_nom;
    }

    public void setFrom_nom(String from_nom) {
        this.from_nom = from_nom;
    }

    public String getTo_nom() {
        return to_nom;
    }

    public void setTo_nom(String to_nom) {
        this.to_nom = to_nom;
    }

    @Override
    public String toString() {
        return "Message{" + "message=" + message + ", from_nom=" + from_nom + ", to_nom=" + to_nom + '}';
    }
    
    
    
}
