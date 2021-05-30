package com.tms11;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * Допустим есть файл с номерами документов.
 * Номером документа является строка, состоящая из букв и цифр(без служебных символов).
 * Пусть этот файл содержит каждый номер документа с новой строки и в строке никакой другой информации, только номер документа.
 * Валидный номер документа должен иметь длину 15 символов и начинаться с последовательности docnum
 * (далее любая последовательность букв/цифр) или kontract(далее любая последовательность букв/цифр).
 *
 * Написать программу для чтения информации из входного файла - путь к входному файлу должне задаваться через консоль.
 * Программа должна проверять номера документов на валидность.
 * Валидные номера документов следует записать в один файл-отчет.
 * Невалидные номера документов следует записать в другой файл-отчет,
 * но после номеров документов следует добавить ифнформацию о том, почему этот документ невалиден.
 */
public class Runner {

    static StringBuilder validNumbers = new StringBuilder();
    static StringBuilder invalidNumbers = new StringBuilder();
    static Scanner input = new Scanner(System.in);
    static FileOutputStream fout = null;

    public static void main(String[] args) {
        System.out.print("Enter to console a file name (path):");
        List<String> docList = getDocumentNumbers(input.nextLine());
        for (String docNumber : docList) {
            if (isDocNumberValid(docNumber)) {
                validNumbers.append(docNumber).append("\n");
            } else {
                invalidNumbers.append(docNumber).append("\n");
            }
        }
        invalidNumbers.append("numbers are of the wrong length or do not contain \"docnum\", \"contract\"");
        input.close();
        writeToFile("valid.txt", validNumbers.toString());
        writeToFile("invalid.txt", invalidNumbers.toString());

    }

    private static List<String> getDocumentNumbers(String filePath) {
        List<String> docList = new ArrayList<>();
        BufferedReader reader;
        String line;
        try {
            File file = new File(filePath);
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                docList.add(line);
            }
            reader.close();
            System.out.println(docList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docList;
    }

    private static void writeToFile(String fileName, String text) {
        File file = new File(fileName);
        try {
            file.createNewFile();
            byte[] bytes = text.getBytes();
            fout = new FileOutputStream(file);
            fout.write(bytes);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isDocNumberValid(String docNumber) {
        if (docNumber.length() == 15) {
            return ((docNumber.startsWith("docnum")) || (docNumber.startsWith("contract")));
        } else {
            return false;
        }
    }

}