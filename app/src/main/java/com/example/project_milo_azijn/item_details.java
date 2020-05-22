package com.example.project_milo_azijn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class item_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Album mAlbum = (Album) bundle.getSerializable("ALBUM");

        TextView mArtist = findViewById(R.id.albumArtist);
        TextView mAlbumTitle = findViewById(R.id.albumTitle);
        TextView mType = findViewById(R.id.releaseType);
        TextView mYear = findViewById(R.id.releaseYear);

        if(intent != null) {
            mArtist.setText(mAlbum.getArtist());
            mAlbumTitle.setText(mAlbum.getAlbum());
            mYear.setText("Release Year: " + mAlbum.getYear());
            mType.setText("Release Type: " + mAlbum.getType());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
