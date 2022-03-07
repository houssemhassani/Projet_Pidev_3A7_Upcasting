/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javafx.scene.image.ImageView;

/**
 *
 * @author Ghassen-pc
 */
class ChessPiece {
    private final String name;
    private final ImageView image;
    private int count;

    public ChessPiece(String name, ImageView image, int count) {
        this.name = name;
        this.image = image;
        this.count = count;

        // Resize the image, if necessary
        this.image.setFitHeight(25);
        this.image.setFitWidth(20);

    }

    public String getName() {
        return name;
    }

    public ImageView getImage() {
        return image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
}
