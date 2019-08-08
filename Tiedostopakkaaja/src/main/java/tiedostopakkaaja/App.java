package tiedostopakkaaja;

import algorithm.Huffman;
import utilities.NotWellImplementedYet;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class App 
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main( String[] args ) {
        
        System.out.println("Syötä teksti jonka haluat pakata tiedostoon result.bin: ");
        String text = scanner.nextLine();
        while (text.length() < 3) {
            System.out.println("Kaatuu, laita enemmän merkkejä ja muutakin kuin pelkkää välilyöntiä (pitkät välilyöntiketjut ei toimi kunnolla vielä)");
            text = scanner.nextLine();
        }

        Map<Character, Integer> freq = new HashMap<>();

		String bitString = Huffman.encodeTextToBitString(text, freq);

        System.out.println("Alkuperäisen tekstin koko tavuina (char = 2 tavua): " + text.length() * 2);

        System.out.println("\nEncoded string is :\n" + bitString);

        // Tän metodin nimi on vaan merkkinä itselle, että suoritan refaktoroinnin.
        String stringFromFile = NotWellImplementedYet.doRefactoring(bitString);

        // decodeBitStringToText tarvitsee HashMapin, joka luodaan encoding yhteydessä.
        // HashMap joka sisältää merkit ja niiden esiintymistiheydet.
        String decodedText = Huffman.decodeBitStringToText(stringFromFile, freq);

        System.out.println("\nDecoded string is: \n");
        System.out.println(decodedText);
        System.out.println();
    }
}
