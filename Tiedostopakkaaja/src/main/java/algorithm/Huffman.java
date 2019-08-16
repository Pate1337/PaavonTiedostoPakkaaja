package algorithm;

import algorithm.Node;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

/** A helper class to be used when building a tree from the bitsequence String.
* Used in buildTreeFromBits.
* @see buildTreeFromBits
*/
class State {
	int bitIndex;
	int charIndex;
}

/** Contains all the functionality for encoding and decoding text using Huffman coding
 */
public class Huffman {
  /** Creates a HashMap containing the characters and their Huffman codes. Goes through
	* the tree starting from the left most leaf node.
	* @param root A Node of the Huffman tree
	* @param str A String containing the root node's Huffman code
	* @param huffmanCode A Map that contains all the Huffman codes for
	* already handled leaf nodes
	* @see Node
	*/
	public static void encode(Node root, String str, Map<Character, String> huffmanCode, StringBuilder tree, StringBuilder characters) {
		if (root == null)
			return;

		// found a leaf node
		if (root.left == null && root.right == null) {
			tree.append("0");
			characters.append(root.character);
			huffmanCode.put(root.character, str);
		} else {
			tree.append("1");
		}

		encode(root.left, str + "0", huffmanCode, tree, characters);
		encode(root.right, str + "1", huffmanCode, tree, characters);
	}

	/** Creates a StringBuilder containing the decoded text
	* @param root A Node of the Huffman tree
	* @param index An integer that keeps track of the current index of the bit in the string
	* @param string A String containing the bits
	* @param sb A StringBuilder that adds all the leaf node characters into a string
	* @return An integer that points the index of the next bit to handle
	*/
	public static int decode(Node root, int index, String string, StringBuilder sb) {
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

	// Builds Huffman Tree and huffmanCode and decode given input text
	// Rakentaa Huffman puun ja palauttaa juurisolmun.
	public static Node buildHuffmanTree(Map<Character, Integer> freq) {
		PriorityQueue<Node> pq = new PriorityQueue<>((l, r) -> l.frequency - r.frequency);
		// Create a leaf node for each character and add it
		// to the priority queue.
		for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
			pq.add(new Node(entry.getKey(), entry.getValue()));
		}

		// do till there is more than one node in the queue
		while (pq.size() != 1) {
			// Remove the two nodes of highest priority
			// (lowest frequency) from the queue
			Node right = pq.poll();
			Node left = pq.poll();

			// System.out.println("Smallest frequencies: " + left.character + ": " + left.frequency + ", " + right.character + ": " + right.frequency);

			// Create a new internal node with these two nodes as children 
			// and with frequency equal to the sum of the two nodes
			// frequencies. Add the new node to the priority queue.
			int sum = left.frequency + right.frequency;
			pq.add(new Node('\0', sum, left, right));
		}
		return pq.peek();
  }
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

	public static String handleOneCharacterCaseOnEncode(String text, char onlyCharacter) {
		String toReturn = "";
		// Ensimmäiset 16 bittiä 0, ilmaisevat puun pituuden
		toReturn += "0000000000000000";
		// Seuraavat 16 bittiä ilmaisevat merkeistä muodostetun bittijonon pituuden biteissä
		// Tässä tapauksessa 16 bittiä, eli "0000000000010000"
		toReturn += "0000000000010000";
		// Seuraavat bitit olisivat puu, jota ei ole, joten bittejäkään ei ole.
		// Seuraavat bitit ilmaisevat ainoan merkin bittijonoesityksen.
		toReturn += fillBeginningWithZeroes(Integer.toBinaryString(onlyCharacter));
		// Loput merkit ovat merkkien määrän verran 0 bittejä
		for (int i = 0; i < text.length(); i++) {
			toReturn += "0";
		}
		return toReturn;
	}

	private static String handleOneCharacterCaseOnDecode(String bitString, char onlyCharacter) {
		// Seuraavat 2 tavua vastaavat integer arvoa 16.
		// Sitä seuraavat 16 bittiä kuvaavat tiedoston ainoaa merkkiä.
		// Loput bitit ovat merkkien määrän verran 0 bittejä.
		String endBits = bitString.substring(48);
		String toReturn = "";
		for (int i = 0; i < endBits.length(); i++) {
			toReturn += onlyCharacter;
		}
		return toReturn;
	}

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

    // Luo Huffman koodit jokaiselle merkille.
		// Luo samalla bittijono tree, jossa 0 kuvaa lehtisolmua ja 1 sisäsolmua.
		// Luo samalla characters StringBuilder, johon tallennetaan lehtisolmuja vastaavat merkit.
		StringBuilder tree = new StringBuilder();
		StringBuilder characters = new StringBuilder();
		Map<Character, String> huffmanCode = new HashMap<>();
    encode(root, "", huffmanCode, tree, characters);

		// Käy läpi lehtisolmujen merkit ja luo jokaisesta bittijonot, joiden pituus on 16 bittiä (2 tavua)
		// 16 bittiä varattu merkeille, koska esim. merkkiä € ei voi ilmaista 8 bitin avulla.
		StringBuilder charsInBits = new StringBuilder();
		for (int i = 0; i < characters.toString().length(); i++) {
			String charInBits = fillBeginningWithZeroes(Integer.toBinaryString(characters.toString().charAt(i)));
			charsInBits.append(charInBits);
		}

		// Luo puun pituutta esittävä bittijono (16 bittiä)
		int treeLength = tree.toString().length();
		String treeLengthInBits = fillBeginningWithZeroes(Integer.toBinaryString(treeLength));

		// Luo mekkejä esittävän bittijonon pituuden esittävä bittijono (16 bittiä)
		int charsLength = charsInBits.toString().length();
		String charsLengthInBits = fillBeginningWithZeroes(Integer.toBinaryString(charsLength));

		// Luo kokonainen bittijono String.
		// Bittijonon ensimmäiset 2 tavua ilmaisevat puu bittijonon pituuden biteissä.
		// Bittijonon seuraavat 2 tavua ilmaisevat merkeistä muodostetun bittijonon pituuden biteissä.
		// Seuraavat bitit ilmaisevat puun.
		// Seuraavat bitit ilmaisevat merkit.
		// Loput bitit ilmaisevat Huffman koodeilla luodun bittijonon.
		StringBuilder finalBitString = new StringBuilder();
		finalBitString.append(treeLengthInBits);
		finalBitString.append(charsLengthInBits);
		finalBitString.append(tree.toString());
		finalBitString.append(charsInBits.toString());
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < text.length(); i++) {
			sb.append(huffmanCode.get(text.charAt(i)));
		}
		finalBitString.append(sb.toString());

		return finalBitString.toString();
  }

  public static String decodeBitStringToText (String bitString) {

		// Ensimmäiset 2 tavua ovat puun pituus.
		int treeLength = Integer.parseInt(bitString.substring(0, 16), 2);

		// Erikoistapaus. Tällöin alkuperäinen tiedosto sisältää vain yhtä merkkiä, esim. "aaaaa"
		if (treeLength == 0) {
			char onlyChar = (char)Integer.parseInt(bitString.substring(32, 48), 2);
			return handleOneCharacterCaseOnDecode(bitString, onlyChar);
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