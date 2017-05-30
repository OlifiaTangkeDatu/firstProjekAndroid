package com.olifia.welcome;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by fiaolifia on 28/05/2017.
 */

public class DiariAdapter extends RecyclerView.Adapter<DiariAdapter.ViewHolder> {
    ArrayList<Diari> diaris;
    Context context;
    navigation navigation;

    public DiariAdapter(Context context,ArrayList<Diari> diaris){
        this.context = context;
        this.diaris = diaris;
    }

    void setParent(navigation navigation){
        this.navigation = navigation;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Diari diari = diaris.get(position);

        int prevLenght = diari.getIsi().length() >= 10 ? 10 : diari.getIsi().length();

        holder.textJudul.setText(diari.getJudul());
        holder.textPrev.setText(diari.getIsi().substring(0,prevLenght)+"...");
        holder.textTanggal.setText(diari.getTanggal());
        holder.imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuPopUp(holder.imageMenu, diari);
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,tampilDiary.class);
                intent.putExtra(DatabaseHandler.KOLOM_ISI,diari.getIsi());
                context.startActivity(intent);
            }
        });
        String kategori = diari.getKategori();
        int imageResource;
        if(kategori.equals("Senang")){
            imageResource = R.drawable.senang;
        }else if(kategori.equals("Sedih")){
            imageResource = R.drawable.sedih;
        }else if(kategori.equals("Marah")){
            imageResource = R.drawable.marah;
        }else if(kategori.equals("Kecewa")){
            imageResource = R.drawable.kecewa;
        }else{
            imageResource = R.drawable.jatuhcinta;
        }

        holder.imageEmotion.setImageResource(imageResource);

    }

    void showMenuPopUp(View view, final Diari diari){
        PopupMenu popupMenu = new PopupMenu(context,view);
        final MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_diari,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_delete_diari:
                        DatabaseHandler databaseHandler = new DatabaseHandler(context);
                        int r = databaseHandler.deleteDiari(diari);
                        Toast.makeText(context,r+" deleted",Toast.LENGTH_LONG).show();
                        navigation.initAdapter();
                        return true;
                    case R.id.action_edit_diari:
                        Intent intent = new Intent(context,Edit.class);
                        intent.putExtra(DatabaseHandler.KOLOM_TANGGAL,diari.getTanggal());
                        intent.putExtra(DatabaseHandler.KOLOM_JUDUL,diari.getJudul());
                        intent.putExtra(DatabaseHandler.KOLOM_ISI,diari.getIsi());
                        intent.putExtra(DatabaseHandler.KOLOM_KATEGORI,diari.getKategori());
                        intent.putExtra(DatabaseHandler.KOLOM_ID,diari.getId());
                        context.startActivity(intent);
                        return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        return diaris.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textJudul, textPrev, textTanggal;
        public View view;
        ImageView imageMenu, imageEmotion;
        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            imageMenu = (ImageView) itemView.findViewById(R.id.menu);
            imageEmotion = (ImageView) itemView.findViewById(R.id.imageEmotion);
            textJudul = (TextView) itemView.findViewById(R.id.list_txt_judul);
            textPrev =   (TextView) itemView.findViewById(R.id.list_txt_prev);
            textTanggal =  (TextView) itemView.findViewById(R.id.list_txt_tangga);

        }
    }
}
