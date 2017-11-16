package com.example.groupe1.ue236_groupe1_semaine2;

/**
 * Created by Toorop on 11/11/2017.
 */

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * On définit notre Adapter personnalisé, qui sera basé sur un ArrayList
 * contenant des objets Contacts
 */
public class CustomContactsAdapter extends ArrayAdapter<Contact> {
    private static final String TAG = "CustomContactsAdapter";
    Context context;

    /**
     * Constructeur
     *
     * @param context
     * @param contacts
     */
    public CustomContactsAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }


    /**
     * On donne à la ListView l'adapter pour qu'il le nourrisse (inflate)
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // vérifie la présence d'une vue et la remplie le cas echéant
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }

        // on récupère le contact courant
        Contact contact = getItem(position);

        // et on remplie la vue avec getNom
        TextView Nom = (TextView) convertView.findViewById(R.id.Nom);
        Nom.setText(contact.getNom());

        return convertView;
    }


}

