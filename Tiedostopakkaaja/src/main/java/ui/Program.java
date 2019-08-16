package ui;

import utilities.FileHandler;
import algorithm.Huffman;
import utilities.Converter;

import java.util.Scanner;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Program {

  private static final Scanner scanner = new Scanner(System.in);
  private static Path inputFilePath = null;
  private static String inputFileName = "";
  private static String outputFilePath = "";
  private static String outputFileName = "";


  public static boolean askIfCompressOrUncompress() {
    String input = null;
    boolean compress = false;
    while (true) {
      System.out.println("Valitse valikon vaihtoehto:\n");
      System.out.println("(1) Pakkaa tekstitiedosto binääritiedostoksi");
      System.out.println("(2) Pura binääritiedosto tekstitiedostoksi");

      input = scanner.nextLine();

      if (input.equals("1")) {
        return true;
      }
      if (input.equals("2")) {
        return false;
      }
    }
  }

  public static boolean fileNameIsRightType(String fileName, String fileType) {
    if (fileName.length() <= fileType.length() || !fileName.substring(fileName.length() - fileType.length()).equals(fileType)) {
      return false;
    }
    return true;
  }

  public static void askInputFilePath(boolean compress) {
    String compressOrUncompress = "purkaa";
    String exampleFileName = "binary.bin";
    String fileType = ".bin";
    String input = "";

    if (compress) {
      fileType = ".txt";
      compressOrUncompress = "pakata";
      exampleFileName = "text.txt";
    }

    while (true) {
      System.out.println("\nSyötä tiedoston polku, jonka haluat " + compressOrUncompress + ": ");
      System.out.println("Esimerkiksi: " + Paths.get(System.getProperty("user.home"), "TiedostoPakkaaja", exampleFileName).toString());
      input = scanner.nextLine();

      inputFilePath = Paths.get(input);
      inputFileName = inputFilePath.getFileName().toString();

      if (!fileNameIsRightType(inputFileName, fileType)) {
        System.out.println("\n!!! Tiedoston nimen tulee päättyä " + fileType + " !!!");
      } else if (!FileHandler.fileExists(input) || !FileHandler.fileIsFile(input)) {
        System.out.println("\n!!! Annettu polku '" + input +  "' oli virheellinen");
        System.out.println("!!! Tiedostoa ei löydy tästä sijainnista !!!\n");
      } else if (!rootMatches(inputFilePath)) {
        System.out.println("\n!!! Annettu polku '" + input +  "' oli virheellinen !!!");
      } else {
        // Everything ok
        break;
      }
    }
  }

  public static boolean rootMatches(Path filePath) {
    String root = Paths.get(System.getProperty("user.home")).toString();
    return filePath.toString().substring(0, root.length()).equals(root);
  }

  public static void createOutputFilePath(boolean compress) {
    // Muuta tiedoston tyyppi
    String outputFileType = ".txt";
    if (compress) {
      outputFileType = ".bin";
    }

    outputFileName = inputFileName.substring(0, inputFileName.length() - 4) + outputFileType;

    // Tarkista, ettei tiedosto polkua ole jo olemassa
    while (true) {
      outputFilePath = inputFilePath.toString().substring(0, inputFilePath.toString().length() - inputFileName.length()) + outputFileName;
      if (FileHandler.fileExists(outputFilePath)) {
        // Tiedosto on jo olemassa
        System.out.println("\n!!! Tiedostopolku" + outputFilePath + " on jo olemassa !!!\n");
        outputFileName = askForANewFileName(outputFileType);
      } else {
        // Tiedostoa ei ole olemassa, jatketaan
        break;
      }
    }
  }

  public static String askForANewFileName(String fileType) {
    String fileName = "";
    String action = "purkaa";
    if (fileType.equals(".bin")) {
      action = "pakata";
    }
    while (true) {
      System.out.println("Anna uusi nimi tiedostolle, johon haluat " + action + " tiedoston (pääte " + fileType + "):");
      fileName = scanner.nextLine();
      if (fileNameIsRightType(fileName, fileType)) {
        break;
      } else {
        System.out.println("\n!!! Tiedoston nimen tulee päättyä " + fileType + " !!!\n");
      }
    }
    return fileName;
  }

  public static void start() {
    boolean compress = askIfCompressOrUncompress();

    askInputFilePath(compress);

    createOutputFilePath(compress);

    if (compress) {
      String text = FileHandler.readTextFromFile(inputFilePath.toString());

      String bitString = Huffman.encodeTextToBitString(text);

      byte[] bytes = Converter.stringToByteArray(bitString);

      FileHandler.writeBytesToFile(bytes, outputFilePath);

      System.out.println("\nTiedosto " + inputFileName + " pakattiin onnistuneesti sijaintiin " + outputFilePath);
    } else {
      ArrayList<Byte> bytes = FileHandler.readBytesFromFile(inputFilePath.toString());

      String bitString = Converter.bytesToString(bytes);

      String decodedText = Huffman.decodeBitStringToText(bitString);

      FileHandler.writeTextToFile(decodedText, outputFilePath);

      System.out.println("\nTiedosto " + inputFileName + " purettiin onnistuneesti sijaintiin " + outputFilePath);
    }

    long inputFileSize = FileHandler.fileSizeInBytes(inputFilePath.toString());
    long outputFileSize = FileHandler.fileSizeInBytes(outputFilePath);

    System.out.println("\nTiedoston " + inputFileName + " koko: " + inputFileSize + " tavua.");
    System.out.println("\nTiedoston " + outputFileName + " koko: " + outputFileSize + " tavua.\n");

    if (compress) {
      double percentage = (double)outputFileSize / (double)inputFileSize * 100.00;
      System.out.println("Tiedoston " + outputFileName + " koko on " + String.format("%.2f", percentage) + " % tiedoston " + inputFileName + " koosta.\n");
    }
  }
}