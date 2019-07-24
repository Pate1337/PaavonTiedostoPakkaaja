import java.io.File;
import java.util.BitSet;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;

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
    System.out.println(bits.length);
    for (int i = 0; i < bits.length; i++) {
      buffer.set(bitIndex++, bits[i] == '1');
    }
    System.out.println("byteArray: " + buffer.toByteArray());
    System.out.println("buffer.size(): " + buffer.size());
    System.out.println("buffer.length(): " + buffer.length());
    System.out.println("buffer.toString(): " + buffer.toString());
    String content = "a";
    System.out.println("a in bytes: " + content.getBytes());
    for (int i = 0; i < bits.length; i++) {
      System.out.println("value of bit in " + i + ": " + buffer.get(i));
    }
    // ObjectOutputStream outputStream = null;
    /*try {
      OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("result.bin"));
      // outputStream = new ObjectOutputStream(new FileOutputStream("result.out"));
      // outputStream.writeObject(buffer);
      outputStream.write(buffer.toByteArray(), 0);
      // System.out.println(buffer.toByteArray().length);
    } catch (Exception e) {
      System.out.println("Error occurred");
      e.printStackTrace();
    }*/
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