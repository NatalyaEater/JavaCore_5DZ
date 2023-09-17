package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileBackup {

    public static void main(String[] args) {
        String sourceDir = "D:\\java\\JavaCore_5DZ";
        String backupDir = "D:\\java\\JavaCore_5DZ_copy";

        try {
            createBackup(sourceDir, backupDir);
            System.out.println("Резервная копия создана ");
        } catch (IOException e) {
            System.out.println("Ошибка при создании резервной копии: " + e.getMessage());
        }
    }

    public static void createBackup(String sourceDir, String backupDir) throws IOException {
        File sourceDirectory = new File(sourceDir);
        File backupDirectory = new File(backupDir);

        /**
         *@param file.isDirectory()
        * Проверяет, является ли файл, обозначенный этим  путем, каталогом
        * Возвращается:-истинно только тогда, когда файл, обозначаемый этим путем, существует и является каталогом;
        * -ложно в противном случае*/

        if (!sourceDirectory.isDirectory()) {
            throw new IllegalArgumentException("Исходный путь не является каталогом");
        }

        /**
         * @param file.exists()
        * Проверяет, существует ли файл или каталог, обозначенный этим абстрактным путем.
        * Возвращается:истинно только тогда, когда файл или каталог, обозначенный этим абстрактным путем, существует;
        * ложно в противном случае*/

        /**
        * @param file.mkdirs()
        * Создает каталог, названный этим абстрактным путем, включая все необходимые, но несуществующие родительские каталоги.
        *  */

        if (!backupDirectory.exists()) {
            backupDirectory.mkdirs();
        }

        /**
        * @param FileInputStream — чтение байтов из файла.
        * @param FileOutputStream — запись байтов в файл.
        * @param byte размер буфера в байтах
         * @param int_len максимальное количество прочитанных байт
        * */

        File[] files = sourceDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    File backupFile = new File(backupDir + "/" + file.getName());
                    {
                        try (FileInputStream inputStream = new FileInputStream(file);
                             FileOutputStream outputStream = new FileOutputStream(backupFile)) {
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = inputStream.read(buffer)) > 0) {
                                outputStream.write(buffer, 0, len);
                            }
                        }
                    }
                }
            }
        }
    }
}
