package com.mjc.school;

import com.mjc.school.controller.NewsController;
import com.mjc.school.utils.Menu;

import java.util.Scanner;

// Application start point
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        NewsController newsController = new NewsController();
        boolean start = true;
        while (start) {
            menu.printMenu();
            String userInput = scanner.next();
            switch (userInput) {
                case "1" -> menu.printAllNews(newsController);
                case "2" -> menu.printNewsById(newsController, scanner);
                case "3" -> menu.createNews(newsController, scanner);
                case "4" -> menu.updateNews(newsController, scanner);
                case "5" -> menu.removeNewsById(newsController, scanner);
                case "6" -> start = false;
                default -> System.out.println(userInput + " not found! Please make correct choice!");
            }
        }
    }
}
