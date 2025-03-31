package com.mycompany.counttext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CountText {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputFile = "C:\\Users\\ADMIN\\Desktop\\Javacore Exercises\\CountText\\src\\input.txt"; 

        while (true) {
            int choice = getValidChoice(scanner);

            if (choice == 3) {
                System.out.println("Exiting program...");
                scanner.close();
                return; 
            }

            scanner.nextLine(); 

            try {
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                StringBuilder inputText = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    inputText.append(line).append(" ");
                }
                reader.close();

                if (choice == 1) {
                    System.out.println("\nFile Content:");
                    System.out.println(inputText.toString());
                } else if (choice == 2) {
                    String[] words = inputText.toString().split("\\s+");
                    Map<String, Integer> wordCountMap = new HashMap<>();

                    for (String word : words) {
                        word = word.toLowerCase().replaceAll("[^a-zA-Z]", ""); 
                        if (!word.isEmpty()) {
                            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                        }
                    }
 
                    Map<Integer, List<String>> sortedByCount = new TreeMap<>(Collections.reverseOrder());
                    for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                        sortedByCount
                            .computeIfAbsent(entry.getValue(), k -> new ArrayList<>())
                            .add(entry.getKey());
                    }

                    System.out.println("\nWord Count Results:");
                    for (Map.Entry<Integer, List<String>> entry : sortedByCount.entrySet()) {
                        System.out.println("Count: " + entry.getKey());
                        for (String word : entry.getValue()) {
                            System.out.println("Word: " + word);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
        }
    }

    public static int getValidChoice(Scanner scanner) {
        int choice = 0;
        boolean validChoice = false;
        
        while (!validChoice) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Display File Content");
            System.out.println("2. Check Word Count");
            System.out.println("3. Exit");
            System.out.print("Your choice (1, 2, or 3): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2 || choice == 3) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid input. Please enter 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                scanner.next(); 
            }
        }

        return choice;
    }
}
