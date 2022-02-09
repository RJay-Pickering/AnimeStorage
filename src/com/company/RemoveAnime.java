package com.company;

import java.sql.*;

import static com.company.Main.in;

public class RemoveAnime {
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
}
