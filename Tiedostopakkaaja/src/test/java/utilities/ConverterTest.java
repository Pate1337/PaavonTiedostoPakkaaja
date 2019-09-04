package utilities;

import utilities.Converter;

import org.junit.*;
import static org.junit.Assert.*;

public class ConverterTest {

  @Test
  public void stringToByteArrayAddsAnExtraByteToTheEndThatTellsTheIndexOfTheLastChangedBit() {
    String input = "00000001";

    byte[] output = Converter.stringToByteArray(input);

    assertEquals(2, output.length);

    assertEquals(0, output[1]);

    input = "0000001";

    output = Converter.stringToByteArray(input);

    assertEquals(2, output.length);

    assertEquals(1, output[1]);

    input = "00010100001";

    output = Converter.stringToByteArray(input);

    assertEquals(3, output.length);

    assertEquals(5, output[output.length - 1]);
  }

  @Test
  public void convertsBitStringToByteArrayCorrectly() {
    String input = "11111111";

    byte[] output = Converter.stringToByteArray(input);

    assertEquals(-1, output[0]);

    input = "00111000" + "01010001" + "00001000" + "01011010" + "0";

    output = Converter.stringToByteArray(input);

    assertEquals(56, output[0]);
    assertEquals(81, output[1]);
    assertEquals(8, output[2]);
    assertEquals(90, output[3]);
    assertEquals(0, output[4]);
  }
}