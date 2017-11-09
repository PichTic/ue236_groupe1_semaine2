package com.example.groupe1.ue236_groupe1_semaine2;

/**
 * Created by Manu on 09/11/2017.
 */

public class contact {
    //Variables d'instance
    public String nom;
    public String prenom;
    public String numero;
    public boolean selected;
    public int id;

    //Variable de classe
    public static int nbcontact = 0;

    //Constructeur

    public contact(String nom, String prenom, String numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.selected = false;
        this.id = nbcontact;
        incrementnbcontact();
    }

    //Méthodes

    public boolean isSelected() {
        return selected;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public static void incrementnbcontact() {
        nbcontact++;
    }
}
