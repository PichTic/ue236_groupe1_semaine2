package com.example.groupe1.ue236_groupe1_semaine2;


public class Voeux {
    //Variables de classe
    private String prenom;
    private String[] phrase_de_voeux = new String[10];

    //Constructeur

    //Constructeur
    public Voeux(String prenom) {
        this.prenom = prenom;
        phrase_de_voeux[0] = "" + prenom + "";
        phrase_de_voeux[1] = "" + prenom + "";
        phrase_de_voeux[2] = "" + prenom + "";
        phrase_de_voeux[3] = "" + prenom + "";
        phrase_de_voeux[4] = "" + prenom + "";
        phrase_de_voeux[5] = "" + prenom + "";
        phrase_de_voeux[6] = "" + prenom + "";
        phrase_de_voeux[7] = "" + prenom + "";
        phrase_de_voeux[8] = "" + prenom + "";
        phrase_de_voeux[9] = "" + prenom + "";
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}


