package com.example.groupe1.ue236_groupe1_semaine2;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class Contact implements Parcelable {
    //Variables d'instance
    private String nom;
    private String prenom;
    private String tel;
    private boolean is_checked;



    //Constructeur
    public Contact(String nom, String prenom, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.is_checked = false;
    }

    //Méthodes

    public static ArrayList<Contact> getContacts() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact("Pichot", "Vincent", "0688320407"));
        contacts.add(new Contact("Michaud", "Emmanuel", "0633216547"));
        contacts.add(new Contact("Latouille", "Melissa", "0656458957"));
        contacts.add(new Contact("Lombardi", "Marion", "0678956421"));
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

    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(tel);
    }
    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>()
    {
        @Override
        public Contact createFromParcel(Parcel source)
        {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size)
        {
            return new Contact[size];
        }
    };

    public Contact(Parcel in) {
        this.nom = in.readString();
        this.prenom = in.readString();
        this.tel = in.readString();
    }

}

