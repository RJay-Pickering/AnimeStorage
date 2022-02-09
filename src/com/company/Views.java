package com.company;

import java.sql.*;

import static com.company.Main.in;

public class Views {
    public static void viewAll() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql:anime-app");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ANIME");
            while (rs.next())
            {
                System.out.print("Anime name: " + rs.getString("name") + ", ");
                System.out.print("Description: " + rs.getString("description") + ", ");
                System.out.print("Number of Seasons: " + rs.getString("seasons") + ", ");
                boolean fav = rs.getBoolean("favorite");
                if (fav) {
                    System.out.println("One of your Favorite");
                } else {
                    System.out.println("Not your Favorite");
                }
                System.out.println();
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("you cannot view your anime's at the moment!");
        }
    }

    public static void viewSearch() {
        System.out.print("Name of the anime: ");
        String searchInput = in.nextLine();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql:anime-app");
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM ANIME WHERE name=?"
            );
            st.setString(1, searchInput);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println("Anime name: " + rs.getString("name") + ", ");
                System.out.println("Description: " + rs.getString("description") + ", ");
                System.out.println("Number of Seasons: " + rs.getString("seasons") + ", ");
                boolean fav = rs.getBoolean("favorite");
                if (fav) {
                    System.out.println("One of your Favorite");
                } else {
                    System.out.println("Not your Favorite");
                }
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("you cannot view your anime's at the moment!");
        }
    }

    public static void viewFavorite() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql:anime-app");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ANIME WHERE favorite=true");
            while (rs.next())
            {
                System.out.println("Anime name: " + rs.getString("name") + ", ");
                System.out.println("Description: " + rs.getString("description") + ", ");
                System.out.println("Number of Seasons: " + rs.getString("seasons"));
                System.out.println();
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("you cannot view your anime's at the moment!");
        }
    }
}
