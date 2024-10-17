package com.scheiermann.datenmanagement;

import com.scheiermann.datenmanagement.utils.FileCopyUtility;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Datenmanagement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Path sourceDirectory = null;

        while (sourceDirectory == null) {
            System.out.print("Bitte den Pfad zum Quellordner eingeben (leer lassen, wenn selber Ordner): ");
            String sourceDirectoryPath = scanner.nextLine();
            sourceDirectory = Paths.get(sourceDirectoryPath.trim());

            if(!Files.exists(sourceDirectory) || !Files.isDirectory(sourceDirectory)) {
                System.out.println("Der Ordner existiert nicht oder ist kein Verzeichnis. Bitte erneut versuchen.");
                sourceDirectory = null;
            }
        }

        FileCopyUtility.copyFiles(sourceDirectory);

        scanner.close();
    }

}
