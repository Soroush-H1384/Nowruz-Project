package org.example;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class SaveUsername {
    private ArrayList<Singer> singers;
    private ArrayList<User> users;
    private String filePath;

    public SaveUsername(String filePath){
        this.filePath = filePath;
        this.singers = new ArrayList<>();
        this.users = new ArrayList<>();
        loadFromFile();
    }

    public void addUser(User user){
        users.add(user);
    }
    public void addSinger(Singer singer){
        singers.add(singer);
    }
    public void saveToFile(){
        JSONArray JA = new JSONArray();
        for (Singer s : singers){
            JSONObject JO = new JSONObject();
            JO.put("Username", s.getUsername());
            JO.put("Password", s.getPassword());
            JO.put("Email", s.getEmail());
            JO.put("Age", s.getAge());
            JO.put("Firstname", s.getFirstName());
            JO.put("Lastname", s.getLastName());
            JO.put("Role", s.getRole());
            JSONArray JA2 = new JSONArray();
            for (Album a : s.getAlbums()){
                JSONObject JO2 = new JSONObject();
                JO2.put("AlbumName",a.getName());
                JO2.put("SingerName",s.getFirstName() +" " + s.getLastName());
                JSONArray JA3 = new JSONArray();
                for (Music m : a.getMusics()){
                    JSONObject JO3 = new JSONObject();
                    JO3.put("MusicName",m.getName());
                    JO3.put("MusicText", m.getText());
                    JO3.put("MusicView", m.getView());
                    JSONArray JA4 = new JSONArray();
                    for (Comment c : m.getComments()){
                        JSONObject JO4 = new JSONObject();
                        JO4.put("Title",c.getTitle());
                        JO4.put("CommentText",c.getText());
                        JO4.put("Time",c.getTime());
                        JSONObject JO5 = new JSONObject();
                        JO5.put("AuthorName",c.getUser().getUsername());
                        JO5.put("AuthorEmail", c.getUser().getEmail());
                        JO4.put("CommentAuthor",JO5);
                        JA4.put(JO4);
                    }
                    JO3.put("Comments",JA4);
                    JA3.put(JO3);
                }
                JO2.put("Musics",JA3);
                JA2.put(JO2);
            }
            JO.put("Albums",JA2);
            JA.put(JO);
        }
        for (User u : users){
            JSONObject JO = new JSONObject();
            JO.put("Username", u.getUsername());
            JO.put("Password", u.getPassword());
            JO.put("Email", u.getEmail());
            JO.put("Age", u.getAge());
            JO.put("Firstname", u.getFirstName());
            JO.put("Lastname", u.getLastName());
            JO.put("Role", u.getRole());
            JSONArray JA2 = new JSONArray();
            for (Singer s : u.getFollowedSingers()){
                JSONObject JO2 = new JSONObject();
                JO2.put("SingerFirstname",s.getFirstName());
                JO2.put("SingerLastname",s.getLastName());
                JO2.put("SingerAge",s.getAge());
                JO2.put("SingerUsername",s.getUsername());
                JO2.put("SingerPassword",s.getPassword());
                JO2.put("SingerEmail",s.getEmail());
                JA2.put(JO2);
            }
            JO.put("FollowedSingers",JA2);
            JSONArray JA3 = new JSONArray();
            for (Comment c : u.getListOfComments()){
                JSONObject JO3 = new JSONObject();
                JO3.put("CommentText", c.getText());
                JO3.put("CommentTime", c.getTime());
                JO3.put("CommentTitle", c.getTitle());
                JA3.put(JO3);
            }
            JO.put("Comments",JA3);
            JSONArray JA4 = new JSONArray();
            for (Music m : u.getFavoriteMusics()){
                JSONObject JO4 = new JSONObject();
                JO4.put("MusicName", m.getName());
                JSONObject JOO = new JSONObject();
                JOO.put("MusicSingerFirstname", m.getSinger().getFirstName());
                JOO.put("MusicSingerLastname", m.getSinger().getLastName());
                JOO.put("MusicSingerAge", m.getSinger().getAge());
                JOO.put("MusicSingerEmail", m.getSinger().getEmail());
                JOO.put("MusicSingerUsername", m.getSinger().getUsername());
                JOO.put("MusicSingerPassword", m.getSinger().getPassword());
                JO4.put("MusicSinger",JOO);
                JO4.put("MusicView", m.getView());
                JO4.put("MusicLike",m.getLike());
                JO4.put("MusicText", m.getText());
                for (String str : m.getSuggestedText()){
                    JSONObject JO5 = new JSONObject();
                    JO5.put("Text",str);
                    JO4.put("MusicSuggestedText",JO5);
                }
                JA4.put(JO4);
            }
            JO.put("FavoriteMusics",JA4);
            JA.put(JO);
        }
        try(FileWriter file = new FileWriter(filePath)){
            file.write(JA.toString(4));
            System.out.println("Save!");
        } catch (IOException e) {
            System.out.println("Error! cannot save!");
        }
    }
    public void loadFromFile(){
        File file = new File(filePath);
        if (file.exists()){
            try{
                String content = new String(Files.readAllBytes(Paths.get(filePath)));
                JSONArray JA = new JSONArray(content);
                for (int i = 0; i < JA.length(); i++){
                    JSONObject JO = JA.getJSONObject(i);
                    String username = JO.getString("Username");
                    String password = JO.getString("Password");
                    String email = JO.getString("Email");
                    String firstname = JO.getString("Firstname");
                    String lastname = JO.getString("Lastname");
                    int age = JO.getInt("Age");
                    String role = JO.getString("Role");

                    if (role.equals("Singer")) {
                        Singer singer = new Singer(firstname, lastname, age, email, username, password);
                        singers.add(singer);
                        JSONArray JA1 = JO.getJSONArray("Albums");
                        for (int k = 0; k < JA1.length(); k++) {
                            JSONObject JO2 = JA1.getJSONObject(k);
                            String AlbumName = JO2.getString("AlbumName");
                            String SingerName = JO2.getString("SingerName");
                            Album album = new Album(AlbumName, singer);
                            singer.addAlbum(album);
                            JSONArray JA2 = JO2.getJSONArray("Musics");
                            for (int j = 0; j < JA2.length(); j++) {
                                JSONObject JO3 = JA2.getJSONObject(j);
                                String MusicName = JO3.getString("MusicName");
                                String MusicText = JO3.getString("MusicText");
                                int MusicView = JO3.getInt("MusicView");
                                Music music = new Music(MusicName, singer, MusicText);
                                music.setView(MusicView);
                                album.addMusic(music);
                                JSONArray JA3 = JO3.getJSONArray("Comments");
                                for (int z = 0; z < JA3.length(); z++) {
                                    JSONObject JO4 = JA3.getJSONObject(z);
                                    String Title = JO4.getString("Title");
                                    String CommentText = JO4.getString("CommentText");
                                    Comment comment = new Comment(Title, CommentText);
                                    music.addComment(comment);
                                }
                            }
                        }
                    } else if (role.equals("User")) {
                        User user = new User(firstname, lastname, age, email, username, password);
                        users.add(user);
                        JSONArray JA1 = JO.getJSONArray("Comments");
                        for (int i2 = 0; i2 < JA1.length(); i2++){
                            JSONObject JO1 = JA1.getJSONObject(i2);
                            String CommentTitle = JO1.getString("CommentTitle");
                            String CommentText = JO1.getString("CommentText");
                            String CommentTime = JO1.getString("CommentTime");
                            Comment comment = new Comment(CommentTitle,CommentText);
                            comment.setTime(CommentTime);
                            user.addComment(comment);
                        }
                        JSONArray JA2 = JO.getJSONArray("FollowedSingers");
                        for (int i3 = 0; i3 < JA2.length(); i3++){
                            JSONObject JO2 = JA2.getJSONObject(i3);
                            String SingerFirstname = JO2.getString("SingerFirstname");
                            String SingerLastname = JO2.getString("SingerLastname");
                            String SingerUsername = JO2.getString("SingerUsername");
                            String SingerPassword = JO2.getString("SingerPassword");
                            int SingerAge = JO2.getInt("SingerAge");
                            String SingerEmail = JO2.getString("SingerEmail");
                            Singer singer = new Singer(SingerFirstname,SingerLastname,SingerAge,SingerEmail,SingerUsername,SingerPassword);
                            singers.add(singer);
                            user.addSingerToFollowedSinger(singer);
                        }
                        JSONArray JA3 = JO.getJSONArray("FavoriteMusics");
                        for (int i4 = 0; i4 < JA3.length(); i4++){
                            JSONObject JO3 = JA3.getJSONObject(i4);
                            String MusicName = JO3.getString("MusicName");
                            int MusicView = JO3.getInt("MusicView");
                            int MusicLike = JO3.getInt("MusicLike");
                            String MusicText = JO3.getString("MusicText");
                            JSONObject JO4 = JO3.getJSONObject("MusicSinger");
                            String MusicSingerFirstname = JO4.getString("MusicSingerFirstname");
                            String MusicSingerLastname = JO4.getString("MusicSingerLastname");
                            String MusicSingerPassword = JO4.getString("MusicSingerPassword");
                            String MusicSingerEmail = JO4.getString("MusicSingerEmail");
                            String MusicSingerUsername = JO4.getString("MusicSingerUsername");
                            int MusicSingerAge = JO4.getInt("MusicSingerAge");
                            Singer singer = new Singer(MusicSingerFirstname,MusicSingerLastname,MusicSingerAge,MusicSingerEmail,MusicSingerUsername,MusicSingerPassword);
                            Music music = new Music(MusicName,singer,MusicText);
                            music.setView(MusicView);
                            music.setLike(MusicLike);
                            user.addMusicToFavoriteMusics(music);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    public ArrayList<User> getUsers(){
        return users;
    }
    public ArrayList<Singer> getSingers(){
        return singers;
    }
}
