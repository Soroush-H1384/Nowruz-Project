package org.example;

public class Entity {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int age;
    private String role;

    public Entity(String firstName, String lastName, int age, String email, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = null;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public int getAge(){
        return age;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }
    public String getRole(){
        return role;
    }


    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setRole(String role){
        this.role = role;
    }
}
