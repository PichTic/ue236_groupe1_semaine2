package com.example.groupe1.ue236_groupe1_semaine2;


import java.util.ArrayList;

public class Contact {
    private String nom;
    private String prenom;
    private String tel;
    private boolean is_checked;


    public Contact(String nom, String prenom, String tel, boolean is_checked) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.is_checked = is_checked;
    }

    public static ArrayList<Contact> getContacts () {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact("Pichot", "Vincent", "0688320407", false));
        contacts.add(new Contact("Michaud", "Emmanuel", "0633216547", false));
        contacts.add(new Contact("Latouille", "Melissa", "0656458957", false));
        contacts.add(new Contact("Lombardi", "Marion", "0678956421", false));
        return contacts;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setIsChecked(boolean is_checked) {
        this.is_checked = is_checked;
    }

    public boolean getIsChecked() {
        return is_checked;
    }
}
