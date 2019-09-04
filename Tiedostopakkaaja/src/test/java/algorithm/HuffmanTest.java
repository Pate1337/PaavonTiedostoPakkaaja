package algorithm;

import algorithm.Huffman;
import algorithm.BitStringTree;
import algorithm.Node;
import utilities.FileHandler;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.util.Random;

public class HuffmanTest {
  static String[] characterStrings;
  static String[] bitStrings;
  static List<HashMap<Character, Integer>> frequencies;
  static String runEfficiencyTests;

  @BeforeClass
  public static void setUp() throws Exception {

    runEfficiencyTests = System.getProperty("efficiency");

    // Kaikki arvot mitä täällä asetetaan, on testitapauksia.
    // Ole varovainen kun muutat näitä arvoja.

    int testCasesLength = 4;
    characterStrings = new String[testCasesLength];
    characterStrings[0] = "a";
    characterStrings[1] = "bbb";
    characterStrings[2] = "cddcca";
    characterStrings[3] = "aaaaaaaaaaaaaaabbbbbbbccccccddddddeeeee";

    bitStrings = new String[testCasesLength];
    bitStrings[0] = "000000000000000000000000011000010";
    bitStrings[1] = "00000000000000000000000001100010000";
    bitStrings[2] = "0000000000000101" + "0000000000110000" + "11000"
      + "0000000001100100" + "0000000001100001" + "0000000001100011"
      + "100001101";
    bitStrings[3] = "0000000000001001" + "0000000001010000" + "111001000"
      + "0000000001100010" + "0000000001100011" + "0000000001100100"
      + "0000000001100101" + "0000000001100001"
      + "111111111111111000000000000000000000001001001001001001010010010010010010011011011011011";

    frequencies = new ArrayList<>();

    frequencies.add(new HashMap<>());
    frequencies.get(0).put('a', 1);

    frequencies.add(new HashMap<>());
    frequencies.get(1).put('b', 3);

    frequencies.add(new HashMap<>());
    frequencies.get(2).put('a', 1);
    frequencies.get(2).put('d', 2);
    frequencies.get(2).put('c', 3);

    frequencies.add(new HashMap<>());
    frequencies.get(3).put('a', 15);
    frequencies.get(3).put('b', 7);
    frequencies.get(3).put('c', 6);
    frequencies.get(3).put('d', 6);
    frequencies.get(3).put('e', 5);
  }

  @Test
  public void frequenciesAreCountedCorrectlyToHashMap() {

    assertTrue(Huffman.createFrequenciesHashMap(characterStrings[0]).equals(frequencies.get(0)));

    assertTrue(Huffman.createFrequenciesHashMap(characterStrings[1]).equals(frequencies.get(1)));

    assertTrue(Huffman.createFrequenciesHashMap(characterStrings[2]).equals(frequencies.get(2)));

    assertTrue(Huffman.createFrequenciesHashMap(characterStrings[3]).equals(frequencies.get(3)));
  }

  @Test
  public void oneCharacterCasesAreHandledCorrectlyOnEncoding() {

    assertEquals("000000000000000000000000011000010", Huffman.handleOneCharacterCaseOnEncode(characterStrings[0], 'a'));

    assertEquals("00000000000000000000000001100010000", Huffman.handleOneCharacterCaseOnEncode(characterStrings[1], 'b'));
  }

  @Test
  public void buildsHuffmanTreeCorrectly() throws Exception {

    Node root1 = Huffman.buildHuffmanTree(frequencies.get(3));

    assertEquals('\0', root1.character);
    assertEquals(39, root1.frequency);
    assertEquals('\0', root1.left.character);
    assertEquals(24, root1.left.frequency);
    assertEquals('\0', root1.left.left.character);
    assertEquals(13, root1.left.left.frequency);
    assertEquals('b', root1.left.left.left.character);
    assertEquals(7, root1.left.left.left.frequency);
    assertEquals(null, root1.left.left.left.left);
    assertEquals(null, root1.left.left.left.right);
    assertEquals('c', root1.left.left.right.character);
    assertEquals(6, root1.left.left.right.frequency);
    assertEquals(null, root1.left.left.right.left);
    assertEquals(null, root1.left.left.right.right);
    assertEquals('\0', root1.left.right.character);
    assertEquals(11, root1.left.right.frequency);
    assertEquals('d', root1.left.right.left.character);
    assertEquals(6, root1.left.right.left.frequency);
    assertEquals(null, root1.left.right.left.left);
    assertEquals(null, root1.left.right.left.right);
    assertEquals('e', root1.left.right.right.character);
    assertEquals(5, root1.left.right.right.frequency);
    assertEquals(null, root1.left.right.right.left);
    assertEquals(null, root1.left.right.right.right);
    assertEquals('a', root1.right.character);
    assertEquals(15, root1.right.frequency);
    assertEquals(null, root1.right.left);
    assertEquals(null, root1.right.right);

    Node root2 = Huffman.buildHuffmanTree(frequencies.get(2));

    assertEquals('\0', root2.character);
    assertEquals(6, root2.frequency);
    assertEquals('\0', root2.left.character);
    assertEquals(3, root2.left.frequency);
    assertEquals('d', root2.left.left.character);
    assertEquals(2, root2.left.left.frequency);
    assertEquals(null, root2.left.left.left);
    assertEquals(null, root2.left.left.right);
    assertEquals('a', root2.left.right.character);
    assertEquals(1, root2.left.right.frequency);
    assertEquals(null, root2.left.right.left);
    assertEquals(null, root2.left.right.right);
    assertEquals('c', root2.right.character);
    assertEquals(3, root2.right.frequency);
    assertEquals(null, root2.right.left);
    assertEquals(null, root2.right.right);
  }

  @Test
  public void createsCorrectHuffmanCodesAndBuildsBitStringTree() {
    BitStringTree tree = new BitStringTree();
    Node root = Huffman.buildHuffmanTree(frequencies.get(3));
    HashMap<Character, String> expected = new HashMap<>();
    expected.put('a', "1");
    expected.put('b', "000");
    expected.put('c', "001");
    expected.put('d', "010");
    expected.put('e', "011");
    Map<Character, String> huffmanCode = Huffman.createHuffmanCodes(root, tree);

    assertTrue(huffmanCode.equals(expected));
    assertEquals("111001000", tree.tree.toString());
    assertEquals("bcdea", tree.characters.toString());

    tree = new BitStringTree();
    root = Huffman.buildHuffmanTree(frequencies.get(2));
    expected = new HashMap<>();
    expected.put('a', "01");
    expected.put('d', "00");
    expected.put('c', "1");
    huffmanCode = Huffman.createHuffmanCodes(root, tree);

    assertTrue(huffmanCode.equals(expected));
    assertEquals("11000", tree.tree.toString());
    assertEquals("dac", tree.characters.toString());
  }

  @Test
  public void createsBitStringOfCharactersInTheGivenParameter() {
    // Each is 16 bits
    String param = "abc123!?€";
    String expected = "0000000001100001" + "0000000001100010" + "0000000001100011"
      + "0000000000110001" + "0000000000110010" + "0000000000110011"
      + "0000000000100001" + "0000000000111111" + "0010000010101100";
    String result = Huffman.createBitStringOfCharacters(param);
    assertEquals(expected, result);
  }

  @Test
  public void fillBeginningWithZeroesAddsZeroesSoThatThereAre16BitsTotal() throws Exception {
    // Tällä tavalla saa testattua private metodeja
    Huffman huffman = new Huffman();
    Method method = Huffman.class.getDeclaredMethod("fillBeginningWithZeroes", String.class);
    method.setAccessible(true);

    String input = "11";
    String output = (String)method.invoke(huffman, input);

    assertEquals("0000000000000011", output);

    input = "000";
    output = (String)method.invoke(huffman, input);
    
    assertEquals("0000000000000000", output);

    input = "1010101010101010";
    output = (String)method.invoke(huffman, input);
    
    assertEquals("1010101010101010", output);
  }

  @Test
  public void oneCharacterCasesAreHandledCorrectlyOnDecoding() throws Exception {
    Huffman huffman = new Huffman();
    Method method = Huffman.class.getDeclaredMethod("handleOneCharacterCaseOnDecode", String.class);
    method.setAccessible(true);

    String output1 = (String)method.invoke(huffman, bitStrings[0]);

    assertEquals(characterStrings[0], output1);

    String output2 = (String)method.invoke(huffman, bitStrings[1]);

    assertEquals(characterStrings[1], output2);
  }

  @Test
  public void encodesTheGivenStringCorrectly() {

    String result1 = Huffman.encodeTextToBitString(characterStrings[0]);

    assertEquals(bitStrings[0], result1);

    String result2 = Huffman.encodeTextToBitString(characterStrings[1]);

    assertEquals(bitStrings[1], result2);

    String result3 = Huffman.encodeTextToBitString(characterStrings[2]);

    assertEquals(bitStrings[2], result3);

    String result4 = Huffman.encodeTextToBitString(characterStrings[3]);

    assertEquals(bitStrings[3], result4);
  }

  @Test
  public void decodesTheGivenStringCorrectly() {

    String result1 = Huffman.decodeBitStringToText(bitStrings[0]);

    assertEquals(characterStrings[0], result1);

    String result2 = Huffman.decodeBitStringToText(bitStrings[1]);

    assertEquals(characterStrings[1], result2);

    String result3 = Huffman.decodeBitStringToText(bitStrings[2]);

    assertEquals(characterStrings[2], result3);

    String result4 = Huffman.decodeBitStringToText(bitStrings[3]);

    assertEquals(characterStrings[3], result4);
  }

  private String generateRandomString(int size, int amountOfCharacters) {
    StringBuilder generatedString = new StringBuilder();
    Random random = new Random();

    for (int i = 0; i < size; i++) {
      generatedString.append((char)(random.nextInt(amountOfCharacters) + 'a'));
    }
    return generatedString.toString();
  }

  private long timeItTakesToEncodeString(String text) {
    long start = System.currentTimeMillis();

    Huffman.encodeTextToBitString(text);

    long end = System.currentTimeMillis();

    return (end - start);
  }

  private void averageTimeItTakesToEncodeString(int textLength, int amountOfCharacters) {

    String text = generateRandomString(textLength, amountOfCharacters);

    long sum = 0;
    int times = 50;

    for (int i = 0; i < times; i++) {
      sum += timeItTakesToEncodeString(text);
    }
    
    System.out.println("length: " + textLength + ", number of characters: " + amountOfCharacters + ", time: " + (sum / times) + "\n");
  }

  @Test
  public void runtimesOfEncoding() {

    if (runEfficiencyTests.equals("true")) {
      System.out.println("\nTIME IT TAKES TO ENCODE TEXT\n");

      averageTimeItTakesToEncodeString(100000, 2);

      averageTimeItTakesToEncodeString(1000000, 2);

      averageTimeItTakesToEncodeString(2000000, 2);

      averageTimeItTakesToEncodeString(4000000, 2);

      averageTimeItTakesToEncodeString(100000, 20);

      averageTimeItTakesToEncodeString(1000000, 20);

      averageTimeItTakesToEncodeString(2000000, 20);

      averageTimeItTakesToEncodeString(4000000, 20);

      averageTimeItTakesToEncodeString(100000, 100);

      averageTimeItTakesToEncodeString(1000000, 100);

      averageTimeItTakesToEncodeString(2000000, 100);

      averageTimeItTakesToEncodeString(4000000, 100);
    }
  }
}