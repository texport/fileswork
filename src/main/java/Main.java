import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    static Path folderPath;

    public static void main(String[] args) {
        for(;;) {
            try {
                System.out.println("Введите номер задания(1,2):");
                switch (getText()) {
                    case "1" -> task1();
                    case "2" -> task2();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static String getText(){
        return new Scanner(System.in).nextLine();
    }

    private static boolean isRight(String text){
        return text.matches("(/[a-zA-Z0-9_.-])?(/[a-zA-Z0-9_.-]+)+/?");
    }

    private static void folderWorkflow(String text) throws IOException {
        if(isRight(text) && (new File(text).isDirectory())) {
            folderPath = Paths.get(text);
            long size = Files.walk(folderPath)
                    .filter(p -> p.toFile().isFile())
                    .mapToLong(p -> p.toFile().length())
                    .sum();
            System.out.println("Размер папки " + folderPath + " составляет " + (((size/1024.0)/1024.0)/1024.0) + " ГБ");
        } else {
            System.out.println("Либо каталога нет, либо ошибка в пути, проверяй");
        }
    }

    private static void task1() throws IOException {
        System.out.println("---------------------");
        System.out.println("Введите путь к папке:");
        folderWorkflow(getText());
        System.out.println("---------------------");
    }

    private static void task2() throws IOException {
        System.out.println("Вы вошли в программу копирование папок, пожалуйста укажите какую папку вы хотите скопировать:");
        copyDirectoryAndFiles(getText(), getText());
    }

    private static void copyDirectoryAndFiles(String at, String to) throws IOException {
        if (isRight(at) && isRight(to)) {
            File source = new File(at);
            File destination = new File(to);
            FileUtils.copyDirectory(source, destination);
        }
    }
}
