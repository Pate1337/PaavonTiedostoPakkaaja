* String tyyppisen bittijonon muuntaminen tavuiksi ja tallentaminen ja lukeminen tiedostoon.
* BitSetistä luovuttu.
  * Helpompi manipuloida bittejä itse ja luoda sellainen tavutaulukko (byte-array), kuin tähän tehtävään soveltuu.
* Ongelmia jotka selätetty:
  * Javan tyypit (int, byte, long jne.) varaavat aina ensimmäisen bitin etumerkin ilmaisemiseen. 
    * Esimerkiksi byteen (8 bittiä) voi tallentaa kokonaisluvut, jotka ovat välillä -127 ja 127 (2^7). Ei kokonaislukua 255 (2^8).
    * Tämä saatiin voitettua manipuloimalla bittejä bitwise operaatioilla.
    * Esimerkiksi Byte.parseByte() antaa error jos parametrina annettu string '10000000'
  * Ongelmaa aiheutti myös se, että tavu, jonka lopussa oli nollia, tallentui liian pieneksi arvoksi. Esim. "11000000" tallennettiin tavuna "00000011".
  * Tavut joiden arvo oli 0 (00000000) piti myös saada tallennettua tiedostoon. Oli ongelma myös jossain vaiheessa.

* Maven projekti luotu.

* Seuraavaksi:
  * Alan jäsentelemään koodia eri luokkiin ja metodeihin ja luon javadocin.
  * Koodin testaus.
  * Pitää pohtia, millaisen ohjelman luon.
    * Käyttöliittymä voisi kysyä käyttäjältä ensin tiedoston polkua, jonka jälkeen kysytään halutaanko tiedosto pakata vai purkaa.
    * Seuraavaksi kysytään polkua, johon purettu/pakattu tiedosto halutaan tallentaa.
    * Tämän jälkeen ohjelma suorittaa annetun toimenpiteen ja tallentaa puretun/pakatun tiedoston annettuun polkuun.
  * Huffmanin algoritmin toteuttaminen Javan tietorakenteita käyttäen.

* Tunnit: 6 + 2 + 1 = 9
