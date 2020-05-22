package com.example.project_milo_azijn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements albumAdapter.albumAdapterClickHandler, SharedPreferences.OnSharedPreferenceChangeListener {
    private RecyclerView recyclerView;
    private AlbumsDbHelper mDbHelper;
    private ArrayList<Album> allAlbums = new ArrayList<>();
    private albumAdapter mAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        //recyclerview
        recyclerView = findViewById(R.id.main_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //register preferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.registerOnSharedPreferenceChangeListener(this);

        //set floating button
        FloatingActionButton addButton =findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        mDbHelper = new AlbumsDbHelper(this);

        //read saved preference & get Albums from database
        String prefString = sp.getString("sorting_list", "Date added");
        assert prefString != null;
        if (prefString.equals("year")) loadAlbums(mDbHelper.listAlbumsYear());
        if (prefString.equals("id")) loadAlbums(mDbHelper.listAlbums());
        if (prefString.equals("artist")) loadAlbums(mDbHelper.listAlbumsArtist());
        if (prefString.equals("album")) loadAlbums(mDbHelper.listAlbumsAlpha());
        if (prefString.equals("type")) loadAlbums(mDbHelper.listAlbumsType());
    }

    private void loadAlbums(ArrayList<Album> albums) {
        allAlbums = albums;
        if(allAlbums.size() > 0){
            recyclerView.setVisibility(View.VISIBLE);
            mAdapter = new albumAdapter( allAlbums, this);
            recyclerView.setAdapter(mAdapter);
        }
        else {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "The database is currently empty, please add an item", Toast.LENGTH_LONG).show();
        }
    }

    //adding albums into DB
    private void addItem() {
        LayoutInflater inflate = LayoutInflater.from(this);
        View view = inflate.inflate(R.layout.add_album, null);

        final EditText artistName = view.findViewById(R.id.enterArtist);
        final EditText albumName = view.findViewById(R.id.enterAlbum);
        final EditText releaseYear = view.findViewById(R.id.enterYear);
        final EditText releaseType = view.findViewById(R.id.enterType);

        //build dialog to add items
        AlertDialog.Builder addBuilder = new AlertDialog.Builder(this);
        addBuilder.setTitle("Add album");
        addBuilder.setView(view);
        addBuilder.create();

        addBuilder.setPositiveButton("Add Album", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String artist = artistName.getText().toString();
                final String album = albumName.getText().toString();
                final String year = releaseYear.getText().toString();
                final String type = releaseType.getText().toString();

                if(TextUtils.isEmpty(artist))Toast.makeText(MainActivity.this, "Please fill in all fields.", Toast.LENGTH_LONG).show();
                else if(TextUtils.isEmpty(album))Toast.makeText(MainActivity.this, "Please fill in all fields.", Toast.LENGTH_LONG).show();
                else if(TextUtils.isEmpty(year))Toast.makeText(MainActivity.this, "Please fill in all fields.", Toast.LENGTH_LONG).show();
                else if(TextUtils.isEmpty(type))Toast.makeText(MainActivity.this, "Please fill in all fields.", Toast.LENGTH_LONG).show();
                else{
                    Album newAlbum = new Album(artist, album, type, year);
                    mDbHelper.addAlbum(newAlbum);
                    finish();
                    startActivity(getIntent());
                }
            }
        });
        addBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        });
        addBuilder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDbHelper.close();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    //send album object to new activity
    @Override
    public void onClick(Album album) {
        Intent detailIntent = new Intent(this, item_details.class);
        detailIntent.putExtra("ALBUM", album);
        startActivity(detailIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //start settings activity
            case R.id.settingsAction:
                Intent settingsIntent = new Intent(this, Settings.class);
                startActivity(settingsIntent);
                return true;
            //restart MainActivity
            case R.id.refreshAction:
                ((Activity)context).finish();
                context.startActivity(((Activity)context).getIntent());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if(s.equals("sorting_list")) {
            String pref = sharedPreferences.getString("sorting_list", "Date added");
            assert pref != null;
            if (pref.equals("year")) loadAlbums(mDbHelper.listAlbumsYear());
            if (pref.equals("id")) loadAlbums(mDbHelper.listAlbums());
            if (pref.equals("artist")) loadAlbums(mDbHelper.listAlbumsArtist());
            if (pref.equals("album")) loadAlbums(mDbHelper.listAlbumsAlpha());
            if (pref.equals("type")) loadAlbums(mDbHelper.listAlbumsType());
        }
    }
}
