package org.example;

import java.util.ArrayList;

public class Singer extends Entity {

    private ArrayList<Album> albums;
    private ArrayList<Music> musics;

    public Singer(String firstName, String lastName, int age, String email, String username, String password) {
        super(firstName, lastName, age, email, username, password);
        this.musics = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.setRole("Singer");
    }



    public void addAlbum(Album album){
        albums.add(album);
    }
    public void addMusic(Music music){
        musics.add(music);
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }
    public ArrayList<Music> getMusics(){
        return musics;
    }

}
