* Perehtymistä Huffmanin koodaukseen sekä diskreettiin kosinimuunnokseen.
* Havaitsin, että niin mielenkiintoinen aihe, kuin kuvatiedoston pakkaaminen olisikin, ei aikani todennäköisesti riitä sille.
  * Epäselväksi jäi, miten kuvan pikselit saadaan arvoiksi Javalla, kuten diskreetti kosinimuunnos edellyttää. Lukemiseni perusteella pikselin arvo ilmaistaan 
  rgba-muodossa, joka sisältää nejä kenttää (red, blue, green, alpha).
* Päädyin tekstitedoston pakkaamiseen Huffmanin koodauksella.
  * Huffmanin koodauksessa hieman epäselväksi jäi esimerkeissä esitetyt tulokset, joissa esim. merkkijonot voidaan esittää niitä vastaavina
  Huffmanin koodeina, jotka koostuvat pelkästään merkeistä '0' ja '1'. Kuitenkin merkit (char) '0' ja '1' käyttävät käsitykseni mukaan 
  kuitenkin 8 bittiä muiden merkkien tapaan, joten enemmän '0' ja '1' merkkejä sisältävät String-oliot luulisi kuitenkin olevan suurempikokoisia,
  kuin muista merkeistä koostuvat pienemmät merkkijonot.
  * Siksi ajattelin, että merkkijonosta, jossa on 0 ja 1 merkkejä, tulee tehdä taulukko, joiden arvot ovat true tai false, koska ne ovat 
  vain 1 bitin mittaisia.
* Huffmanin koodauksessa käytettävä algoritmi on selkeä, mutta tietorakenteiden toteuttamiseen itse tulee vielä perehtyä. 
  * Epäselväksi jäi, miten toteutan prioriteettijonon.
  
* Seuraavaksi alan kirjoittaa Huffmanin algoritmia käyttäen Javan valmiita tietokanteita.

* Bittien kirjoittamista reenattu Javalla. Löytö: https://stackoverflow.com/questions/33086677/writing-binary-value-to-file-for-huffman-encoding

* Ehkä hyödyllinen https://www.codejava.net/java-se/file-io/how-to-read-and-write-binary-files-in-java

* Myös tässä hyödyllinen ehkä BitSetin tilalle: https://stackoverflow.com/questions/4220917/is-it-possible-to-read-write-bits-from-a-file-using-java

* Tunnit: 6 + 3 = 9
