package com.example.groupe1.ue236_groupe1_semaine2;

/**
 * Created by Toorop on 11/11/2017.
 */

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class CustomContactsAdapter extends ArrayAdapter<Contact> {
    private static final String TAG = "CustomContactsAdapter";
    Context context;

    public CustomContactsAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }


        Contact contact = getItem(position);

        TextView Nom = (TextView) convertView.findViewById(R.id.Nom);

        Nom.setText(contact.getNom());

        return convertView;
    }


}

