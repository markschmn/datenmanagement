package com.scheiermann.datenmanagement.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileCopyUtility {

    public static void copyFiles(Path sourceDir) {
        if(!Files.exists(sourceDir) || !Files.isDirectory(sourceDir)) {
            System.out.println("Der Quellordner existiert nicht oder ist kein Verzeichnis.");
            return;
        }

        Path pngDir = sourceDir.resolve("PNG");
        Path jpgDir = sourceDir.resolve("JPG");

        try {
            if(!Files.exists(pngDir)) {
                Files.createDirectory(pngDir);
                System.out.println("Unterordner 'PNG' wurde erstellt: " + pngDir);
            }
            if(!Files.exists(jpgDir)) {
                Files.createDirectory(jpgDir);
                System.out.println("Unterordner 'JPG' wurde erstellt: " + jpgDir);
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Erstellen der Unterordner: " + e.getMessage());
            return;
        }

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourceDir)) {
            for (Path file : directoryStream) {
                String fileName = file.getFileName().toString().toLowerCase();
                if(Files.isRegularFile(file) && (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))) {
                    Path targetSubDir = fileName.endsWith(".png") ? pngDir : jpgDir;
                    Path targetPath = targetSubDir.resolve(file.getFileName());

                    Files.move(file, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Datei verschoben: " + file.getFileName());
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Verschieben der Dateien: " + e.getMessage());
        }
    }

}
