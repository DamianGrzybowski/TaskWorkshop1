package pl.coderslab;


import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        menu();

    }

    private static void options() {
        String[] arrayOptions = {"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        for (String option : arrayOptions) {
            System.out.println(ConsoleColors.RESET + option);
        }
    }

    private static String[][] fileArray(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        int index = 0;
        try (Scanner scan = new Scanner(path)) {
            while (scan.hasNextLine()) {
                index++;
                scan.nextLine();
            }
        }
        String[][] tasks = new String[index][3];
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                for (int i = 0; i < tasks.length; i++) {
                    String lines = scanner.nextLine();
                    String[] arrLines = lines.split(",");
                    for (int j = 0; j < tasks[i].length; j++) {
                        tasks[i][j] = arrLines[j];
                    }
                }

            }

        }
        return tasks;
    }


    private static String[][] add(String[][] arr) {
        Scanner scanner = new Scanner(System.in);
        String[][] tasks = Arrays.copyOf(arr, arr.length + 1);
        String[] newArr = new String[3];
        System.out.println("Please add task description");
        String task = scanner.nextLine();
        newArr[0] = task;
        System.out.println("Please add task due date");
        String date = scanner.nextLine();
        newArr[1] = date;
        System.out.println("Is your task is important: true/false");
        String important = scanner.nextLine();
        newArr[2] = important;
        tasks[arr.length] = newArr;
        return tasks;
    }

    private static String[][] remove(String[][] arr) {
        System.out.println("Please select number to remove");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            int taskNumber = Integer.parseInt(input);
            if (taskNumber >= 0 && taskNumber < arr.length) {
                arr = ArrayUtils.remove(arr, taskNumber);
                System.out.println("Value was successfully deleted");
            }else {
                System.out.println("Incorrect argument passed. Please give number greater or equal 0");
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect argument passed. Please give number greater or equal 0");
        }
        return arr;
    }

    private static void list(String[][] arr) throws IOException {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(index + " : ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
                if (j == arr[i].length - 1) {
                    System.out.println("");
                }
            }
            index++;
        }
    }

    private static void menu() {
        try {
            String[][] tasks = fileArray("tasks.csv");
            Scanner scanner = new Scanner(System.in);
            options();
            String input = scanner.nextLine();
            options();
            while (!"exit".equals(input)) {
                switch (input) {
                    case "add" -> {
                        System.out.println("add");
                        tasks = add(tasks);
                        System.out.println("");
                    }
                    case "list" -> {
                        System.out.println("list");
                        list(tasks);
                        System.out.println("");
                    }
                    case "remove" -> {
                        System.out.println("remove");
                        list(tasks);
                        System.out.println("");
                        tasks = remove(tasks);
                    }
                    default -> System.out.println("Please select a correct option.");
                }
                options();
                input = scanner.nextLine();
            }

        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

    }

}



