package pl.coderslab;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) throws IOException {
        options();
        try {
            add(fileArray("tasks.csv"));
            list(fileArray("tasks.csv"));
        } catch (IOException e) {
            System.out.println("Brak takiego pliku");
            e.printStackTrace();
        }

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

    private static String[][] add(String[][] arr) {
        Scanner scanner = new Scanner(System.in);
        String[][] arr1 = Arrays.copyOf(arr, arr.length + 1);
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
        arr1[arr.length] = newArr;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                System.out.println(arr1[i][j]);
            }
        }
        return arr1;
    }

}



