package com.example.groupe1.ue236_groupe1_semaine2;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

public class Voeux extends AppCompatActivity {
    //Variables d'instance
    public String[] originaltext;
    public String[] formatedtext;
    public int id;

    //Créateur

    public Voeux() {
    }

    //Méthodes

    public void setOriginaltext(String[] text) {
        originaltext = text;
    }

    public void setFormatedtext(String[] prenom, int id) {
        for (int i = 0; i < prenom.length; i++) {
            formatedtext[i] = String.format(originaltext[id], prenom[i]);
        }
    }

    public String[] getOriginaltext() {
        return originaltext;
    }

    public String[] getFormatedtext() {
        return formatedtext;
    }
}
