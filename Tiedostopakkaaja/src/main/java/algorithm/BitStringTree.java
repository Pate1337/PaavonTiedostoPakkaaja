package algorithm;

/** A class that holds the bitstring representation of the Huffman tree
* and the corresponding characters of the leaf nodes.
* For example a tree
* <p>		null</p>
*	<p>		/  \</p>
* <p>	null   b</p>
* <p> /  \</p>
* <p>a		 c</p>
* would be 11000 as a bitstring tree.
* And the character order would be acb.
*/
public class BitStringTree {
	StringBuilder tree = new StringBuilder();
	StringBuilder characters = new StringBuilder();
}