package org.example;

import java.util.ArrayList;

public class Music {
    private String name;
    private ArrayList<Comment> comments;
    private String text;
    private ArrayList<String> suggestedText;
    private Album album;
    private Singer singer;
    private int view;
    private int like;
    private boolean isFavorite;

    public Music(String name, Singer singer, String text) {
        this.name = name;
        this.singer = singer;
        this.text = text;
        this.comments = new ArrayList<>();
        this.view = 0;
        this.like = 0;
        this.suggestedText = new ArrayList<>();
    }
    public void setView(int musicView){
        this.view = musicView;
    }
    public void increaseView(){
        this.view++;
    }

    public void setLike(int musicLike){
        this.like = musicLike;
    }

    public void increaseLike(){
        this.like++;
    }

    public int getLike(){
        return like;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public Singer getSinger() {
        return singer;
    }

    public int getView(){
        return view;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    public void addComment(Comment comment){
        comments.add(comment);
    }
    public void addTextToSuggestedText(String text){
        suggestedText.add(text);
    }
    public ArrayList<String> getSuggestedText(){
        return suggestedText;
    }
}
