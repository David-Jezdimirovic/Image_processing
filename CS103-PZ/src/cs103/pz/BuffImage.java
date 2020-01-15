/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs103.pz;

import java.awt.image.BufferedImage;

/**
 *
 * @author Lenovo
 */
public class BuffImage {
    
    private BufferedImage buffImage;
    private String putanja;

    public BuffImage() {
    }

    public BufferedImage getBuffImage() {
        return buffImage;
    }

    public void setBuffImage(BufferedImage buffImage) {
        this.buffImage = buffImage;
    }

    public String getPutanja() {
        return putanja;
    }

    public void setPutanja(String putanja) {
        this.putanja = putanja;
    }
    
}
