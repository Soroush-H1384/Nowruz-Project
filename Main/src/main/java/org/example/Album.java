package org.example;

import java.util.ArrayList;

public class Album {
    private String name;
    private ArrayList<Music> musics;
    private Singer singer;

    public Album(String name, Singer singer){
        this.name = name;
        this.singer = singer;
        this.musics = new ArrayList<>();
    }
    public void addMusic(Music music){
        musics.add(music);
    }

    public String getName(){
        return name;
    }
    public Singer getSinger(){
        return singer;
    }
    public ArrayList<Music> getMusics(){
        return musics;
    }
}
