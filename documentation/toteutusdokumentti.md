# Toteutusdokumentti

* Ohjelman yleisrakenne
* Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)
* Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen)
* Työn mahdolliset puutteet ja parannusehdotukset
* Lähteet

### Ohjelman yleisrakenne

Ohjelman avulla voi pakata tekstitiedostoja (.txt). Pakkaamisen lopputuloksena ohjelma luo binääritiedoston (.bin), jonka koko on noin 40-60 % alkuperäisen tekstitiedoston koosta. Ohjelman avulla voidaan pakkauksen tuloksena saatu binääritiedosto myös purkaa täysin alkuperäiseen muotoon. Ohjelmaa ei voi käyttää muiden binääritiedostojen purkamiseen, kuin tällä ohjelmalla pakattuihin binääritiedostoihin.

Ohjelma käyttää tiedoston pakkaamiseen ja purkamiseen Huffmanin koodausta. Huffmanin koodauksen perusidea on esitetty [määrittelydokumentissa](https://github.com/Pate1337/PaavonTiedostoPakkaaja/blob/master/documentation/maarittelydokumentti.md).

Jotta pakatun tiedoston (binääritiedosto) purkaminen on mahdollista, on jokaisen pakatun tiedoston alkuun tallennettava purkamiseen tarvittava data. Data sisältää Huffman puun bittijonoesityksen (koko, eli bittien määrä voi vaihdella) sekä alkuperäisessä tekstitiedostossa esiintyvät merkit ja niiden määrät.

Huffmanin algoritmi sijaitsee luokassa Huffman. Sen tärkeimmät metodit ovat encodeTextToBitString ja decodeBitStringToText. Metodi encodeTextToBitString saa parametrinaan alkuperäisen tekstitiedoston sisällön String-oliona ja se palauttaa binääritiedostoon tallennettavan bittijono Stringin.

Bittijono Stringien muuttaminen varsinaisiksi biteiksi tapahtuu luokassa Converter.

Tiedostojen käsittely tapahtuu luokassa FileHandler.

### Suorituskyky

Suorituskyky raportin voi luoda ajamalla testit komennolla
```
mvn test -Defficiency=true
```

Alla on erään raportin sisältö.

#### Prioriteettijono

Raportissa verrataan toteuttamaani prioriteettijonoa Javan prioriteettijonoon insert-operaation osalta. Aluksi prioriteettijonoihin lisätään 10000 solmu-oliota, jonka jälkeen lisäykset toteutetaan vielä 100000, 1000000, 2000000 ja 4000000 solmulle. Jokainen aika, joka raportissa näkyy, on keskiarvo 50:stä eri mittauksesta. Jokainen lisäys on toteutettu siten, että ne ovat "worst case", eli uudella lisättävällä solmulla on suurempi prioriteetti, kuin aiemmin lisätyillä solmuilla. Alla olevassa taulukossa on erään raportin sisältö.

Solmut | NodePriorityQueue (ms) | Javan PriorityQueue (ms)
--- | --- | ---
10000 | 1 | 1
100000 | 5 | 4
1000000 | 70 | 60
2000000 | 139 | 138
4000000 | 309 | 297

Taulukon perusteella luotu graafi näyttää seuraavalta:

<img src="https://raw.githubusercontent.com/Pate1337/PaavonTiedostoPakkaaja/master/documentation/kuvat/chart1.png" width="750">

Taulukosta voidaan huomata, että myös minun toteutukseni prioriteettijonosta näyttäisi toteuttavan aikavaativuuden O(nlogn), jossa n on solmujen määrä. Tarkempi analyysi on luvassa ensi viikolla :D

Raportti vertaa myös Javan prioriteettijonon ja minun toteutukseni poll-operaation nopeutta. Mittaukset on toteutettu samalla tavalla, kuin insert-operaation osalta. Tässä on erään raportin sisältö.

Solmut | NodePriorityQueue (ms) | Javan PriorityQueue (ms)
--- | --- | ---
10000 | 0 | 1
100000 | 1 | 10
1000000 | 17 | 126
2000000 | 35 | 262
4000000 | 71 | 544

#### Huffman

Huffman algoritmin suoritusaikoja aloitettu luokassa HuffmanTest.

### Lähteet

* [Techie Delight - Huffman coding compression algorithm](https://www.techiedelight.com/huffman-coding/)

* Jyrki Kivinen, Tietorakenteet ja algoritmit kurssimateriaali.