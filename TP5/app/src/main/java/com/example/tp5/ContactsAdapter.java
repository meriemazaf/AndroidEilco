package com.example.tp5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private  final List<Contact> mContacts;
    private Context context ;


    public ContactsAdapter(Context context,List<Contact> contacts){
        this.context = context;
        mContacts = contacts;

    }

    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {

        Contact contact = mContacts.get(position);

        TextView firstNameTextView = holder.firstNameTextView;
        firstNameTextView.setText(contact.getPrenom());

        TextView lastNameTextView = holder.lastNameTextView;
        lastNameTextView.setText(contact.getNom());

        ImageView imageView = holder.imageView;
        Glide.with(context).load(mContacts.get(position).getImageUrl()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView firstNameTextView;
        public TextView lastNameTextView;
        public ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firstNameTextView = (TextView) itemView.findViewById(R.id.contact_firstname);
            lastNameTextView = (TextView) itemView.findViewById(R.id.contact_lastname);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}
