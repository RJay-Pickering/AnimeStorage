package com.company;

import java.sql.*;

import static com.company.Main.in;

public class NewAnime {
    public static void add() {
        boolean favorite;
        System.out.print("Name of Anime: ");
        String addName = in.nextLine();

        System.out.print("Short Description: ");
        String addDescription = in.nextLine();

        System.out.print("Amount of Seasons: ");
        String addSeasons = in.nextLine();

        while (true) {
            System.out.print("Is " + addName +" your favorite Anime? ");
            String addFavorite = in.nextLine();
            if (addFavorite.equals("yes")) {
                favorite = true;
                break;
            } else if (addFavorite.equals("no")) {
                favorite = false;
                break;
            } else {
                System.out.println("Try answering yes or no in lower case!");
            }
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql:anime-app");
            PreparedStatement st = connection.prepareStatement(
                    "INSERT INTO ANIME (name, description, seasons, favorite) VALUES (?, ?, ?, ?)"
            );
            st.setString(1, addName);
            st.setString(2, addDescription);
            st.setString(3, addSeasons);
            st.setBoolean(4, favorite);
            st.executeUpdate();
            st.close();
            System.out.println("You have added this animoo!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("you already added this anime");
        }
    }
}
