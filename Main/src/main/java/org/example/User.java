package org.example;

import java.util.ArrayList;

public class User extends Entity {

    private ArrayList<Singer> listOfSinger;
    private ArrayList<Comment> listOfComments;
    private ArrayList<Music> favoriteMusics;
    public ArrayList<Singer> followedSingers;

    public User(String firstName, String lastName, int age, String email, String username, String password){
        super(firstName, lastName, age, email, username, password);
        this.listOfSinger = new ArrayList<>();
        this.listOfComments = new ArrayList<>();
        this.favoriteMusics = new ArrayList<>();
        this.followedSingers = new ArrayList<>();
        this.setRole("User");
    }

    public void addMusicToFavoriteMusics(Music music){
        favoriteMusics.add(music);
    }
    public void addSingerToFollowedSinger(Singer singer){
        followedSingers.add(singer);
    }

    public void addComment(Comment comment){
        listOfComments.add(comment);
    }
    public ArrayList<Singer> getListOfSinger(){
        return listOfSinger;
    }

    public ArrayList<Music> getFavoriteMusics(){
        return favoriteMusics;
    }

    public ArrayList<Singer> getFollowedSingers(){
        return followedSingers;
    }
    public ArrayList<Comment> getListOfComments(){
        return listOfComments;
    }
}
