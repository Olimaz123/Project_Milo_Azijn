package com.example.project_milo_azijn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View.OnClickListener;


public class albumAdapter extends RecyclerView.Adapter<albumAdapter.albumViewHolder> {

    private ArrayList<Album> listAlbums;
    private ArrayList<Album> mAlbumList;

    private final albumAdapterClickHandler mClickHandler;

    public albumAdapter(ArrayList<Album> listAlbums, albumAdapterClickHandler clickHandler) {
        this.listAlbums = listAlbums;
        this.mAlbumList = listAlbums;
        mClickHandler = clickHandler;
    }

    public interface albumAdapterClickHandler {
        void onClick(Album album);
    }

    public class albumViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public final TextView textView;
        public albumViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.album_data);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Album clickedAlbum = listAlbums.get(pos);
            mClickHandler.onClick(clickedAlbum);
        }
    }

    @Override
    public albumViewHolder onCreateViewHolder (ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutID = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attach = false;
        View view = inflater.inflate(layoutID, viewGroup, attach);
        return new albumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(albumAdapter.albumViewHolder holder, int position) {
        final Album album = listAlbums.get(position);
        holder.textView.setText(album.getArtist() + " - " + album.getAlbum());
    }

    // Return the amount of albums
    @Override
    public int getItemCount() {
        if(null == mAlbumList) return 0;
        return mAlbumList.size();
    }
}
