package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static User currentUser = null;
    private static Singer currentSinger = null;

    public static void main(String[] args) {
        SaveUsername SAVE = new SaveUsername("Username.json");
        Scanner sc = new Scanner(System.in);
        /// ----------------------------------
        while (true) {
            System.out.println("Send a number and select your role...");
            System.out.println("1 - Admin");
            System.out.println("2 - Singer");
            System.out.println("3 - User");
            System.out.println("4 - Exit");
            int roleForCheck = sc.nextInt();
            System.out.println("Enter your username...");
            String usernameForCheck = sc.next();
            System.out.println("Enter your password(6 to 8 character)...");
            String passwordForCheck = sc.next();
            while (!(8 >= passwordForCheck.length() && passwordForCheck.length() >= 6)) {
                System.out.println("Try again");
                passwordForCheck = sc.next();
            }
            Boolean foundUser = false;
            switch (roleForCheck) {
                case 1:
                    break;
                ///  Singer login;
                case 2:
                    for (Singer s : SAVE.getSingers()) {
                        if (usernameForCheck.equals(s.getUsername())) {
                            foundUser = true;
                            currentSinger = s;
                            while (!passwordForCheck.equals(s.getPassword())) {
                                System.out.println("your password is not correct");
                                System.out.println("Please Enter again");
                                passwordForCheck = sc.next();
                            }
                            System.out.println("Welcome to app");
                            break;
                        }
                    }
                    if (!foundUser) {
                        System.out.println("You don't have any account");
                        System.out.println("Enter your first name...");
                        String firstnameForCheck = sc.next();
                        System.out.println("Enter your last name...");
                        String lastnameForCheck = sc.next();
                        System.out.println("Enter your age...");
                        int ageForCheck = sc.nextInt();
                        System.out.println("Enter your email...");
                        String emailForCheck = sc.next();
                        Singer newSinger = new Singer(firstnameForCheck, lastnameForCheck, ageForCheck, emailForCheck, usernameForCheck, passwordForCheck);
                        SAVE.addSinger(newSinger);
                        SAVE.saveToFile();
                        currentSinger = newSinger;
                    }
                    ///  This while is for Singers;
                    boolean back3 = false;
                    while (true) {
                        if (back3) {
                            break;
                        }
                        System.out.println("1-Profile");
                        System.out.println("2-Musics");
                        System.out.println("3-Newest Comments");
                        System.out.println("4-Popular Musics");
                        switch (sc.nextInt()) {
                            ///  in this case we can see our information and changed them.
                            case 1:
                                boolean back2 = false;
                                while (true) {
                                    if (back2) {
                                        break;
                                    }
                                    System.out.println("1-Change Password");
                                    System.out.println("2-Edit Information");
                                    System.out.println("3-Logout");
                                    System.out.println("4-back");
                                    switch (sc.nextInt()) {
                                        ///  if you want to change your password, you should enter your password correctly.
                                        case 1:
                                            System.out.println("Please Enter your password...");
                                            String password = sc.next();
                                            while (!password.equals(currentSinger.getPassword())) {
                                                System.out.println("your password is not correct.Please try again...");
                                                password = sc.next();
                                            }
                                            System.out.println("Please Enter new password(6 to 8 character)...");
                                            password = sc.next();
                                            while (!(6 <= password.length() && password.length() <= 8) || password.equals(currentSinger.getPassword())) {
                                                System.out.println("Try again, Please enter new password between 6 to 8 character");
                                                password = sc.next();
                                            }
                                            System.out.println("Confirm your password...");
                                            String password2 = sc.next();
                                            while (!password2.equals(password)) {
                                                System.out.println("your password do not match with confirmation password");
                                                password2 = sc.next();
                                            }
                                            currentSinger.setPassword(password);
                                            SAVE.saveToFile();
                                            System.out.println("Your password change successfully");
                                            break;
                                        ///  Information.
                                        case 2:
                                            System.out.println("Your information is: ");
                                            System.out.println("1-First name: " + currentSinger.getFirstName());
                                            System.out.println("2-Last name: " + currentSinger.getLastName());
                                            System.out.println("3-Age: " + currentSinger.getAge());
                                            System.out.println("4-Username: " + currentSinger.getUsername());
                                            System.out.println("5-Email: " + currentSinger.getEmail());
                                            System.out.println("------Guide Box-------------------------");
                                            System.out.println(">> Enter one letter to select:");
                                            System.out.println("  >> 's' --> select");
                                            System.out.println("  >> 'q' --> back to manu");
                                            System.out.println("----------------------------------------");
                                            String back = sc.next();
                                            switch (back) {
                                                case "s":
                                                    System.out.println("Enter number of information you want to edit...");
                                                    int select = sc.nextInt();
                                                    switch (select) {
                                                        case 1:
                                                            System.out.println("New 'first name':");
                                                            currentSinger.setFirstName(sc.next());
                                                            break;
                                                        case 2:
                                                            System.out.println("New 'last name':");
                                                            currentSinger.setLastName(sc.next());
                                                            break;
                                                        case 3:
                                                            System.out.println("New 'Age':");
                                                            currentSinger.setAge(sc.nextInt());
                                                            break;
                                                        case 4:
                                                            System.out.println("New 'Username':");
                                                            currentSinger.setUsername(sc.next());
                                                            break;
                                                        case 5:
                                                            System.out.println("New 'Email':");
                                                            currentSinger.setEmail(sc.next());
                                                            break;
                                                        default:
                                                            System.out.println("Please Enter number from menu!!");
                                                            break;
                                                    }
                                                    break;
                                                case "q":
                                                    break;
                                            }
                                            break;
                                        case 3:
                                            back3 = true;
                                            back2 = true;
                                            break;
                                        case 4:
                                            back2 = true;
                                            break;
                                    }
                                }
                                break;

                            ///  in this case we can see our musics and albums and add new of them.
                            case 2:
                                System.out.println("1-Albums");
                                System.out.println("2-Musics");
                                switch (sc.nextInt()) {
                                    ///  see and add album.
                                    case 1:
                                        System.out.println("1-Show List of Albums");
                                        System.out.println("2-Add New Album");
                                        System.out.println("0-back");
                                        boolean back4 = false;
                                        while (true) {
                                            if (back4) {
                                                break;
                                            }
                                            switch (sc.nextInt()) {
                                                case 1:
                                                    int counter = 1;
                                                    for (Album a1 : currentSinger.getAlbums()) {
                                                        if (a1.getName().equals("Single-Track")) {
                                                            continue;
                                                        }
                                                        System.out.println(counter + "-" + a1.getName());
                                                        counter++;
                                                    }
                                                    System.out.println("------Guide Box-------------------------");
                                                    System.out.println(">> Enter one letter to select:");
                                                    System.out.println("  >> 's' --> select");
                                                    System.out.println("  >> 'n' --> next page");
                                                    System.out.println("  >> 'p' --> previous page");
                                                    System.out.println("  >> 'b' --> back to manu");
                                                    System.out.println("----------------------------------------");
                                                    String menuLetter = sc.next();
                                                    if (menuLetter.equals("s")) {
                                                        int number = sc.nextInt();
                                                        if (currentSinger.getAlbums().get(number).getMusics().size() == 0) {
                                                            System.out.println("This album is empty!!");
                                                        } else {
                                                            for (Music m : currentSinger.getAlbums().get(number).getMusics()) {
                                                                System.out.println("Title: " + m.getName());
                                                                System.out.println("Singer: " + m.getSinger().getFirstName() + m.getSinger().getLastName());
                                                                System.out.println("Text: " + m.getText());
                                                                System.out.println("Viewed: " + m.getView());
                                                                System.out.println("----------------------------------------");
                                                            }
                                                        }
                                                    }
//                                                    else if ("n") {
//
//
//                                                    } else if ("p") {
//
//                                                    }
                                                    else if (menuLetter.equals("b")) {
                                                        back4 = true;
                                                    }
                                                    break;
                                                case 2:
                                                    System.out.println("Album name: ");
                                                    String AlbumName = sc.next();
                                                    currentSinger.addAlbum(new Album(AlbumName, currentSinger));
                                                    SAVE.saveToFile();
                                                    System.out.println("Album created!");
                                                    break;
                                                case 0:
                                                    back4 = true;
                                                    break;
                                            }
                                        }

                                        break;
                                    ///  see and add music.
                                    case 2:
                                        System.out.println("1-Show List of Musics");
                                        System.out.println("2-Add New Music");
                                        System.out.println("0-Back");

                                        while (true) {
                                            switch (sc.nextInt()) {
                                                case 1:
                                                    for (Album a1 : currentSinger.getAlbums()) {
                                                        if (a1.getName().equals("Single-Track")) {
                                                            for (int i = 0; i < a1.getMusics().size(); i++) {
                                                                System.out.println(i + 1 + "-" + a1.getMusics().get(i).getName());
                                                            }
                                                        }
                                                    }
                                                    Album a4 = null;
                                                    for (Album a : currentSinger.getAlbums()) {
                                                        if (a.getName().equals("Single-Track")) {
                                                            a4 = a;
                                                        }
                                                    }
                                                    if (a4.getMusics().size() > 0) {
                                                        int number = sc.nextInt();
                                                        if (number >= 1) {
                                                            number--;
                                                        } else {
                                                            System.out.println("Error!");
                                                        }
                                                        System.out.println("Title: " + a4.getMusics().get(number).getName());
                                                        System.out.println("Singer: " + a4.getMusics().get(number).getSinger().getFirstName() + a4.getMusics().get(number).getSinger().getLastName());
                                                        System.out.println("Text: " + a4.getMusics().get(number).getText());
                                                        System.out.println("Viewed: " + a4.getMusics().get(number).getView());
                                                        System.out.println("------------------------");
                                                    }
                                                    break;
                                                case 2:
                                                    System.out.println("Music name: ");
                                                    String MusicName = sc.next();
                                                    System.out.println("Send Music Text: ");
                                                    sc.nextLine();
                                                    String MusicText = sc.nextLine();
                                                    boolean isMakeMusic = false;
                                                    for (Album a : currentSinger.getAlbums()) {
                                                        if (a.getName().equals("Single-Track")) {
                                                            Music newMusic = new Music(MusicName, currentSinger, MusicText);
                                                            currentSinger.addMusic(newMusic);
                                                            a.addMusic(newMusic);
                                                            newMusic.setAlbum(a);
                                                            isMakeMusic = true;
                                                            break;
                                                        }
                                                    }
                                                    if (!isMakeMusic) {
                                                        Album Single_Track = new Album("Single-Track", currentSinger);
                                                        currentSinger.addAlbum(Single_Track);
                                                        Music newMusic = new Music(MusicName, currentSinger, MusicText);
                                                        currentSinger.addMusic(newMusic);
                                                        Single_Track.addMusic(newMusic);
                                                        newMusic.setAlbum(Single_Track);
                                                    }

                                                    SAVE.saveToFile();
                                                    System.out.println("Music created!");
                                                    break;
                                            }

                                        }

                                }
                                ///  in this case, first sort comments by time then show title and time of them.
                            case 3:
                                for (Album al : currentSinger.getAlbums()) {
                                    for (Music mu : al.getMusics()) {
                                        for (int i = 0; i < mu.getComments().size(); i++) {
                                            for (int j = i; j < mu.getComments().size(); j++) {
                                                if (mu.getComments().get(i).getRealTime().isBefore(mu.getComments().get(j).getRealTime())) {
                                                    Comment temp = mu.getComments().get(i);
                                                    mu.getComments().set(i, mu.getComments().get(j));
                                                    mu.getComments().set(j, temp);
                                                }
                                            }
                                        }
                                    }
                                }
                                for (Album a : currentSinger.getAlbums()) {
                                    for (Music m : a.getMusics()) {
                                        for (Comment c : m.getComments()) {
                                            System.out.println("\uD83D\uDDE8\uFE0F" + c.getTitle() + "|" + "\uFE0F" + c.getTime());
                                        }
                                    }
                                }

                                break;
                            ///  in this case, first sort music by view then show name and view of them.
                            case 4:
                                int counter3 = 1;
                                int maxCount = 1;
                                int pageNumber = 1;
                                int page;
                                ArrayList<Music> arrLMusic = new ArrayList<>();
                                for (Singer s : SAVE.getSingers()) {
                                    for (Album a : s.getAlbums()) {
                                        for (Music m : a.getMusics()) {
                                            arrLMusic.add(m);
                                        }
                                    }
                                }
                                page = (int) Math.ceil(arrLMusic.size() / 5.0);
                                for (int i = 0; i < arrLMusic.size(); i++) {
                                    for (int j = i; j < arrLMusic.size(); j++) {
                                        if (arrLMusic.get(i).getView() < arrLMusic.get(j).getView()) {
                                            Music temp = arrLMusic.get(i);
                                            arrLMusic.set(i, arrLMusic.get(j));
                                            arrLMusic.set(j, temp);
                                        }
                                    }
                                }
                                while (true) {
                                    counter3 = 5 * (pageNumber - 1) + 1;
                                    maxCount = counter3;
                                    for (int i = counter3; i < Math.min(arrLMusic.size(), maxCount + 5); i++) {
                                        System.out.println(counter3 + "-" + arrLMusic.get(i).getName() + "|" + arrLMusic.get(i).getView());
                                        counter3++;
                                    }
                                    System.out.println("Page: " + pageNumber + "/" + page);
                                    System.out.println("------Guide Box-------------------------");
                                    System.out.println(">> Enter one letter to select:");
                                    System.out.println("  >> 's' --> select");
                                    System.out.println("  >> 'n' --> next page");
                                    System.out.println("  >> 'p' --> previous page");
                                    System.out.println("  >> 'q' --> back to manu");
                                    System.out.println("----------------------------------------");
                                    String back = sc.next();
                                    if (back.equals("Q") || back.equals("q")) {
                                        break;
                                    } else if (back.equals("n".toLowerCase()) && pageNumber < page) {
                                        pageNumber++;
                                    } else if (back.equals("p".toLowerCase()) && pageNumber > 1) {
                                        pageNumber--;
                                    } else if (back.equals("s".toLowerCase())) {
                                        boolean back4 = false;
                                        while (true){
                                            if (back4){
                                                break;
                                            }
                                            int select = sc.nextInt();
                                            if (0 < select && select <= arrLMusic.size()) {
                                                select--;
                                                System.out.println("\uD83C\uDFB5 " + arrLMusic.get(select).getName());
                                                System.out.println("\uD83C\uDF99\uFE0F " + arrLMusic.get(select).getSinger().getFirstName() + " " + arrLMusic.get(select).getSinger().getLastName());
                                                arrLMusic.get(select).increaseView();
                                                System.out.println("\uD83D\uDC41\uFE0F\u200D\uD83D\uDDE8\uFE0F View: " + arrLMusic.get(select).getView());
                                                System.out.println("----------------------------------------");
                                                System.out.println("\uD83D\uDDB9 " + arrLMusic.get(select).getText());
                                                System.out.println("----------------------------------------");
                                                for (Comment c : arrLMusic.get(select).getComments()) {
                                                    System.out.println(c.getUser().getUsername() + ":               " + c.getTime());
                                                    System.out.println(c.getText());
                                                    System.out.println("~~~~~~~~~~~~~~~~~~~~~");
                                                }
                                                System.out.println("------Guide Box-------------------------");
                                                System.out.println(">> Enter one number:");
                                                System.out.println("  >> 1 --> add comment");
                                                System.out.println("  >> 2 --> add this song to favorite songs");
                                                System.out.println("  >> 3 --> like this song");
                                                System.out.println("  >> 4 --> suggest text for music");
                                                System.out.println("  >> 0 --> back");
                                                System.out.println("----------------------------------------");

                                                int add = sc.nextInt();
                                                switch (add) {
                                                    case 1:
                                                        System.out.println("Title of your comment: ");
                                                        String commentTitle = sc.next();
                                                        System.out.println("Enter your comment...");
                                                        sc.nextLine();
                                                        String newComment = sc.nextLine();
                                                        Comment comment = new Comment(commentTitle, newComment);
                                                        currentUser.addComment(comment);
                                                        arrLMusic.get(select).addComment(comment);
                                                        break;
                                                    case 2:
                                                        currentUser.addMusicToFavoriteMusics(arrLMusic.get(select));
                                                        System.out.println("add successfully");
                                                        break;
                                                    case 3:
                                                        arrLMusic.get(select).increaseLike();
                                                        System.out.println("this music have " + arrLMusic.get(select).getLike() + " Like");
                                                        break;
                                                    case 4:
                                                        System.out.println("You can suggest text for music,this text show when artist accept it");
                                                        String suggestedText = sc.nextLine();
                                                        arrLMusic.get(select).addTextToSuggestedText(suggestedText);
                                                        System.out.println("Your Text send to artist.");
                                                        break;
                                                    case 0:
                                                        back4 = true;
                                                        break;
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                        }
                    }
                    break;
                ///  User login;
                ///  if user don't exist, new user created.
                case 3:
                    for (User u : SAVE.getUsers()) {
                        if (usernameForCheck.equals(u.getUsername())) {
                            foundUser = true;
                            currentUser = u;
                            while (!passwordForCheck.equals(u.getPassword())) {
                                System.out.println("your password is not correct");
                                System.out.println("Please Enter again");
                                passwordForCheck = sc.next();
                            }
                            System.out.println("Welcome to app");
                            break;
                        }
                    }
                    if (!foundUser) {
                        System.out.println("You don't have any account");
                        System.out.println("Enter your first name...");
                        String firstnameForCheck = sc.next();
                        System.out.println("Enter your last name...");
                        String lastnameForCheck = sc.next();
                        System.out.println("Enter your age...");
                        int ageForCheck = sc.nextInt();
                        System.out.println("Enter your email...");
                        String emailForCheck = sc.next();
                        User newUser = new User(firstnameForCheck, lastnameForCheck, ageForCheck, emailForCheck, usernameForCheck, passwordForCheck);
                        SAVE.addUser(newUser);
                        SAVE.saveToFile();
                        currentUser = newUser;
                    }
                    ///  This while is for Users;
                    boolean back2 = false;
                    while (true) {
                        if (back2) {
                            break;
                        }
                        System.out.println("1-Profile");
                        System.out.println("2-Popular Musics");
                        System.out.println("3-Followed Artist");
                        System.out.println("4-Favorite Musics");
                        switch (sc.nextInt()) {
                            ///  see information and change them.
                            case 1:
                                boolean back1 = false;
                                while (true) {
                                    if (back1) {
                                        break;
                                    }
                                    System.out.println("1-Change Password");
                                    System.out.println("2-Edit Information");
                                    System.out.println("3-Logout");
                                    System.out.println("4-back");
                                    switch (sc.nextInt()) {
                                        ///  change password.
                                        case 1:
                                            while (true) {
                                                System.out.println("Please Enter your password...");
                                                String password = sc.next();
                                                while (!password.equals(currentUser.getPassword())) {
                                                    System.out.println("your password is not correct.Please try again...");
                                                    password = sc.next();
                                                }
                                                System.out.println("Please Enter new password(6 to 8 character)...");
                                                password = sc.next();
                                                while (!(6 <= password.length() && password.length() <= 8) || password.equals(currentUser.getPassword())) {
                                                    System.out.println("Try again, Please enter new password between 6 to 8 character");
                                                    password = sc.next();
                                                }
                                                System.out.println("Confirm your password...");
                                                String password2 = sc.next();
                                                while (!password2.equals(password)) {
                                                    System.out.println("your password do not match with confirmation password");
                                                    password2 = sc.next();
                                                }
                                                currentUser.setPassword(password);
                                                SAVE.saveToFile();
                                                System.out.println("Your password change successfully");
                                                String back = sc.next();
                                                if (back.equals("q") || back.equals("Q")) {
                                                    break;
                                                } else {
                                                    System.out.println("Not found");
                                                }
                                            }
                                            break;
                                        /// information
                                        case 2:
                                            System.out.println("Your information is: ");
                                            System.out.println("Username: " + currentUser.getUsername());
                                            System.out.println("Email: " + currentUser.getEmail());
                                            System.out.println("First name: " + currentUser.getFirstName());
                                            System.out.println("Last name: " + currentUser.getLastName());
                                            System.out.println("Age: " + currentUser.getAge());
                                            System.out.println("Enter q and back");
                                            System.out.println("------Guide Box-------------------------");
                                            System.out.println(">> Enter one letter to select:");
                                            System.out.println("  >> 's' --> select");
                                            System.out.println("  >> 'q' --> back to manu");
                                            System.out.println("----------------------------------------");
                                            String back = sc.next();
                                            switch (back) {
                                                case "s":
                                                    System.out.println("Enter number of information you want to edit...");
                                                    int select = sc.nextInt();
                                                    switch (select) {
                                                        case 1:
                                                            System.out.println("New 'first name':");
                                                            currentUser.setFirstName(sc.next());
                                                            break;
                                                        case 2:
                                                            System.out.println("New 'last name':");
                                                            currentUser.setLastName(sc.next());
                                                            break;
                                                        case 3:
                                                            System.out.println("New 'Age':");
                                                            currentUser.setAge(sc.nextInt());
                                                            break;
                                                        case 4:
                                                            System.out.println("New 'Username':");
                                                            currentUser.setUsername(sc.next());
                                                            break;
                                                        case 5:
                                                            System.out.println("New 'Email':");
                                                            currentUser.setEmail(sc.next());
                                                            break;
                                                        default:
                                                            System.out.println("Please Enter number from menu!!");
                                                            break;
                                                    }
                                                    break;
                                                case "q":
                                                    break;
                                            }
                                            break;
                                        case 3:
                                            back1 = true;
                                            back2 = true;
                                            currentUser = null;
                                            break;
                                        case 4:
                                            back1 = true;
                                            break;
                                    }
                                }
                                break;
                            ///  show most viewed musics.
                            case 2:
                                int counter3;
                                int maxCount;
                                int pageNumber = 1;
                                int page;
                                ArrayList<Music> arrLMusic = new ArrayList<>();
                                for (Singer s : SAVE.getSingers()) {
                                    for (Album a : s.getAlbums()) {
                                        for (Music m : a.getMusics()) {
                                            arrLMusic.add(m);
                                        }
                                    }
                                }
                                page = (int) Math.ceil(arrLMusic.size() / 5.0);
                                for (int i = 0; i < arrLMusic.size(); i++) {
                                    for (int j = i; j < arrLMusic.size(); j++) {
                                        if (arrLMusic.get(i).getView() < arrLMusic.get(j).getView()) {
                                            Music temp = arrLMusic.get(i);
                                            arrLMusic.set(i, arrLMusic.get(j));
                                            arrLMusic.set(j, temp);
                                        }
                                    }
                                }
                                boolean back4 = false;
                                while (true) {
                                    if (back4) {
                                        break;
                                    }
                                    counter3 = 5 * (pageNumber - 1);
                                    maxCount = counter3;
                                    for (int i = counter3; i < Math.min(arrLMusic.size(), maxCount + 5); i++) {
                                        System.out.println(counter3 + 1 + "-" + arrLMusic.get(i).getName() + "|" + arrLMusic.get(i).getView());
                                        counter3++;
                                    }
                                    System.out.println("Page: " + pageNumber + "/" + page);
                                    System.out.println("------Guide Box-------------------------");
                                    System.out.println(">> Enter one letter to select:");
                                    System.out.println("  >> 's' --> select");
                                    System.out.println("  >> 'n' --> next page");
                                    System.out.println("  >> 'p' --> previous page");
                                    System.out.println("  >> 'q' --> back to manu");
                                    System.out.println("----------------------------------------");
                                    String back = sc.next();
                                    if (back.equals("Q") || back.equals("q")) {
                                        break;
                                    } else if (back.equals("n".toLowerCase()) && pageNumber < page) {
                                        pageNumber++;
                                    } else if (back.equals("p".toLowerCase()) && pageNumber > 1) {
                                        pageNumber--;
                                    } else if (back.equals("s".toLowerCase())) {
                                        int select = sc.nextInt();
                                        if (0 < select && select <= arrLMusic.size()) {
                                            select--;
                                            System.out.println("\uD83C\uDFB5 " + arrLMusic.get(select).getName());
                                            System.out.println("\uD83C\uDF99\uFE0F " + arrLMusic.get(select).getSinger().getFirstName() + " " + arrLMusic.get(select).getSinger().getLastName());
                                            arrLMusic.get(select).increaseView();
                                            System.out.println("\uD83D\uDC41\uFE0F\u200D\uD83D\uDDE8\uFE0F View: " + arrLMusic.get(select).getView());
                                            System.out.println("\uD83D\uDC4D Like: " + arrLMusic.get(select).getLike());
                                            System.out.println("----------------------------------------");
                                            System.out.println("\uD83D\uDDB9 " + arrLMusic.get(select).getText());
                                            System.out.println("----------------------------------------");
                                            for (Comment c : arrLMusic.get(select).getComments()) {
                                                System.out.println(c.getUser().getUsername() + ":               " + c.getTime());
                                                System.out.println(c.getText());
                                                System.out.println("~~~~~~~~~~~~~~~~~~~~~");
                                            }
                                            System.out.println(">> Enter one number:");
                                            System.out.println("  >> 1 --> add comment");
                                            System.out.println("  >> 2 --> add this song to favorite songs");
                                            System.out.println("  >> 3 --> like this song");
                                            System.out.println("  >> 4 --> follow this singer");
                                            System.out.println("  >> 0 --> back");
                                            int add = sc.nextInt();
                                            switch (add) {
                                                case 1:
                                                    System.out.println("Title of your comment: ");
                                                    String commentTitle = sc.next();
                                                    System.out.println("Enter your comment...");
                                                    sc.nextLine();
                                                    String newComment = sc.nextLine();
                                                    Comment comment = new Comment(commentTitle, newComment);
                                                    currentUser.addComment(comment);
                                                    arrLMusic.get(select).addComment(comment);
                                                    break;
                                                case 2:
                                                    currentUser.addMusicToFavoriteMusics(arrLMusic.get(select));
                                                    System.out.println("add successfully");
                                                    break;
                                                case 3:
                                                    arrLMusic.get(select).increaseLike();
                                                    System.out.println("this music have " + arrLMusic.get(select).getLike() + " Like");
                                                    break;
                                                case 4:
                                                    boolean shouldBack = false;
                                                    boolean isFollowing = false;
                                                    for (Singer s : currentUser.getFollowedSingers()) {
                                                        if (s.equals(arrLMusic.get(select).getSinger())) {
                                                            isFollowing = true;
                                                            System.out.println("You follow this singer!");
                                                            System.out.println("Do you want unfollow this singer?(yes/no)");
                                                            String yesOrNo = sc.next();
                                                            if (yesOrNo.equalsIgnoreCase("yes")) {
                                                                currentUser.getFollowedSingers().remove(s);
                                                                System.out.println("Unfollow successfully!");
                                                                shouldBack = true;
                                                                isFollowing = false;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    if (shouldBack) {
                                                        break;
                                                    } else {
                                                        if (!isFollowing) {
                                                            currentUser.addSingerToFollowedSinger(arrLMusic.get(select).getSinger());
                                                            System.out.println("Followed!");
                                                            break;
                                                        }
                                                    }
                                                    break;
                                                case 0:
                                                    back4 = true;
                                                    break;
                                            }
                                        }
                                    }
                                }
                                break;
                            ///  show followed music.
                            case 3:
                                int counter2 = 1;
                                int maxCount2 = 1;
                                int pageNumber2 = 1;
                                int page2;
                                page2 = (int) Math.ceil(currentUser.getFollowedSingers().size() / 5.0);
                                while (true) {
                                    counter2 = 5 * (pageNumber2 - 1) + 1;
                                    maxCount2 = counter2;
                                    for (int i = counter2 - 1; i < Math.min(currentUser.getFollowedSingers().size(), maxCount2 + 5); i++) {
                                        System.out.println(counter2 + "-" + currentUser.getFollowedSingers().get(i).getFirstName() + " " + currentUser.getFollowedSingers().get(i).getLastName());
                                        counter2++;
                                    }
                                    System.out.println("Page: " + pageNumber2 + "/" + page2);
                                    System.out.println(">> Send 'q' to Quit, 'n' to next and 'p' to previous pages <<");
                                    String back = sc.next();
                                    if (back.equals("Q") || back.equals("q")) {
                                        break;
                                    } else if (back.equals("n".toLowerCase()) && pageNumber2 < page2) {
                                        pageNumber2++;
                                    } else if (back.equals("p".toLowerCase()) && pageNumber2 > 1) {
                                        pageNumber2--;
                                    }
                                }
                                break;
                            ///  show favorite musics
                            case 4:
                                int counter1 = 1;
                                int maxCount1 = 1;
                                int pageNumber1 = 1;
                                int page1;
                                page1 = (int) Math.ceil(currentUser.getFavoriteMusics().size() / 5.0);
                                while (true) {
                                    counter1 = 5 * (pageNumber1 - 1) + 1;
                                    maxCount1 = counter1;
                                    for (int i = counter1 - 1; i < Math.min(currentUser.getFavoriteMusics().size(), maxCount1 + 5); i++) {
                                        System.out.println(counter1 + "-" + currentUser.getFavoriteMusics().get(i).getName() + " >> " + currentUser.getFavoriteMusics().get(i).getSinger().getFirstName() + " " + currentUser.getFavoriteMusics().get(i).getSinger().getLastName());
                                        counter1++;
                                    }
                                    System.out.println("Page: " + pageNumber1 + "/" + page1);
                                    System.out.println(">> Send 'q' to Quit, 'n' to next and 'p' to previous pages <<");
                                    String back = sc.next();
                                    if (back.equals("Q") || back.equals("q")) {
                                        break;
                                    } else if (back.equals("n".toLowerCase()) && pageNumber1 < page1) {
                                        pageNumber1++;
                                    } else if (back.equals("p".toLowerCase()) && pageNumber1 > 1) {
                                        pageNumber1--;
                                    }
                                }
                                break;

                        }
                    }
                    break;
                case 4:
                    return;
            }
        }

    }
}