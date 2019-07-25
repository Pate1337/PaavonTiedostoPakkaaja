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

class Main {
  public static void main(String[] args) {
    // Manipuloi ensin tavun bittejä ja tallenna jokainen tavu tiedostoon käyttäen
    // BufferedOutputStreamia
    BitSet buffer = new BitSet();
    // BitSet sets the size() to 64
    int bitIndex = 0;
    char[] bits = new char[8];
    bits[0] = '0';
    bits[1] = '1';
    bits[2] = '0';
    bits[3] = '0';
    bits[4] = '1';
    bits[5] = '1';
    bits[6] = '0';
    bits[7] = '0';
    // 01001100 is 76 in decimals
    int k = 25;
    System.out.println("int value 2 converted to byte is: " + (byte)k);
    System.out.println(bits.length);
    for (int i = 0; i < bits.length; i++) {
      buffer.set(bitIndex++, bits[i] == '1');
    }
    byte[] b = buffer.toByteArray();
    // int value = b[0];
    // System.out.println("int value of byte is: " + value);
    System.out.println("byte: " + b);
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
    System.out.println("byteArray.length: " + buffer.toByteArray().length);

    // Integer integerObject = new Integer("23");


    // ------------ THIS WAS IMPORTANT --------------
    String s = "01111000"; // 120
    int value = Integer.parseInt(s, 2);
    System.out.println("Integer value of 00010111 in binary: " + value);
    byte g = Byte.parseByte(s, 2);
    Byte h = new Byte("120");
    byte v = (byte) h.byteValue();
    System.out.println("byte value of 00101101 in binary: " + v);
    // byte lowBits = v&0x3; // 2 lowest bits
    int lowBits = v&0x3; // 2 lowest bits
    System.out.println("lowbits of 00101101: " + lowBits);
    v |= 0xf; // set the low 4 bits to 1
    System.out.println("byte value of 00101101 in binary: " + v);
    // -----------------------------------------------


    // ObjectOutputStream outputStream = null;
    // The writing
    /*try {
      ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("result.bin"));
      os.writeChar('p');
      os.writeChar('a');
      os.writeChar('s');
      os.writeChar('k');
      os.writeChar('a');
      os.close();
      // OutputStream outputStream = new FileOutputStream("result.bin");
      // outputStream = new ObjectOutputStream(new FileOutputStream("result.out"));
      // outputStream.writeObject(buffer);
      // outputStream.write(buffer.toByteArray());
      // outputStream.close();
      // System.out.println(buffer.toByteArray().length);
    } catch (Exception e) {
      System.out.println("Error occurred");
      e.printStackTrace();
    }

    // Reading
    char[] chars = new char[5];
    try {
      ObjectInputStream is = new ObjectInputStream(new FileInputStream("result.bin"));
      chars[0] = is.readChar();
      chars[1] = is.readChar();
      chars[2] = is.readChar();
      chars[3] = is.readChar();
      chars[4] = is.readChar();
      is.close();
      // InputStream inputStream = new BufferedInputStream(new FileInputStream("result.bin"));
      // FileInputStream fileInputStream = new FileInputStream("result.bin");

      // int singleCharInt;
      // char singleChar;

      // while ((singleCharInt = fileInputStream.read()) != -1) {
      //  singleChar = (char) singleCharInt;
      //  System.out.print(singleChar);
      //}
      // fileInputStream.close();
    } catch (Exception e) {
      System.out.println("Error reading file");
    }
    for (int i = 0; i < chars.length; i++) {
      System.out.print(chars[i]);
    }*/
    // System.out.println("input: " + input);
      // return buffer.toByteArray();
    /*try {
      File file = new File("compressed.txt");
      if (file.exists()) {
        double bytes = file.length();
        System.out.println("File size in bytes: " + bytes);
      } else {
        System.out.println("File does not exist");
      }
    } catch (Exception e) {
      System.out.println("Error");
    }*/
  }
}