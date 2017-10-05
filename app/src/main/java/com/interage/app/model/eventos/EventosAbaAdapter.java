package com.interage.app.model.eventos;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.interage.app.interage.R;
import com.interage.app.model.Evento;

import java.util.List;

public class EventosAbaAdapter extends RecyclerView.Adapter<EventosAbaAdapter.MyViewHolder> {

    private Context mContext;
    private List<Evento> eventoList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView evento_name;
        public ImageView thumbnail_event;

        public MyViewHolder(View view) {
            super(view);
            evento_name = (TextView) view.findViewById(R.id.evento_name);
            thumbnail_event = (ImageView) view.findViewById(R.id.thumbnail_event);
        }
    }


    public EventosAbaAdapter(Context mContext, List<Evento> albumList) {
        this.mContext = mContext;
        this.eventoList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eventos_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Evento evento = eventoList.get(position);
        holder.evento_name.setText(evento.getName());
        Glide.with(mContext).load(evento.getThumbnail()).into(holder.thumbnail_event);
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return eventoList.size();
    }
}
