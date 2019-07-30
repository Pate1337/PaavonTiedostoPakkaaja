import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) {

    // String tyyppisen bittijonon tallentaminen ja lukeminen tiedostosta

    String string = "111111110001000011101101010100101011000000000000111110100010001111010011010101010000101000101000001111111110001010001011010110110100001010111111101010101000000101100000010100101110100101010100110100110101111001010101001100000000001111110010010100101101111110011110001011010010010101";

    System.out.println("Bittijono jota käsitellään:\n" + string);

    // Jos (string.length() + 7) / 8 > 2 147 483 647 (suurin int) niin bytes täytyy
    // muuttaa ArrayList. Taulukon koko voi olla maksimissaan edellä mainittu luku.
    // Vajaat tavut (% 8 != 0) täytetään 0-biteillä loppuun. Esim. 11 => 11000000.
    byte[] bytes = new byte[(string.length() + 7) / 8];
    for (int i = 0; i < string.length(); i++) {
      if (string.charAt(i) == '1') {
        // Manipuloi kohdassa bytes[i / 8] olevaa 00000000 tavua. Operaatiot selitetty alla.
        bytes[i / 8] |= 1 << (7 - (i % 8));
      }
    }

    /* 
      Bitwise operations:

      00000001 << 3 = 00001000 
      1 << 3 == 8 (Shift bit 1 to index 3)

      1010 | 0101 == 1111 (OR)
    */

    // Tallenna taulukossa bytes olevat tavut tiedostoon
    try {
      DataOutputStream os = new DataOutputStream(new FileOutputStream("result.bin"));
      for (int i = 0; i < bytes.length; i++) {
        os.writeByte(bytes[i]);
      }
      os.close();
    } catch (Exception e) {
      System.out.println("Error writing to file");
    }

    /*
    Tiedoston koko

    File f = new File("result.bin");
    if (f.exists() && f.isFile()) {
      System.out.println("Size of result.bin in bytes: " + f.length());
    }
    */

    // Lue tavut tiedostosta. Käytä ArrayList. Tiedoston koko tavuissa metodilla
    // File.length() on tyyppiä long. Taulukon koko ei voi olla long.
    ArrayList<Byte> bytesFromFile = new ArrayList<>();
    try {
      DataInputStream is = new DataInputStream(new FileInputStream("result.bin"));
      while (is.available() > 0) {
        bytesFromFile.add(is.readByte());
      }
      is.close();
    } catch (Exception e) {
      System.out.println("Error reading file");
    }

    // Tulosta tiedostosta luetut tavut.
    System.out.println("Tavut luettuna tiedostosta:");
    for (int i = 0; i < bytesFromFile.size(); i++) {
      System.out.print(Integer.toBinaryString((bytesFromFile.get(i) & 0xFF) + 0x100).substring(1));
    }
    System.out.println();
  }
}