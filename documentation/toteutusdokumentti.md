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
Raportti on tuloste komentoriville. Alla on erään raportin sisältö.

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

<img src="https://raw.githubusercontent.com/Pate1337/PaavonTiedostoPakkaaja/master/documentation/kuvat/chart2.png" width="750">

#### Huffman

Raportissa on listattu myös Huffman luokan encodeTextToBitString-metodin suoritusaikoja erilaisille syötteille. Tässä on eräs raportti:

Erilaisten merkkien määrät ovat 2, 20 ja 100.

Syötteen pituus | 2 | 20 | 100
--- | --- | --- | ---
100000 | 7 | 5 | 9
1000000 | 37 | 50 | 74
2000000 | 72 | 98 | 156
4000000 | 144 | 193 | 298

<img src="https://raw.githubusercontent.com/Pate1337/PaavonTiedostoPakkaaja/master/documentation/kuvat/chart3.png" width="750">

### Puutteet ja parannusehdotukset

#### Hajautustaulu

Minun toteutukseni HashMapista varaa käyttöönsä 701-pakkaisen taulukon, eikä sen kokoa voi kasvattaa eikä pienentää. Tämä on tämän sovelluksen käyttöön riittävä ratkaisu, koska on hyvin epätodennäköistä, että syötteenä saatu tekstitiedosto sisältäisi enemmän kuin 701 erilaista merkkiä (Hajautustauluun tallennetaan jokainen uniikki merkki). Jos erilaisia merkkejä olisi kuitenkin enemmän kuin 701 kappaletta, niin hajautustaulun toiminnallisuus hidastuisi, koska sen täyttöasteeksi tulisi enemmän kuin 1.

Näin ollen olisi hyvä, jos taulukon koko alkaisi aluksi pienemmästä alkuluvusta kuin 701, ja kasvaisi tarvittaessa.

#### Prioriteettijono

Prioriteettijono on toteutettu pelkästään Node-olioille. Sovellukselle riittävä ratkaisu, mutta tämän luokan muuttaminen geneeriseksi olisi siisti juttu. Prioriteettijono varaa aluksi käyttöönsä 11-paikkaisen taulukon, jonka koko tuplaantuu tarvittaessa. Ylärajaksi prioriteettijonon koolle asetin 4000000 alkiota. Jos prioriteettijonoon yritetään lisätä enemmän kuin 4 miljoonaa alkiota, niin saadaan ArrayIndexOutOfBoundsException. Näin ollen jos syötteessä on 2000001 erilaista merkkiä, niin sovellus kaatuu. (Erilaisten merkkien määrä on yhtä kuin lehtien määrä, ja näin ollen solmuja puussa on 2 * 2000001 - 1 = 4000001).

#### Huffman algoritmi

Koodi sisältää monia kohtia, joihin en ole tyytyväinen. Siellä täällä on StringBuilder-olioita ja jotkut metodit saavat jopa parametreinaan StringBuilderin. Myös tiedostojen "header"-osioiden käsittely olisi pitänyt eriyttää selkeästi omiin metodeihinsa. Osa metodeista on private ja osa taas public (ei mitään tolkkua).

#### Tiedoston lukeminen ja tiedostoon kirjoittaminen

Tällä hetkellä vain maksimissaan 500 kt:n tiedostojen lukeminen on mahdollista. En perehtynyt tähän tarkemmin, mutta näin oletan, koska asetin FileHandler-luokan metodissa readTextFromFile(), bufferin kooksi 506000.

#### Käyttöliittymä

Käyttöliittymä estää muiden tyyppisten, kuin .txt ja .bin tiedostojen pakkaamisen ja purkamisen. Päädyin tähän ratkaisuun, koska en halunnut kuluttaa liikaa aikaa käyttöliittymän kanssa, mutta halusin kuitenkin käsitellä vääränlaiset syötteet. Erityyppisten tiedostojen pakkaaminen ja purkaminen olisi selkeä parannusehdotus.

Käyttöliittymästä saisi myös melko helposti siistin graafisen käyttöliittymän, jossa tiedostosijainnit olisi mahdollista hakea käyttöliittymää käyttäen (kuten vaikka 7-zip ja muut tiedoston pakkausohjelmat).

#### Muut

ArrayList, Arrays.copy() ja StringBuilder jäivät toteuttamatta itse. 

### Lähteet

* [Techie Delight - Huffman coding compression algorithm](https://www.techiedelight.com/huffman-coding/)

* Jyrki Kivinen, Tietorakenteet ja algoritmit kurssimateriaali.

* [HashMap](https://dzone.com/articles/custom-hashmap-implementation-in-java)