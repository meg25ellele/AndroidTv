package com.example.myapplication;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

public class Image  implements Serializable {

    private static final String TAG = Image.class.getSimpleName();

    static final long serialVersionUID = 727566175075960653L;
    private int id;
    private String title;
    private String cardImageUrl;


    public Image(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public URI getCardImageURI(){
        try{
            return new URI(getCardImageUrl());
        } catch  (URISyntaxException e ){
            return null;
        }
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ": " + title;
    }
}

