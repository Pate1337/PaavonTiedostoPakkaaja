package utilities;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class FileHandler {

  public static void writeBytesToFile(byte[] bytes, String fileName) {
    // Tallenna taulukossa bytes olevat tavut tiedostoon
    try {
      DataOutputStream os = new DataOutputStream(new FileOutputStream(fileName));
      for (int i = 0; i < bytes.length; i++) {
        os.writeByte(bytes[i]);
      }
      os.close();
    } catch (Exception e) {
      System.out.println("Error writing to file");
    }
  }

  public static ArrayList<Byte> readBytesFromFile(String fileName) {
    // Lue tavut tiedostosta. Käytä ArrayList. Tiedoston koko tavuissa metodilla
    // File.length() on tyyppiä long. Taulukon koko ei voi olla long.
    ArrayList<Byte> bytesFromFile = new ArrayList<>();
    try {
      DataInputStream is = new DataInputStream(new FileInputStream(fileName));
      while (is.available() > 0) {
        bytesFromFile.add(is.readByte());
      }
      is.close();
    } catch (Exception e) {
      System.out.println("Error reading file");
    }
    return bytesFromFile;
  }

  public static boolean fileExists(String fileName) {
    File f = new File(fileName);
    if (f.exists()) {
      return true;
    }
    return false;
  }
  public static boolean fileIsFile(String fileName) {
    File f = new File(fileName);
    if (f.isFile()) {
      return true;
    }
    return false;
  }

  public static long fileSizeInBytes(String fileName) {
    File f = new File(fileName);
    if (f.exists() && f.isFile()) {
      return f.length();
    }
    return 0;
  }

  public static String readTextFromFile(String fileName) {
    String text = "";
    try {
      FileReader textFileReader = new FileReader(fileName);
      BufferedReader bufReader = new BufferedReader(textFileReader);
      // 500 kilotavua
      char[] buffer = new char[506000];
      int numberOfCharsRead = bufReader.read(buffer);
      while (numberOfCharsRead != -1) {
        text += String.valueOf(buffer, 0, numberOfCharsRead);
        numberOfCharsRead = textFileReader.read(buffer);
      }
      bufReader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return text;
  }

  public static void writeTextToFile(String text, String fileName) {
    try {
      FileWriter fw = new FileWriter(fileName);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(text);
      bw.close();    
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void deleteFile(String fileName) {
    File f = new File(fileName);
    f.delete();
  }
}