import java.io.File;
import java.util.BitSet;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) {

    /*
      Weird things:

      byte b = 255; // Gives a 'possible lossy conversion from int to byte' error

      BUT, when
      BitSet set = new BitSet();
      String s = '11111111' (= 255 in decimal)
      And set the bits accordinly to string to the BitSet.
      Then
      byte[] array = set.toByteArray(); // No error...Why the fuck
    */
    // BitSet is the way to go. This way all the bits can be useful unlike when setting them
    // first to byte or int.
    // BitSet buffer = new BitSet();
    String string = "11111111000100001110110101010010101100000000000011111010001000111101001101010101000010100010100000111111111000101"; // 241

    /*int bitIndex = 0;
    for (int i = string.length() - 1; i > 0; i--) {
      buffer.set(bitIndex, string.charAt(i) == '1');
      bitIndex++;
    }*/

    /*int bitIndex = 0;
    for (int i = 0; i < string.length(); i++) {
      buffer.set(bitIndex, string.charAt(i) == '1');
      bitIndex++;
    }*/

    // The least significant bit is set last (BitSet index 0)
    /*int bitIndex = string.length() - 1;
    for (int i = 0; i < string.length(); i++) {
      buffer.set(bitIndex, string.charAt(i) == '1');
      bitIndex--;
    }*/


    // Convert to byteArray urself (because BitSet's toByteArray() leaves out the 0 bytes)
    /*byte[] array = new byte[(string.length() + 7) / 8];
    for (int i = 0; i < buffer.length(); i++) {
      if (buffer.get(i)) {
        array[array.length - i / 8 - 1] |= 1 << (i % 8);
      } else {
        array[array.length - i / 8 - 1] |= 0 << (i % 8);
      }
    }*/
    /* 
      00000001 << 3 = 00001000 
      1 << 3 == 8 (Shift bit 1 to index 3)

      1010 | 0101 == 1111 (OR)
    */
    byte[] array = new byte[(string.length() + 7) / 8];
    for (int i = 0; i < string.length(); i++) {
      System.out.println("Setting to index: " + (i / 8));
      if (string.charAt(i) == '1') {
        // array[array.length - i / 8 - 1] |= 1 << (i % 8);
        array[i / 8] |= 1 << (7 - (i % 8));
      } /*else {
        array[i / 8] |= 0 << (7 - (i % 8));
      }*/
    }

    /*System.out.println("Bitset size(): " + buffer.size());
    System.out.println("Bitset toString(): " + buffer.toString());
    System.out.println("Bitset length(): " + buffer.length());*/
    // byte[] array = buffer.toByteArray();
    System.out.println("array.length: " + array.length);
    for (int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
      // System.out.println(Integer.toBinaryString(array[i]));
      System.out.println(Integer.toBinaryString((array[i] & 0xFF) + 0x100).substring(1));
    }
    /*long p = 0L;
    for (int i = 0; i < buffer.length(); ++i) {
      p += buffer.get(i) ? (1L << i) : 0L;
    }
    System.out.println("BitSet in long: " + p);
    byte[] b = buffer.toByteArray();
    // int value = b[0];
    // System.out.println("int value of byte is: " + value);
    System.out.println("byte: " + b[0]);
    System.out.println("byteArray: " + buffer.toByteArray());
    System.out.println("buffer.size(): " + buffer.size());
    System.out.println("buffer.length(): " + buffer.length());
    System.out.println("buffer.toString(): " + buffer.toString());
    String content = "a";
    System.out.println("a in bytes: " + content.getBytes());
    for (int i = 0; i < bits.length; i++) {
      System.out.println("value of bit in " + i + ": " + buffer.get(i));
    }
    // toByteArray() type is byte[]
    System.out.println("byteArray.length: " + buffer.toByteArray().length);*/

    // Integer integerObject = new Integer("23");


    // ------------ THIS WAS IMPORTANT --------------
    /*String s = "01111000"; // 120
    int value = Integer.parseInt(s, 2);
    System.out.println("Integer value of 00010111 in binary: " + value);
    byte g = Byte.parseByte(s, 2);
    Byte h = new Byte("120");
    byte v = (byte) h.byteValue();
    System.out.println("byte value of 00101101 in binary: " + v);
    // byte lowBits = v&0x3; // 2 lowest bits
    int lowBits = v&0x3; // 2 lowest bits
    System.out.println("lowbits of 00101101: " + lowBits);
    v |= 0xf; // set the low 4 bits to 1 (same as v = v | 0xf)
    System.out.println("byte value of 00101101 in binary: " + v);

    byte uus = 0x78; // Decimal: 120, binary: 01111000
    uus |= 0x2; // Does 01111000 | 00000010 (OR) = 01111010, decimal: 122
    System.out.println("uus: " + uus); // 122

    int testi = Integer.parseInt("1111000011110000111100001111000", 2);
    byte iso = 112;
    System.out.println(iso);
    // testi |= 0x80; // changes first bit to 1
    // long intti = 4294967295;
    System.out.println(testi);
    // System.out.println(Integer.toBinaryString(intti));
    System.out.println(Integer.toBinaryString(testi));
    System.out.println(Integer.toBinaryString((testi & 0xFF) + 0x100).substring(1));*/
    // String tyyppisen bittijonoesityksen tallentaminen ja lukeminen tiedostosta
/*
    String a = "011110000100110001"; // 120 and 76

    // There is a problem now with the string representation of binary:
    // In byte for example, the first bit is reserved for the -.
    // So bytes that start with 1 and contain 8 bits are not allowed, because they are too big.
    // Same with int, which is 32 bits. The least bits wasted would be with int.
    // Use int and take chunks of 31 characters long from the string representation.
    // Remember to handle the 32nd bits always (just dismiss them). They are added by default

    // Luo riittävän pitkä byte array a.length() / 8
    int l = a.length() / 8;
    if (a.length() % 8 != 0) {
      // Need to add one more spot to the array for the remaining bits
      l++;
    }
    byte[] bytes = new byte[l];
    int start = 0;
    int end = 8;
    int index = 0;
    while (end <= a.length()) {
      // Lisää byte byte arrayhin
      System.out.println("Adding byte to array: " + a.substring(start, end));
      bytes[index] = Byte.parseByte(a.substring(start, end), 2);
      start += 8;
      end += 8;
      index++;
    }

    // The remaining bits
    String remaining = a.substring(start, a.length());
    StringBuilder build = new StringBuilder();
    build.append(remaining);
    System.out.println("remaining: " + remaining);
    // This does not work if first remaining bit is 1
    if (!remaining.isEmpty()) {
      System.out.println("Remaining is an empty string");
      // Just for starters, fill the rest of the byte to 0's
      for (int i = 0; i < 8 - remaining.length(); i++) {
        build.append("0");
      }
      bytes[bytes.length - 1] = Byte.parseByte(build.toString(), 2);
    }
    // Do something to the remaining bits (There is already room in the array for them)

    // 1. Muunna string byte
    // byte b = Byte.parseByte(a, 2);

    // Lisää byte arrayhin

    // bytes[0] = b;

    // 2. Tallenna esim tiedostoon
    */
    try {
      DataOutputStream os = new DataOutputStream(new FileOutputStream("result.bin"));
      for (int i = 0; i < array.length; i++) {
        os.writeByte(array[i]);
        System.out.println("Writing byte: " + Integer.toBinaryString((array[i] & 0xFF) + 0x100).substring(1));
      }
      os.close();
    } catch (Exception e) {
      System.out.println("Error writing to file");
    }

    // Tiedoston koko

    File f = new File("result.bin");
    if (f.exists() && f.isFile()) {
      System.out.println("Size of result.bin in bytes: " + f.length());
    }

    // 3. Lue tiedostosta byte getByte()
    // byte readByte = 0;

    // byte[] readBytes = new byte[f.length()];
    ArrayList<Byte> list = new ArrayList<>();
    try {
      DataInputStream is = new DataInputStream(new FileInputStream("result.bin"));
      int o = 0;
      while (is.available() > 0) {
        // readByte = is.readByte();
        // System.out.println("Reading byte: " + Integer.toBinaryString((readByte & 0xFF) + 0x100).substring(1));
        // readBytes[o] = is.readByte();
        list.add(is.readByte());
        o++;
      }
      // Remember to handle the last byte somewhere if it's made of remainders
      is.close();
    } catch (Exception e) {
      System.out.println("Error reading file");
    }

    for (int i = 0; i < list.size(); i++) {
      // System.out.println(readBytes[i]);
      // System.out.println(Integer.toBinaryString(array[i]));
      System.out.println(Integer.toBinaryString((list.get(i) & 0xFF) + 0x100).substring(1));
    }

    // 4. Käännä byte takaisin stringiki, joka esittää bittijonoa
    /*
    // String s1 = String.format("%8s", Integer.toBinaryString(readByte & 0xFF)).replace(' ', '0');
    // https://stackoverflow.com/questions/12310017/how-to-convert-a-byte-to-its-binary-string-representation
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < readBytes.length; i++) {
      String s1 = Integer.toBinaryString((readBytes[i] & 0xFF) + 0x100).substring(1);
      sb.append(s1);
    }
    System.out.println("Bittijono: " + sb.toString());*/
    // -----------------------------------------------
  }
}