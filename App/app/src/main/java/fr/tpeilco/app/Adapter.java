package fr.mohamed.app;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

//    List<PokemonSpecies> list_pokemon = new ArrayList<>();
    List<Results> list_pokemon = new ArrayList<>();
    private Context context;

    public Adapter(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_pokemon;
        TextView name_pokemon;
        ProgressBar progress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_pokemon = itemView.findViewById(R.id.image_pokemon);
            name_pokemon = itemView.findViewById(R.id.name_pokemon);
            progress = itemView.findViewById(R.id.progress);
        }

        private void showProgressBar(boolean showProgressBar){
            if(showProgressBar){
                progress.setVisibility(View.VISIBLE);
            }
            else{
                progress.setVisibility(View.GONE);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Results result = list_pokemon.get(position);

            holder.name_pokemon.setText(result.getName());

            if(result.getImage() == null){
                holder.showProgressBar(true);
                holder.image_pokemon.setImageResource(0);
            }else{
                holder.showProgressBar(false);
                Glide.with(holder.image_pokemon.getContext())
                        .load(result.getImage())
                        .into(holder.image_pokemon);
            }

            holder.image_pokemon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Results resDetails = list_pokemon.get(holder.getAdapterPosition());

                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_details_pokemon);

                    int width = WindowManager.LayoutParams.MATCH_PARENT;
                    int height = WindowManager.LayoutParams.WRAP_CONTENT;

                    dialog.getWindow().setLayout(width, height);


                    ImageView image_pokemon_description = dialog.findViewById(R.id.image_pokemon_description);
                    TextView description_pokemon  = dialog.findViewById(R.id.description_pokemon);
                    Button exit_btn  = dialog.findViewById(R.id.exit_btn);

                    Glide.with(image_pokemon_description.getContext())
                            .load(resDetails.getImage())
                            .into(image_pokemon_description);
                    description_pokemon.setText("id : "+resDetails.getId()+"  height :" + resDetails.getHeight() + "order  : " + resDetails.getOrder());

                    dialog.show();

                    exit_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });


                }
            });
    }

    @Override
    public int getItemCount() {
        return this.list_pokemon.size();
    }

    public void setResults(List<Results> results){
        this.list_pokemon.clear();
        this.list_pokemon.addAll(results);
        notifyDataSetChanged();
    }

    public void updateResult(Results result){
        list_pokemon.set(list_pokemon.indexOf(result), result);
        notifyItemChanged(list_pokemon.indexOf(result));
        notifyDataSetChanged();
    }


    public void updateDetailsPokemon(Results detailsPokemon){

    }

}
