# Toteutusdokumentti

* Ohjelman yleisrakenne
* Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)
* Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen)
* Työn mahdolliset puutteet ja parannusehdotukset
* Lähteet

### Suorituskyky

Tätä täytyy vielä hioa. Koko toteutusdokumentti on ihan jäätävän näköinen tällä hetkellä.
Prioriteettijonoa koskeva osio on ihan hyvällä alulla.

#### Prioriteettijono

Tällä hetkellä luokassa NodePriorityQueueTest luodaan suorituskyky raportti (joka on vain tuloste), kun testit ajetaan komennolla mvn test. Raportissa verrataan toteuttamaani prioriteettijonoa Javan prioriteettijonoon insert-operaation osalta. Aluksi prioriteettijonoihin lisätään 10000 solmu-oliota, jonka jälkeen lisäykset toteutetaan vielä 100000, 1000000, 2000000 ja 4000000 solmulle. Jokainen aika, joka raportissa näkyy, on keskiarvo 50:stä eri mittauksesta. Jokainen lisäys on toteutettu siten, että ne ovat "worst case", eli uudella lisättävällä solmulla on suurempi prioriteetti, kuin aiemmin lisätyillä solmuilla. Alla olevassa taulukossa on erään raportin sisältö.

Solmut | NodePriorityQueue (ms) | Javan PriorityQueue (ms)
--- | --- | ---
10000 | 1 | 1
100000 | 5 | 4
1000000 | 70 | 60
2000000 | 139 | 138
4000000 | 309 | 297

Taulukosta voidaan huomata, että myös minun toteutukseni prioriteettijonosta näyttäisi toteuttavan aikavaativuuden O(nlogn), jossa n on solmujen määrä. Tarkempi analyysi on luvassa ensi viikolla :D

#### Huffman

Huffman algoritmin suoritusaikoja aloitettu luokassa HuffmanTest.


JÄTIN ALLA OLEVAN TKESTIN TÄNNE TOISTAISEKSI, KORJATAAN POIS ENSI VIIKOLLA !!

* Ohjelmaa voi käyttää .txt-muotoisten tiedostojen pakkaamiseen ja .bin-muotoisten tiedostojen purkamiseen.
  * Käyttöliittymä estää muiden tyyppien pakkaamisen/purkamisen, muuten todennäköisesti toimis :D

* Pakkaaminen ja purkaminen tapahtuu luokassa Huffman (pakkaus algorithm).
* Pakkaaminen:
  * Kutsutaan metodia encodeTextToBitString, jolle annetaan parametrina String jolle pakkaaminen halutaan suorittaa.
  * Metodi suorittaa seuraavat toimenpiteet:
    1. Luodaan hajautustaulu, joka sisältää tekstissä esiintyvät merkit ja niiden esiintymistiheydet (merkkien määrät).
    2. Luodaan Huffman puu hajautustaulun perusteella. Huffman puu luodaan seuraavasti:
      * Luodaan jokaista merkkiä vastaavat lehtisolmut ja lisätään solmut prioriteettijonoon. Prioriteettijonossa suurimman prioriteetin saa solmu, jonka esiintymistiheys on pienin.
      * Seuraavat askeleet suoritetaan niin kauan, kunnes prioriteettijonossa on vain 1 solmu.
        * Poista 2 suurimman prioriteetin omaavaa solmua jonosta
        * Luodaan sisäsolmu, jonka oikeaksi lapseksi asetetaan edellisistä solmuista se, jonka prioriteetti on suurempi ja vasemmaksi lapseksi toinen. Sisäsolmun esiintymistiheydeksi asetetaan lasten esiintymistiheyksien summa.
        * Sisäsolmu lisätään prioriteettijonoon.
      * Prioriteettijonon ainoa alkio on juurisolmu ja puu on valmis.
    3. Luodaan Huffman koodit jokaiselle tekstissä esiintyvälle merkille ja tallennetaan ne hajautustauluun. Huffman koodit luodaan seuraavasti:
      * Aloita Huffman puun juuresta.
      * Aina kun siirrytään solmusta vasempaan lapsisolmuun, lisätään '0'.
      * Aina kun siirrytään solmusta oikeaan lapsisolmuun, lisätään '1'.
      * Kun saavutaan lehtisolmuun, on luotu lehtisolmua vastaavalle merkille Huffman koodi, joka koostuu merkeistä '0' ja '1'.
      * Näin tehdään jokaiselle lehtisolmulle.
    4. Alkuperäisestä tekstistä voidaan nyt luoda String, joka koostuu pelkästään merkeistä '0' ja '1', korvaamalla jokainen tekstin merkki niitä vastaavilla Huffman koodeilla.
  * Jotta pakattu tiedosto voidaan purkaa, täytyy tekstissä esiintyvät merkit ja Huffman puu tallentaa myös tiedostoon. Myös nämä esitetään biteillä ja ne tallennetaan tiedoston alkuun.
    * Huffman puu voidaan esittää biteillä siten, että jokaista sisäsolmua merkataan merkillä 1 ja lehtisolmua merkillä 0.
    * Bittijono puu luodaan samalla, kun Huffman koodeja luodaan merkeille metodissa encode.
    * Aina kun löydetään lehtisolmu, otetaan talteen myös lehtisolmua vastaava merkki.
    * Jokaiselle merkille on varattuna 16 bittiä, joten ohjelmaa ei voi käyttää tiedostojen pakkaamiseen, jotka sisältävät merkkejä, joiden binäärimuoto vaatii enemmän kuin 16 bittiä.
  * Itse bittijono Stringin muuttaminen tavuiksi tapahtuu luokassa Converter (pakkaus utilities)
    * Tavujen luonnissa viimeiseksi tavuksi lisätään tavu, joka kertoo viimeiseksi muutetun bitin indeksin arvon. Bittijono Stringin pituus ei vältämättä ole jaollinen 8:lla (tavun pituus), joten viimeinen tavu on välttämätön.
  * Lopullinen tiedostoon tallennettava tavutaulukko koostuu siis seuraavasti:
    1. 2 tavua kuvaa bittijono puun pituutta biteissä.
    2. 2 tavua kuvaavat erilaisten merkkien muodostaman bittijonon pituutta biteissä.
      * esim. "abb" bitteinä 000000000110000100000000011000100000000001100010, pituus = 48, binäärilukuna 0000000000110000.
    3. Huffman puu bitteinä (pituus mikä tahansa).
    4. Merkit binäärilukuina, esim. kohdan 2 bittijono.
    5. Huffman koodeilla luotu bittijono.
    6. Viimeinen tavu, joka kertoo viimeiseksi muutetun bitin indeksin.
  * Erikoistapauksena ovat tiedostot, jotka sisältävät vain yhtä merkkiä. Esim. tiedosto, jonka teksti on "aaaaaaaaaa".
  * Tällöin tiedostoon tallennettava tavutaulukko koostuu seuraavasti:
    1. 2 ensimmäistä tavua ilmaisevat arvoa 0, 0000000000000000.
    2. 2 tavua kuvaavat ainoan merkin binäärilukuesitystä.
    3. 0 bitit, joita on yhtä monta, kuin merkkejä oli tiedostossa.
    4. Viimeinen tavu, joka kertoo viimeiseksi muutetun bitin indeksin.
  * Tavut tallennetaan tiedostoon luokan FileHandler metodien avulla.

* Purkaminen:
  * Tapahtuu kutsumalla metodia decodeBitStringToText, joka saa parametrinaan bittijono Stringin.
  * Jos tiedoston 2 ensimmäistä tavua kuvaavat arvoa 0, suoritetaan erikoistapaus, jossa alkuperäisessä tiedostossa oli vain yhtä merkkiä.
  * Muuten muodostetaan bittien perusteella Huffman puu.
  * Näin saadaan selville, miten jokaisen lehden Huffman koodi on muodostettu, ja teksti voidaan palauttaa alkuperäiseksi metodissa decode.