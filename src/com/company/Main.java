package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void add() {
        boolean favorite;
        System.out.print("Name of Anime: ");
        String addName = in.nextLine();

        System.out.print("Short Description: ");
        String addDescription = in.nextLine();

        System.out.print("Amount of Seasons: ");
        String addSeasons = in.nextLine();

        while (true) {
            System.out.print("Favorite: ");
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

    public static void delete() {
        System.out.print("Name of the anime: ");
        String deleteInput = in.nextLine();
        System.out.print("Are you sure you want to delete " + deleteInput + "? (Warning: misspelling will be taken as a no!) ");
        String choicesInput = in.nextLine();
        if (choicesInput.equals("yes")) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:postgresql:anime-app");
                PreparedStatement st = connection.prepareStatement(
                        "DELETE FROM ANIME WHERE name=?;"
                );
                st.setString(1, deleteInput);
                st.executeUpdate();
                st.close();
                System.out.println("DELETED");
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("you cannot delete your anime's at the moment!");
            }
        }
    }

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

    public static void main(String[] args) {
        System.out.println("Welcome to Animoo Hub where you can [add] anime's, [update] them, [delete] them from your list, [view] them, or [quit].");
        while (true) {
            System.out.println("What would you like to do?");
            System.out.print("> ");
            String choice = in.nextLine();

            if (choice.equals("add")) {
                add();

            } else if (choice.equals("update")) {
                System.out.print("Name of anime to update: ");
                String updatedInput = in.nextLine();
                while (true) {
                    System.out.println("Would you like to Update for this anime? The [name], [description], how many [seasons] there are, change the [favorite], or go [back]?");
                    System.out.print("> ");
                    String updateStr = in.nextLine();
                    if (updateStr.equals("name")) {
                        updateName(updatedInput);
                    } else if (updateStr.equals("description")) {
                        updateDescription(updatedInput);
                    } else if (updateStr.equals("seasons")) {
                        updateSeasons(updatedInput);
                    } else if (updateStr.equals("favorite")) {
                        updateFavorite(updatedInput);
                    } else if (updateStr.equals("back")) {
                        break;
                    } else {
                        System.out.println("wrong Input");
                    }
                }

            } else if (choice.equals("delete")) {
                delete();

            } else if (choice.equals("view")) {
                while (true) {
                    System.out.println("Would you like to view [all], [search] for a anime, view by [favorite], or go [back]?");
                    System.out.print("> ");
                    String choiceView = in.nextLine();
                    if (choiceView.equals("all")) {
                        viewAll();
                    } else if (choiceView.equals("search")) {
                        viewSearch();
                    } else if (choiceView.equals("favorite")) {
                        viewFavorite();
                    } else if (choiceView.equals("back")) {
                        break;
                    } else {
                        System.out.println("choose a answer!");
                    }
                }

            } else if (choice.equals("quit")) {
                break;

            } else {
                System.out.println("I don't understand, Please try again!");
            }
        }
    }
}
