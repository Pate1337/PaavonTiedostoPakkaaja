package tiedostopakkaaja;

import algorithm.Huffman;
import utilities.NotWellImplementedYet;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

// Tilapaisratkaisu
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class App 
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main( String[] args ) {
        
        // Lopullisessa versiossa käyttäjä antaa tiedoston nimen, ja se tiedosto pakataan.
        // Toiminnallisuus siirtyy myös muualle main()-metodista. Tilapäisratkaisu.

        System.out.println("Vaihtoehdot:");
        System.out.println("(1) Syötä oma teksti");
        System.out.println("(2) Pakkaa tiedosto originalFile.txt");
        String text = scanner.nextLine();
        while (true) {
            if (text.equals("1")) {
                System.out.println("Syötä teksti jonka haluat pakata: ");
                text = scanner.nextLine();
                while (text.length() == 0) {
                    System.out.println("Annettu teksti on tyhjä, anna ainakin yksi merkki.");
                    text = scanner.nextLine();
                }
                System.out.println("Aluperäisen tekstin koko tavuina: " + text.length());
                break;
            } else if (text.equals("2")) {
                String fileName = "originalFile.txt";
                String line = null;
                try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader = 
                        new FileReader(fileName);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = 
                        new BufferedReader(fileReader);

                    while((line = bufferedReader.readLine()) != null) {
                        text += line;
                    }   

                    // Always close files.
                    bufferedReader.close();         
                }
                catch(Exception ex) {
                    System.out.println(ex);                
                }
                // Tiedoston koko
                File f = new File("originalFile.txt");
                if (f.exists() && f.isFile()) {
                    System.out.println("Tiedoston originalFile.txt koko tavuina: " + f.length());
                }
                break;
            } else {
                System.out.println("Syötä 1 tai 2 jatkaaksesi!");
                text = scanner.nextLine();
            }
        }

        // -----------------------------------------------------------

        Map<Character, Integer> freq = new HashMap<>();

		String bitString = Huffman.encodeTextToBitString(text, freq);

        // System.out.println("\nHuffmanin tuottama bittijono:\n" + bitString);

        // Tän metodin nimi on vaan merkkinä itselle, että suoritan refaktoroinnin.
        String stringFromFile = NotWellImplementedYet.doRefactoring(bitString);

        // encode toimii oikein, mutta decode ei toimi nyt oikein !!!

        // decodeBitStringToText tarvitsee HashMapin, joka luodaan encoding yhteydessä.
        // HashMap joka sisältää merkit ja niiden esiintymistiheydet.
        String decodedText = Huffman.decodeBitStringToText(stringFromFile, freq);

        System.out.println("Näytä tiedostosta result.bin purettu teksti? (y/n)");
        text = scanner.nextLine();
        if (text.equals("y")) {
            System.out.println("\nTiedostosta luettu ja dekoodattu teksti: \n");
            System.out.println(decodedText);
        }
        System.out.println();
    }
}
