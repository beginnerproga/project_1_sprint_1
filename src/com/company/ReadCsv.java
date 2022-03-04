package com.company;
import java.io.*;

public class ReadCsv { //Класс для чтения csv файла
    String path;

    public ReadCsv(String path) {
        this.path = path;
    }

    public String readingCsv() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(path), "Cp1251"));
            String fileContent = "";
            String str;
            while ((str = br.readLine()) != null) {
                fileContent += str;
                fileContent += ",";
            }
            fileContent = removeLastChar(fileContent);
            //System.out.println(fileContent);
            br.close();
            return(fileContent) ;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Чтение файла не произошло, возможно указана неверная директория");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Чтение файла не произошло, возможно указана неверная директория");
            return null;
        }
    }

    private String removeLastChar(String str) {
        if (str == null || str.length() == 0)
            return null;
        else {
            str = str.substring(0, str.length() - 1);
            return str;
        }
    }
}