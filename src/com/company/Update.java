package com.company;

import java.sql.*;

import static com.company.Main.in;

public class Update {
    public static void updateName(String deleteInput) {
        System.out.print("New name for " + deleteInput + ": ");
        String newInput = in.nextLine();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql:anime-app");
            PreparedStatement st = connection.prepareStatement(
                    "UPDATE ANIME SET name=? WHERE name=?;"
            );
            st.setString(1, newInput);
            st.setString(2, deleteInput);
            st.executeUpdate();
            st.close();
            System.out.println("Name updated from " + deleteInput + " to " + newInput);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("you cannot update your anime's name at the moment!");
        }
    }

    public static void updateDescription(String deleteInput) {
        System.out.print("New description for " + deleteInput + ": ");
        String newInput = in.nextLine();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql:anime-app");
            PreparedStatement st = connection.prepareStatement(
                    "UPDATE ANIME SET description=? WHERE name=?;"
            );
            st.setString(1, newInput);
            st.setString(2, deleteInput);
            st.executeUpdate();
            st.close();
            System.out.println("description updated for " + deleteInput + "!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("you cannot update your anime's description at the moment!");
        }
    }

    public static void updateSeasons(String deleteInput) {
        System.out.print("How many seasons are now in " + deleteInput + "? ");
        String newInput = in.nextLine();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql:anime-app");
            PreparedStatement st = connection.prepareStatement(
                    "UPDATE ANIME SET seasons=? WHERE name=?;"
            );
            st.setString(1, newInput);
            st.setString(2, deleteInput);
            st.executeUpdate();
            st.close();
            System.out.println(deleteInput + " Is now updated, with " + newInput + " seasons in it!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("you cannot update the amount of seasons your anime has, at the moment!");
        }
    }

    public static void updateFavorite(String deleteInput) {
        System.out.print("Is " + deleteInput + " your favorite? ");
        String newInput = in.nextLine();
        if (newInput.equals("yes")) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:postgresql:anime-app");
                PreparedStatement st = connection.prepareStatement(
                        "UPDATE ANIME SET favorite=true WHERE name=?;"
                );
                st.setString(1, deleteInput);
                st.executeUpdate();
                st.close();
                System.out.println(deleteInput + " Is now your favorite");
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("you cannot update your favorite anime, at the moment!");
            }
        } else if (newInput.equals("no")) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:postgresql:anime-app");
                PreparedStatement st = connection.prepareStatement(
                        "UPDATE ANIME SET favorite=false WHERE name=?;"
                );
                st.setString(1, deleteInput);
                st.executeUpdate();
                st.close();
                System.out.println(deleteInput + " Is now not favorite!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("you cannot update your hated anime, at the moment!");
            }
        } else {
            System.out.println("Please type [yes] or [no]!");
        }
    }
}
