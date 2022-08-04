import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    static String text;
    static Path folderPath;

    public static void main(String[] args) {
        for (;;) {
            try {
                System.out.println("Введите путь к папке:");
                text = getText();
                System.out.println("---------------------");
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

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    private static String getText() {
        return new Scanner(System.in).nextLine();
    }

    private static boolean isRight(String text){
        return text.matches("(/[a-zA-Z0-9_.-])?(/[a-zA-Z0-9_.-]+)+/?");
    }
}
