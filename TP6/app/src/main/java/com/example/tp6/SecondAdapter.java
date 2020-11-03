package com.example.tp6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder>{

    private List<Repo> mRepos;

    public SecondAdapter(List<Repo> mRepos) {
        this.mRepos = mRepos;
    }

    @NonNull
    @Override
    public SecondAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context  = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View repoView = inflater.inflate(R.layout.item_repo, parent, false);

        return new ViewHolder(repoView);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondAdapter.ViewHolder holder, int position) {
        Repo repo = mRepos.get(position);

        TextView id = holder.id;
        id.setText(repo.getId());

        TextView name = holder.name;
        name.setText(repo.getName());

        TextView fullname = holder.fullname;
        fullname.setText(repo.getFull_name());

        TextView html_url = holder.html_url;
        html_url.setText(repo.getHtml_url());
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView name;
        public TextView fullname;
        public TextView html_url;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.textId);
            name = (TextView) itemView.findViewById(R.id.textName);
            fullname = (TextView) itemView.findViewById(R.id.textFullName);
            html_url = (TextView) itemView.findViewById(R.id.textHtmlUrl);
        }
    }
}
