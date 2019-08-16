package utilities;

import java.util.ArrayList;

public class Converter {

  /* 
    Bitwise operations:

    00000001 << 3 = 00001000 
    1 << 3 == 8 (Shift bit 1 to index 3)

    1010 | 0101 == 1111 (OR)
  */
  
  public static byte[] stringToByteArray(String bitString) {
    // Jos (bitString.length() + 7) / 8 > 2 147 483 647 (suurin int) niin bytes täytyy
    // muuttaa ArrayList. Taulukon koko voi olla maksimissaan edellä mainittu luku.
    // Jos viimeisen tavun pituus ei ole 8 bittiä, sen loppuun lisätään tarvittavat 0 bitit.
    // Esim. 11 => 11000000.
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
    return bytes;
  }

  public static String bytesToString(ArrayList<Byte> bytes) {
    // Rakenna String, johon tulee kaikkien tavujen bittijonoesitykset
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.size(); i++) {
      sb.append(Integer.toBinaryString((bytes.get(i) & 0xFF) + 0x100).substring(1));
    }

    // Muuta StringBuilder Stringiksi
    String string = sb.toString();

    // Ota viimeinen tavu (ilmaisee viimeisenä muutetun bitin indeksiä)
    int lastByte = bytes.get(bytes.size() - 1);

    // Poista viimeinen tavu ja toiseksi viimeisen tavun ylimäräiset bitit
    String finalBitString = string.substring(0, string.length() - (8 + lastByte));
    
    return finalBitString;
  }
}