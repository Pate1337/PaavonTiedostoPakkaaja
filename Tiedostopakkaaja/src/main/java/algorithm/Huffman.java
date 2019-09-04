package algorithm;

import algorithm.Node;
import algorithm.State;
import algorithm.BitStringTree;
import datastructures.NodePriorityQueue;

import java.util.Map;
import java.util.HashMap;

/** Contains all the functionality for encoding and decoding text using Huffman coding
 */
public class Huffman {

	private static void encode(Node root, String str, Map<Character, String> huffmanCode, BitStringTree tree) {
		if (root == null)
			return;

		if (root.left == null && root.right == null) {
			tree.tree.append("0");
			tree.characters.append(root.character);
			huffmanCode.put(root.character, str);
		} else {
			tree.tree.append("1");
		}

		encode(root.left, str + "0", huffmanCode, tree);
		encode(root.right, str + "1", huffmanCode, tree);
	}

	private static int decode(Node root, int index, String string, StringBuilder sb) {
		if (root == null)
			return index;

		// found a leaf node
		if (root.left == null && root.right == null) {
      sb.append(root.character);
			return index;
		}

		index++;

		if (string.charAt(index) == '0')
			index = decode(root.left, index, string, sb);
		else
			index = decode(root.right, index, string, sb);

		return index;
	}

	/** Builds Huffman tree and returns the root.
	* @param freq Map of characters and their frequencies.
	* @return The root node of the Huffman tree.
	*/
	public static Node buildHuffmanTree(Map<Character, Integer> freq) {
		NodePriorityQueue pq = new NodePriorityQueue();

		// Luo lehtisolmut jokaisesta merkistä ja lisää ne prioriteettijonoon
		for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
			pq.insert(new Node(entry.getKey(), entry.getValue()));
		}

		while (pq.size() != 1) {
			Node right = pq.poll();
			Node left = pq.poll();

			int sum = left.frequency + right.frequency;
			pq.insert(new Node('\0', sum, left, right));
		}
		return pq.peek();
  }

	/** Creates a HashMap containing the characters and their frequencies.
	* @param text String from which the HashMap is created.
	* @return The created HashMap.
	*/
  public static Map<Character, Integer> createFrequenciesHashMap(String text) {
		Map<Character, Integer> freq = new HashMap<>();
    for (int i = 0 ; i < text.length(); i++) {
			if (!freq.containsKey(text.charAt(i))) {
				freq.put(text.charAt(i), 0);
			}
			freq.put(text.charAt(i), freq.get(text.charAt(i)) + 1);
		}
		return freq;
	}

	private static String fillBeginningWithZeroes(String string) {
		String zeroes = "";
		for (int i = 0; i < 16 - string.length(); i++) {
			zeroes += "0";
		}
		return zeroes + string;
	}

	/** Builds the Huffman tree of the given bitstring.
	* @param bits The bitstring representation of the Huffman tree.
	* @param state A State object, that is needed when going through the tree.
	* @param chars Char array that contains the corresponding characters of the trees leaf nodes.
	* @return The root node of the Huffman tree.
	* @see State
	*/
	public static Node buildTreeFromBits(String bits, State state, char[] chars) {
		Node root = null;
		char nextBit = bits.charAt(state.bitIndex);
		state.bitIndex++;
		if (nextBit == '0') {
			// Lehtisolmu
			// Laita frequency 0, sitä ei käytetä dekoodauksessa kuitenkaan.
			root = new Node(chars[state.charIndex], 0);
			state.charIndex++;
		} else {
			// Sisäsolmu
			Node left = buildTreeFromBits(bits, state, chars);
			Node right = buildTreeFromBits(bits, state, chars);
			root = new Node('\0', 0, left, right);
		}
		return root;
	}

	/** Handles the cases, where the file to encode only consists of 1 character.
	* Will check the amount of the single character in the given text and create a bitstring
	* that has as many 0's as there were characters in the text.
	* @param text The text string to encode.
	* @param onlyCharacter The only character in the text string.
	* @return The encoded string that can be saved to a binary file as is.
	*/
	public static String handleOneCharacterCaseOnEncode(String text, char onlyCharacter) {
		String toReturn = "";
		// Ensimmäiset 16 bittiä 0, ilmaisevat puun pituuden
		toReturn += "0000000000000000";
		
		// Seuraavat bitit ilmaisevat ainoan merkin bittijonoesityksen.
		toReturn += fillBeginningWithZeroes(Integer.toBinaryString(onlyCharacter));
		// Loput merkit ovat merkkien määrän verran 0 bittejä
		for (int i = 0; i < text.length(); i++) {
			toReturn += "0";
		}
		return toReturn;
	}

	private static String handleOneCharacterCaseOnDecode(String bitString) {
		// Seuraavat 16 bittiä kuvaavat tiedoston ainoaa merkkiä.
		// Loput bitit ovat merkkien määrän verran 0 bittejä.
		char onlyChar = (char)Integer.parseInt(bitString.substring(16, 32), 2);
		String endBits = bitString.substring(32);
		String toReturn = "";
		for (int i = 0; i < endBits.length(); i++) {
			toReturn += onlyChar;
		}
		return toReturn;
	}

	/** Creates the Huffman codes for each leaf node in the Huffman tree.
	* @param root The root node of the Huffman tree.
	* @param tree A BitStringTree object. Will be built at the same time.
	* @return A HashMap containing the characters and their Huffman codes.
	* @see BitStringTree
	*/
	public static Map<Character, String> createHuffmanCodes(Node root, BitStringTree tree) {
		Map<Character, String> huffmanCode = new HashMap<>();
    encode(root, "", huffmanCode, tree);

		return huffmanCode;
	}

	/** Creates a bitstring representation of the given text.
	* Each character is transformed to 16 bit binary form.
	* @param text The text which needs to be represented as binary.
	* @return A bitstring, where every character takes 16 bits.
	*/
	public static String createBitStringOfCharacters (String text) {
		StringBuilder charsInBits = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			String charInBits = fillBeginningWithZeroes(Integer.toBinaryString(text.charAt(i)));
			charsInBits.append(charInBits);
		}
		return charsInBits.toString();
	}

	/** Encodes the given string using Huffman coding.
	* @param text The original string.
	* @return String The encoded form of the original string.
	*/
  public static String encodeTextToBitString (String text) {

		// Luo HashMap freq, joka sisältää merkit ja niiden esiintymistiheydet.
    Map<Character, Integer> freq = createFrequenciesHashMap(text);

    // Erikoistapaus, jossa merkkijono koostuu vain yhdestä merkistä, esim. "aaaaaaa"
    if (freq.size() == 1) {
			Map.Entry<Character, Integer> entry = freq.entrySet().iterator().next();
 			char onlyCharacter = entry.getKey();
      return handleOneCharacterCaseOnEncode(text, onlyCharacter);
    }

    // Luo Huffman puu
		Node root = buildHuffmanTree(freq);

		// Luo Huffman koodit jokaiselle merkille
		BitStringTree bitStringTree = new BitStringTree();
		Map<Character, String> huffmanCode = createHuffmanCodes(root, bitStringTree);
		StringBuilder characters = bitStringTree.characters;
		StringBuilder tree = bitStringTree.tree;

		// Käy läpi lehtisolmujen merkit ja luo jokaisesta bittijonot, joiden pituus on 16 bittiä (2 tavua)
		// 16 bittiä varattu merkeille, koska esim. merkkiä € ei voi ilmaista 8 bitin avulla.
		String charsInBits = createBitStringOfCharacters(characters.toString());

		// Luo puun pituutta esittävä bittijono (16 bittiä)
		int treeLength = tree.toString().length();
		String treeLengthInBits = fillBeginningWithZeroes(Integer.toBinaryString(treeLength));

		// Luo mekkejä esittävän bittijonon pituuden esittävä bittijono (16 bittiä)
		int charsLength = charsInBits.length();
		String charsLengthInBits = fillBeginningWithZeroes(Integer.toBinaryString(charsLength));

		// Luo kokonainen bittijono String
		StringBuilder finalBitString = new StringBuilder();
		finalBitString.append(treeLengthInBits);
		finalBitString.append(charsLengthInBits);
		finalBitString.append(tree.toString());
		finalBitString.append(charsInBits);
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < text.length(); i++) {
			sb.append(huffmanCode.get(text.charAt(i)));
		}
		finalBitString.append(sb.toString());

		return finalBitString.toString();
  }

	/** Decodes the bitstring to it's original form.
	* @param bitString The encoded text. Needs to be a result of encodeTextToBitString.
	* @return String The decoded form of the bitstring given as parameter.
	*/
  public static String decodeBitStringToText (String bitString) {

		// Ensimmäiset 2 tavua ovat puun pituus.
		int treeLength = Integer.parseInt(bitString.substring(0, 16), 2);

		// Erikoistapaus. Tällöin alkuperäinen tiedosto sisältää vain yhtä merkkiä, esim. "aaaaa"
		if (treeLength == 0) {
			return handleOneCharacterCaseOnDecode(bitString);
		} 

		// Seuraavat 2 tavua ovat merkkijono bittijonon pituus
		int charsInBitsLength = Integer.parseInt(bitString.substring(16, 32), 2);

		// Seuraavat treeLength bittiä ovat puu
		String treeBits = bitString.substring(32, 32 + treeLength);

		// Seuraavat charsInBitsLength bittiä ovat merkkien bittijono esitykset
		char[] chars = new char[charsInBitsLength / 16];
		int start = 32 + treeLength;
		int end = start + 16;
		for (int i = 0; i < charsInBitsLength / 16; i++) {
			chars[i] = (char)Integer.parseInt(bitString.substring(start, end), 2);
			start += 16;
			end += 16;
		}

		State state = new State();
		state.bitIndex = 0;
		state.charIndex = 0;
		Node root = buildTreeFromBits(treeBits, state, chars);

		// Viimeiset bitit ovat Huffman koodien luoma bittijono.
		String huffmanBits = bitString.substring(start);
		StringBuilder sb = new StringBuilder();
		int index = -1;
		while (index < huffmanBits.length() - 1) {
			index = decode(root, index, huffmanBits, sb);
		}
    return sb.toString();
  }
}