package algorithm;

import algorithm.Huffman;
import algorithm.BitStringTree;
import algorithm.Node;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class HuffmanTest {
  String[] characterStrings;
  String[] bitStrings;
  List<HashMap<Character, Integer>> frequencies;

  @Before
  public void setUp() {

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
    bitStrings[2] = "0000000000000101" + "0000000000110000" + "10100"
      + "0000000001100011" + "0000000001100100" + "0000000001100001"
      + "010100011";
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
  public void buildsHuffmanTreeCorrectly() {

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
  public void encodesTheGivenStringCorrectly() {

    String result1 = Huffman.encodeTextToBitString(characterStrings[0]);

    assertEquals(bitStrings[0], result1);

    String result2 = Huffman.encodeTextToBitString(characterStrings[1]);

    assertEquals(bitStrings[1], result2);

    String result4 = Huffman.encodeTextToBitString(characterStrings[3]);

    assertEquals(bitStrings[3], result4);
  }
}