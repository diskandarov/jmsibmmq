package ru.iskdan.jmsibmmq;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {
    public static ArrayList<String[]> mainRead() throws FileNotFoundException {
        File file = new File("E:\\JavaPr\\jmsibmmq\\tagList.txt");
        Scanner scanner = new Scanner(file);
        String[] numbersSt = new String[0];
        ArrayList<String[]> tagList = new ArrayList<>();
        int counter = 0;
        while (scanner.hasNextLine()) {
            counter++;
            String line = scanner.nextLine();
            numbersSt = line.split(";");
            tagList.add(numbersSt);
        }
        scanner.close();
    return tagList;
    }
}