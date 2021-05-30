package com.tms11;

import java.io.*;
import java.util.*;

/**
 * Допустим есть файл с номерами документов.
 * Номером документа является строка, состоящая из букв и цифр(без служебных символов).
 * Пусть этот файл содержит каждый номер документа с новой строки и в строке никакой другой информации, только номер документа.
 * Валидный номер документа должен иметь длину 15 символов и начинаться с последовательности docnum
 * (далее любая последовательность букв/цифр) или kontract(далее любая последовательность букв/цифр).
 * <p>
 * Написать программу для чтения информации из входного файла - путь к входному файлу должне задаваться через консоль.
 * Программа должна проверять номера документов на валидность.
 * Валидные номера документов следует записать в один файл-отчет.
 * Невалидные номера документов следует записать в другой файл-отчет,
 * но после номеров документов следует добавить ифнформацию о том, почему этот документ невалиден.
 */
public class Runner {

    static Scanner scanner = new Scanner(System.in);
    static CustomList<String> fileList = new CustomList<>();
    static Set<String> numberSet = new HashSet<>();
    static Map<String, String> validatedNumsMap = new HashMap<>();
    static FileOutputStream fout = null;

    public static void main(String[] args) throws IOException {
        collectFiles();
        readFileAndWriteNumber();
        validateNumbers();
        createValidationReport();

    }

    private static void collectFiles() {
        System.out.println("Enter to console a names of files which you need to read (0 = is end of input) ");
        while (true) {
            String fileName = scanner.nextLine();
            if (isZero(fileName)) break;
            else fileList.addElement(fileName);
        }
        scanner.close();
    }

    private static void readFileAndWriteNumber() {
        BufferedReader reader;
        String line;
        for (int i = 0; i < fileList.size(); i++) {
            try {
                File file = new File(fileList.getElementByIndex(i));
                reader = new BufferedReader(new FileReader(file));
                while ((line = reader.readLine()) != null) {
                    numberSet.add(line);
                }
                reader.close();
                System.out.println(numberSet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void validateNumbers() {
        numberSet.forEach(num -> {
            if (num.length() == 15) {
                if ((num.startsWith("docnum")) || (num.startsWith("contract"))) {
                    validatedNumsMap.put(num, "correct number");
                } else {
                    validatedNumsMap.put(num, "incorrect number - does not start with \"docnum\" or \"contract\"");
                }
            } else {
                validatedNumsMap.put(num, "incorrect number - wrong length " + num.length());
            }
        });
    }

    private static void createValidationReport() throws IOException {
        StringBuilder sb = new StringBuilder();
        validatedNumsMap.forEach((key, value) -> sb.append(key).append(": ").append(value).append("\n"));
        writeToFile("report.txt", sb.toString());
    }

    private static void writeToFile(String fileName, String text) {
        File file = new File(fileName);
        try {
            file.createNewFile();
            fout = new FileOutputStream(file);
            byte[] bytes = text.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isZero(String str) {
        if (str.length() == 1) {
            return Integer.parseInt(str) == 0;
        }
        return false;
    }

}