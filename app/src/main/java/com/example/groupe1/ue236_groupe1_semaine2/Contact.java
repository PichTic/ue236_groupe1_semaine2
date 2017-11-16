package com.example.groupe1.ue236_groupe1_semaine2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class Contact implements Parcelable {
    //Variables d'instance
    private String nom;
    private String tel;
    private String first_name;
    private String last_name;
    private boolean is_checked;
    private boolean changenom;

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(nom);
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
        this.tel = in.readString();
    }

    //Constructeur
    public Contact(String nom, String prenom, String tel) {
        this.nom = nom;
        this.tel = tel;
        this.is_checked = false;
        this.changenom = false;

    }

    public Contact() {
        this.is_checked = false;
        this.changenom = false;
    }

    //Méthodes

    public void setNom(String nom) {
        this.nom = nom;
    }


    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setIsChecked(boolean is_checked) {
        this.is_checked = is_checked;
    }

    public void setChangenom() {
        if(this.changenom) {
            this.changenom = false;
        }
        else {
            this.changenom = true;
        }
    }

    public boolean isChangenom() {
        return changenom;
    }

    public void changeIsChecked() {
        if(this.is_checked) {
            this.is_checked = false;
        }
        else {
            this.is_checked = true;
        }
    }

    public String getNom() {
        return nom;
    }

    public String getTel() {
        return tel;
    }

    public boolean getIsChecked() {
        return is_checked;
    }
}
