package tiedostopakkaaja;

import tiedostopakkaaja.App;
import utilities.FileHandler;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppTest {

    static String[] testfiles;

    @BeforeClass
    public static void setUp() {
        testfiles = new String[5];
        testfiles[0] = "alice29";
        testfiles[1] = "asyoulik";
        testfiles[2] = "lcet10";
        testfiles[3] = "plrabn12";
        testfiles[4] = "testfile3";

        // Jos vastaavat .bin-päätteiset tiedostot ovat jo hakemistossa, poista ne
        for (int i = 0; i < testfiles.length; i++) {
            String filePath = "testfiles/" + testfiles[i] + ".bin";
            if (FileHandler.fileExists(filePath)) {
                FileHandler.deleteFile(filePath);
            }
        }
    }

    public void setApp() {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();

        String input = "";

        for (int i = 0; i < testfiles.length; i++) {
            Path testFilePath = Paths.get(s, "testfiles", testfiles[i] + ".txt");
            String originalFilePath = testFilePath.toString();

            Path decodedFilePath = Paths.get(s, "testfiles", testfiles[i] + ".bin");

            input += "1\n" + originalFilePath + "\n" + "2\n" + decodedFilePath.toString() + "\n" + "decodedText.txt\n";
        }
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
    
    @Test
    public void fileRemainsTheSame() {

        setApp();

        for (int i = 0; i < testfiles.length; i++) {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            Path testFilePath = Paths.get(s, "testfiles", testfiles[i] + ".txt");
            String originalFilePath = testFilePath.toString();

            Path decodedFilePath = Paths.get(s, "testfiles", testfiles[i] + ".bin");

            String[] args = null;
            
            // Ensin valitsee pakkaamisen ja pakkaa tiedoston
            App.main(args);

            // Tämä valitsee purkamisen ja purkaa tiedoston
            App.main(args);

            String originalFileText = FileHandler.readTextFromFile(originalFilePath);

            String resultingFilePath = Paths.get(s, "testfiles", "decodedText.txt").toString();

            String resultingFileText = FileHandler.readTextFromFile(resultingFilePath);

            assertEquals(originalFileText, resultingFileText);

            FileHandler.deleteFile(decodedFilePath.toString());

            FileHandler.deleteFile(resultingFilePath);
        }
    }
}
