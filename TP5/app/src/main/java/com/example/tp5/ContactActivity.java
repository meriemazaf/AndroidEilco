package com.example.tp5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    List<Contact> contacts =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //Lookup the recyclerView in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        //Initialisation des contacts
        contacts.add(new Contact("Jean", "Pierre", "https://histoire-image.org/sites/default/jeanne-arc-sacre-charlesvii.jpg"));
        contacts.add(new Contact("Jeanne", "D'arc", "https://histoire-image.org/sites/default/jeanne-arc-sacre-charlesvii.jpg"));
        contacts.add(new Contact("Pierre", "Menez", "https://histoire-image.org/sites/default/jeanne-arc-sacre-charlesvii.jpg"));
        contacts.add(new Contact("Arthur", "Rimbaut", "https://histoire-image.org/sites/default/jeanne-arc-sacre-charlesvii.jpg"));
        contacts.add(new Contact("Richard", "Coeur de lion", "https://histoire-image.org/sites/default/jeanne-arc-sacre-charlesvii.jpg"));
        contacts.add(new Contact("Zinedine", "Zidane", "https://histoire-image.org/sites/default/jeanne-arc-sacre-charlesvii.jpg"));
        contacts.add(new Contact("Yannick", "Noah", "https://histoire-image.org/sites/default/jeanne-arc-sacre-charlesvii.jpg"));

        //Création d'un adapter avec initialisation du constructeur avec notre liste de contacts
        ContactsAdapter adapter = new ContactsAdapter(this,contacts);
        //On notifie au recyclerview notre adapter
        rvContacts.setAdapter(adapter);
        //On déclare quelle type de LayoutManger on désire
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}