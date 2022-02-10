package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Animoo Hub where you can [add] anime's, [update] them, [delete] them from your list, [view] them, or [quit].");
        while (true) {
            System.out.println("What would you like to do?");
            System.out.print("> ");
            String choice = in.nextLine();

            if (choice.equals("add")) {
                NewAnime.add();

            } else if (choice.equals("update")) {
                System.out.print("Name of anime to update: ");
                String updatedInput = in.nextLine();
                Condition.isAInput(updatedInput);

            } else if (choice.equals("delete")) {
                RemoveAnime.delete();

            } else if (choice.equals("view")) {
                while (true) {
                    System.out.println("Would you like to view [all], [search] for a anime, view by [favorite], or go [back]?");
                    System.out.print("> ");
                    String choiceView = in.nextLine();
                    if (choiceView.equals("all")) {
                        Views.viewAll();
                    } else if (choiceView.equals("search")) {
                        Views.viewSearch();
                    } else if (choiceView.equals("favorite")) {
                        Views.viewFavorite();
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
