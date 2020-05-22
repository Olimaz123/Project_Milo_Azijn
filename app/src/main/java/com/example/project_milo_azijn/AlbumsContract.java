package com.example.project_milo_azijn;

import android.provider.BaseColumns;

public final class AlbumsContract {
    private AlbumsContract() {}

    public class AlbumEntry implements BaseColumns {
        public final static String TABLE_NAME = "Albums";
        public final static String COLUMN_NAME_ARTIST = "Artist";
        public final static String COLUMN_NAME_ALBUM = "Album";
        public final static String COLUMN_NAME_YEAR = "Year";
        public final static String COLUMN_NAME_TYPE = "type";
    }
}