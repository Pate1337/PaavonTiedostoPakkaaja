package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.util.ArrayList;

// Tämä luokka toteuttaa tällä hetkellä vain bittijonon kirjoittamisen tiedostoon ja sieltä
// lukemisen heti. Tiedoston lukeminen ja bittioperaatiot pitää erityttää omiin luokkiinsa.
public class NotWellImplementedYet {
  public static String doRefactoring(String bitString) {
    // String tyyppisen bittijonon tallentaminen ja lukeminen tiedostosta

    // Jos (bitString.length() + 7) / 8 > 2 147 483 647 (suurin int) niin bytes täytyy
    // muuttaa ArrayList. Taulukon koko voi olla maksimissaan edellä mainittu luku.
    // Vajaat tavut (% 8 != 0) täytetään 0-biteillä loppuun. Esim. 11 => 11000000.
    // Tavun bittien indeksöinti on päinvastainen kuin normaalisti. Ensimmäinen bitti
    // vasemmalta vastaa indeksin arvoa 7. Viimeinen on 0.
    // Lisää taulukkoon 1 lisäpaikka. Sen tarkoitus selviää alla.
    byte[] bytes = new byte[(bitString.length() + 7) / 8 + 1];
    for (int i = 0; i < bitString.length(); i++) {
      if (bitString.charAt(i) == '1') {
        // Manipuloi kohdassa bytes[i / 8] olevaa 00000000 tavua. Operaatiot selitetty alla.
        bytes[i / 8] |= 1 << (7 - (i % 8));
      }
    }

    // Lisää taulukon bytes viimeiseksi tavuksi tavu, joka ilmaisee viimeisen muutetun
    // tavun, viimeiseksi muutetun bitin indeksiä. Eli sen lukuarvo on jotain väliltä 0 - 7.
    bytes[bytes.length - 1] = (byte)(7 - ((bitString.length() - 1) % 8));

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

    // Tiedoston koko
    File f = new File("result.bin");
    if (f.exists() && f.isFile()) {
    System.out.println("Tiedoston result.bin koko tavuina: " + f.length());
    }

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

    // Ota viimeinen tiedostosta luettu tavu (ilmaisee viimeisenä muutetun bitin indeksiä)
    int lastByte = bytesFromFile.get(bytesFromFile.size() - 1);

    // Rakenna String, johon tulee kaikkien tavujen bittijonoesitykset
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytesFromFile.size(); i++) {
      sb.append(Integer.toBinaryString((bytesFromFile.get(i) & 0xFF) + 0x100).substring(1));
    }

    // Muuta StringBuilder Stringiksi
    String string = sb.toString();

    // Poista viimeinen tavu ja toiseksi viimeisen tavun ylimäräiset bitit
    String finalBitString = string.substring(0, string.length() - (8 + lastByte));

    return finalBitString;
  }
}