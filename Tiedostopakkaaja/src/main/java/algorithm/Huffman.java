package algorithm;

import algorithm.Node;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Huffman {
  // traverse the Huffman Tree and store Huffman Codes
	// in a map.
	public static void encode(Node root, String str, Map<Character, String> huffmanCode) {
		if (root == null)
			return;

		// found a leaf node
		if (root.left == null && root.right == null) {
			huffmanCode.put(root.character, str);
		}


		encode(root.left, str + "0", huffmanCode);
		encode(root.right, str + "1", huffmanCode);
	}

	// traverse the Huffman Tree and decode the encoded string
	public static int decode(Node root, int index, String string, StringBuilder sb) {
		if (root == null)
			return index;

		// found a leaf node
		if (root.left == null && root.right == null) {
			// System.out.print(root.character);
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
	public static void buildHuffmanTree(PriorityQueue<Node> pq, Map<Character, Integer> freq) {

		// Create a leaf node for each character and add it
		// to the priority queue.
		for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
			pq.add(new Node(entry.getKey(), entry.getValue()));
		}

		// do till there is more than one node in the queue
		while (pq.size() != 1) {
			// Remove the two nodes of highest priority
			// (lowest frequency) from the queue
			Node left = pq.poll();
			Node right = pq.poll();

			// Create a new internal node with these two nodes as children 
			// and with frequency equal to the sum of the two nodes
			// frequencies. Add the new node to the priority queue.
			int sum = left.frequency + right.frequency;
			pq.add(new Node('\0', sum, left, right));
		}
  }
  public static void createFrequenciesHashMap(String text, Map<Character, Integer> freq) {
    for (int i = 0 ; i < text.length(); i++) {
			if (!freq.containsKey(text.charAt(i))) {
				freq.put(text.charAt(i), 0);
			}
			freq.put(text.charAt(i), freq.get(text.charAt(i)) + 1);
		}
  }

  public static String encodeTextToBitString (String text, Map<Character, Integer> freq) {
    Map<Character, String> huffmanCode = new HashMap<>();
    PriorityQueue<Node> pq = new PriorityQueue<>((l, r) -> l.frequency - r.frequency);

    // Create the hashmap containing characters and their frequencies.
    // The content of this HashMap needs to be saved in the file, so it can be read while
    // decoding the binary file.
    createFrequenciesHashMap(text, freq);

    // Build the Huffman tree
    buildHuffmanTree(pq, freq);

    Node root = pq.peek();
    // encode the given parameter String text
    encode(root, "", huffmanCode);

    System.out.println("\nOriginal string was :\n" + text);

		// print encoded string
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < text.length(); i++) {
			sb.append(huffmanCode.get(text.charAt(i)));
		}

    /*
    // print the Huffman codes
		System.out.println("Huffman Codes are :\n");
		for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
    */

    // return the encoded String
    return sb.toString();
  }
  public static String decodeBitStringToText (String bitString, Map<Character, Integer> freq) {
    
    // Tarvitsee parametrinaan HashMapin, joka sisältää merkit ja esiintymistiheydet
    PriorityQueue<Node> pq = new PriorityQueue<>((l, r) -> l.frequency - r.frequency);

    buildHuffmanTree(pq, freq);

    Node root = pq.peek();

    StringBuilder sb = new StringBuilder();

    // decode the encoded string
		int index = -1;
		while (index < bitString.length() - 2) {
			index = decode(root, index, bitString, sb);
		}
    return sb.toString();
  }
}