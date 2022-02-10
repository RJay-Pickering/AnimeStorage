package com.company;

import static com.company.Main.in;

import java.sql.*;

public class Condition {
    public static void isAInput (String updatedInput) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql:anime-app");
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM ANIME WHERE name=?"
            );
            st.setString(1, updatedInput);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                rs.getString("name");
                isTrue(updatedInput);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void isTrue (String updatedInput) {
        while (true) {
            System.out.println("Would you like to Update for " + updatedInput + "? The [name], [description], how many [seasons] there are, change the [favorite], or go [back]?");
            System.out.print("> ");
            String updateStr = in.nextLine();
            if (updateStr.equals("name")) {
                Update.updateName(updatedInput);
            } else if (updateStr.equals("description")) {
                Update.updateDescription(updatedInput);
            } else if (updateStr.equals("seasons")) {
                Update.updateSeasons(updatedInput);
            } else if (updateStr.equals("favorite")) {
                Update.updateFavorite(updatedInput);
            } else if (updateStr.equals("back")) {
                break;
            } else {
                System.out.println("wrong Input");
            }
        }
    }
}
