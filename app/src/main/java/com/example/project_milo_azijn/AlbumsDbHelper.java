package com.example.project_milo_azijn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AlbumsDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Albums";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + AlbumsContract.AlbumEntry.TABLE_NAME + " (" +
            AlbumsContract.AlbumEntry._ID + " INTEGER PRIMARY KEY," +
            AlbumsContract.AlbumEntry.COLUMN_NAME_ARTIST + " TEXT, " +
            AlbumsContract.AlbumEntry.COLUMN_NAME_ALBUM + " TEXT, " +
            AlbumsContract.AlbumEntry.COLUMN_NAME_YEAR + " TEXT, " +
            AlbumsContract.AlbumEntry.COLUMN_NAME_TYPE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + AlbumsContract.AlbumEntry.TABLE_NAME;

    public AlbumsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate (SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    //different methods for sorting albums
    public ArrayList<Album> listAlbums() {
        String query = "SELECT * FROM " + AlbumsContract.AlbumEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Album> AlbumList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String artist = cursor.getString(1);
                String album = cursor.getString(2);
                String year = cursor.getString(3);
                String type = cursor.getString(4);
                AlbumList.add(new Album(id, artist, album, type, year));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return AlbumList;
    }

    public ArrayList<Album> listAlbumsAlpha() {
        String query = "SELECT * FROM " + AlbumsContract.AlbumEntry.TABLE_NAME + " ORDER BY " + AlbumsContract.AlbumEntry.COLUMN_NAME_ALBUM;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Album> AlbumList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String artist = cursor.getString(1);
                String album = cursor.getString(2);
                String year = cursor.getString(3);
                String type = cursor.getString(4);
                AlbumList.add(new Album(id, artist, album, type, year));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return AlbumList;
    }

    public ArrayList<Album> listAlbumsYear() {
        String query = "SELECT * FROM " + AlbumsContract.AlbumEntry.TABLE_NAME + " ORDER BY " + AlbumsContract.AlbumEntry.COLUMN_NAME_YEAR;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Album> AlbumList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String artist = cursor.getString(1);
                String album = cursor.getString(2);
                String year = cursor.getString(3);
                String type = cursor.getString(4);
                AlbumList.add(new Album(id, artist, album, type, year));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return AlbumList;
    }

    public ArrayList<Album> listAlbumsArtist() {
        String query = "SELECT * FROM " + AlbumsContract.AlbumEntry.TABLE_NAME + " ORDER BY " + AlbumsContract.AlbumEntry.COLUMN_NAME_ARTIST;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Album> AlbumList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String artist = cursor.getString(1);
                String album = cursor.getString(2);
                String year = cursor.getString(3);
                String type = cursor.getString(4);
                AlbumList.add(new Album(id, artist, album, type, year));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return AlbumList;
    }

    public ArrayList<Album> listAlbumsType() {
        String query = "SELECT * FROM " + AlbumsContract.AlbumEntry.TABLE_NAME + " ORDER BY " + AlbumsContract.AlbumEntry.COLUMN_NAME_TYPE;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Album> AlbumList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String artist = cursor.getString(1);
                String album = cursor.getString(2);
                String year = cursor.getString(3);
                String type = cursor.getString(4);
                AlbumList.add(new Album(id, artist, album, type, year));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return AlbumList;
    }

    public void addAlbum(Album album) {
        ContentValues values = new ContentValues();
        values.put(AlbumsContract.AlbumEntry.COLUMN_NAME_ARTIST, album.getArtist());
        values.put(AlbumsContract.AlbumEntry.COLUMN_NAME_ALBUM, album.getAlbum());
        values.put(AlbumsContract.AlbumEntry.COLUMN_NAME_TYPE, album.getType());
        values.put(AlbumsContract.AlbumEntry.COLUMN_NAME_YEAR, album.getYear());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(AlbumsContract.AlbumEntry.TABLE_NAME, null, values);
    }
}
