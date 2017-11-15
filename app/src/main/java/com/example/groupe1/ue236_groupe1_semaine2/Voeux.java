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
        for (int i = 0; i < originaltext.length; i++) {
            originaltext[i] = String.format(originaltext[i], "<prenom>");
        }
    }

    public void setFormatedtext(String[] prenom) {
        //Création d'une variable temporaire pour insérer la phrase personnalisée des prénoms choisis
        String[] temp = new String[prenom.length];
        for (int i = 0; i < prenom.length; i++) {
            temp[i] = String.format(originaltext[id], prenom[i]);
        }
        formatedtext = temp;
    }

    public String[] getOriginaltext() {
        return originaltext;
    }

    public String[] getFormatedtext() {
        return formatedtext;
    }
}
