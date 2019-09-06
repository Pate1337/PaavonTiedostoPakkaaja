# Toteutusdokumentti

* Ohjelman yleisrakenne
* Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)
* Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen)
* Työn mahdolliset puutteet ja parannusehdotukset
* Lähteet

## Ohjelman yleisrakenne

Ohjelman avulla voi pakata tekstitiedostoja (.txt). Pakkaamisen lopputuloksena ohjelma luo binääritiedoston (.bin), jonka koko on noin 40-60 % alkuperäisen tekstitiedoston koosta. Ohjelman avulla voidaan pakkauksen tuloksena saatu binääritiedosto myös purkaa täysin alkuperäiseen muotoon. Ohjelmaa ei voi käyttää muiden binääritiedostojen purkamiseen, kuin tällä ohjelmalla pakattuihin binääritiedostoihin.

Ohjelma käyttää tiedoston pakkaamiseen ja purkamiseen Huffmanin koodausta. Huffmanin koodauksen perusidea on esitetty [määrittelydokumentissa](https://github.com/Pate1337/PaavonTiedostoPakkaaja/blob/master/documentation/maarittelydokumentti.md).

Jotta pakatun tiedoston (binääritiedosto) purkaminen on mahdollista, on jokaisen pakatun tiedoston alkuun tallennettava purkamiseen tarvittava data. Data sisältää Huffman puun bittijonoesityksen (koko, eli bittien määrä voi vaihdella) sekä alkuperäisessä tekstitiedostossa esiintyvät merkit ja niiden määrät.

Huffmanin algoritmi sijaitsee luokassa Huffman. Sen tärkeimmät metodit ovat encodeTextToBitString ja decodeBitStringToText. Metodi encodeTextToBitString saa parametrinaan alkuperäisen tekstitiedoston sisällön String-oliona ja se palauttaa binääritiedostoon tallennettavan bittijono Stringin.

Bittijono Stringien muuttaminen varsinaisiksi biteiksi tapahtuu luokassa Converter.

Tiedostojen käsittely tapahtuu luokassa FileHandler.

## Suorituskyky

Suorituskyky raportin voi luoda ajamalla testit komennolla
```
mvn test -Defficiency=true
```
Raportti on tuloste komentoriville. Alla on erään raportin sisältö.

### Prioriteettijono

#### insert

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

Minimikeon insert-operaatio pseudokoodilla esitettynä on seuraavanlainen.

```
heap-insert(A,k)
1.  A.heap-size = A.heap-size+1
2.  i = A.heap-size
3.  while i>1 and A[parent(i)] > k
4.    A[i] = A[parent(i)]
5.    i = parent(i)
6.  A[i]=k
```

Kuten määrittelydokumentissa mainittiin, voidaan minimikeko ajatella binääripuuna. Insert-operaation pahimmassa tapauksessa lisättävä solmu viedään puun juureen. Koska n-alkioisen binääripuun korkeus on O(logn), niin pahimmassa tapauksessa rivit 3-5 suoritetaan logn kertaa, eli insert-operaation aikavaativuudeksi saadaan O(logn).

Raportissa insert-operaatio tehdään yhtä monta kertaa, kuin prioriteettijonossa on alkioita (eli jokaista alkiota kohden yksi lisäys). Jos n on alkioiden määrä, niin tällöin kaikkien insert-operaatioiden aikavaativuudeksi saadaan O(nlogn).

#### poll

Raportti vertaa myös Javan prioriteettijonon ja minun toteutukseni poll-operaation nopeutta. Mittaukset on toteutettu samalla tavalla, kuin insert-operaation osalta. Tässä on erään raportin sisältö.

Solmut | NodePriorityQueue (ms) | Javan PriorityQueue (ms)
--- | --- | ---
10000 | 0 | 1
100000 | 1 | 10
1000000 | 17 | 126
2000000 | 35 | 262
4000000 | 71 | 544

<img src="https://raw.githubusercontent.com/Pate1337/PaavonTiedostoPakkaaja/master/documentation/kuvat/chart2.png" width="750">

Poll-operaation yhteydessä kutsutaan minimikeon `heapify`-operaatiota. Heapify-operaation pseudokoodi on alla:

```
heapify(A,i)
1.  l = left(i)
2.  r = right(i)
3.  if r ≤ A.heap-size
4.    if A[l] > A[r] smallest = r
5.    else smallest = l
5.    if A[i] > A[smallest]
6.      vaihda A[i] ja A[smallest]
7.      heapify(A,smallest)
8.  elsif l == A.heap-size and A[i]>A[l]
9.    vaihda A[i] ja A[l]
```

Samoin kuin insert-operaatiossa, suoritetaan heapify-operaatio pahimmassa yhtä monta kertaa, kuin puun korkeus. Siis heapify-operaation aikavaativuus on O(logn).

Poll-operaation pseudokoodi on seuraavanlainen:

```
heap-del-min(A)
1.  min = A[1]
2.  A[1] = A[A.heap-size]
3.  A.heap-size = A.heap-size -1
4.  heapify(A,1)
5.  return min
```

Rivit 1, 2, 3 ja 5 suoriutuvat vakioajassa O(1), mutta rivin 4 heapifyn takia poll-operaation aikavaativuudeksi saadaan sama kuin heapifyn, eli O(logn).

### Huffman

#### encode

Raportissa on listattu myös Huffman luokan encodeTextToBitString-metodin suoritusaikoja erilaisille syötteille. Tässä on eräs raportti:

Erilaisten merkkien määrät ovat 2, 20 ja 100.

Syötteen pituus | 2 | 20 | 100
--- | --- | --- | ---
100000 | 5 | 6 | 8
1000000 | 54 | 66 | 86
2000000 | 107 | 142 | 163
4000000 | 215 | 277 | 346

<img src="https://raw.githubusercontent.com/Pate1337/PaavonTiedostoPakkaaja/master/documentation/kuvat/chart3.png" width="750">

encodeTextToBitString-metodi on esitetty lyhykäisyydessään alla:

```
encodeTextToBitString(text)
1.  HashMap freq = createFrequenciesHashMap(text)
2.  Node root = buildHuffmanTree(freq)
3.  HashMap huffmanCodes = createHuffmanCodes(root)
4.  Loput operaatiot
```

Jos syötteessä olevien merkkien yhteenlaskettu määrä on e ja erilaisten merkkien määrä on n.
Rivillä 1 suoritetaan vakioaikaset O(1) hajautustauluun lisäysoperaatiot e kertaa. freq avaimina toimivat merkit, joten hajautustaulun kooksi tulee n (erilaiset merkit).

Rivillä 2 kutsutaan metodia buildHuffmanTree. Metodissa buildHuffmanTree lisätään ensin lehtisolmut prioriteettijonoon, lehtisolmujen määrä on n, eli sama kuin hajautustaulun freq koko. Tämän jälkeen prioriteettijonoon lisätään vielä loput solmut, joita on 2n-1 kappaletta. Koska prioriteettijonon insert-operaation aikavaativuus on O(logn), niin koko buildHuffmanTree-metodin aikavaativuudeksi saadaan O(nlogn).

Rivillä 3 kutsutaan metodia createHuffmanCodes, joka puolestaan kutsuu metodia encode jokaiselle puun root solmulle. Solmuja on 2n-1 kappaletta, joten metodin createHuffmanCodes aikavaativuus on O(n).

Rivin 4 loput operaatiot, ovat bittijono merkkijonon luomiseen liittyvät operaatiot, joiden aikavaativuus on O(n).

Metodin encodeTextToBitString lopullinen aikavaativuus on siis O(nlogn).

#### decode

Eräs raportti decodeBitStringToText-metodille.

Erilaisten merkkien määrät ovat 2, 20 ja 100.

Syötteen pituus | 2 | 20 | 100
--- | --- | --- | ---
100000 | 2 | 4 | 7
1000000 | 15 | 45 | 70
2000000 | 32 | 91 | 156
4000000 | 76 | 190 | 278

<img src="https://raw.githubusercontent.com/Pate1337/PaavonTiedostoPakkaaja/master/documentation/kuvat/chart4.png" width="750">

## Puutteet ja parannusehdotukset

### Hajautustaulu

Minun toteutukseni HashMapista varaa käyttöönsä 701-pakkaisen taulukon, eikä sen kokoa voi kasvattaa eikä pienentää. Tämä on tämän sovelluksen käyttöön riittävä ratkaisu, koska on hyvin epätodennäköistä, että syötteenä saatu tekstitiedosto sisältäisi enemmän kuin 701 erilaista merkkiä (Hajautustauluun tallennetaan jokainen uniikki merkki). Jos erilaisia merkkejä olisi kuitenkin enemmän kuin 701 kappaletta, niin hajautustaulun toiminnallisuus hidastuisi, koska sen täyttöasteeksi tulisi enemmän kuin 1.

Näin ollen olisi hyvä, jos taulukon koko alkaisi aluksi pienemmästä alkuluvusta kuin 701, ja kasvaisi tarvittaessa.

### Prioriteettijono

Prioriteettijono on toteutettu pelkästään Node-olioille. Sovellukselle riittävä ratkaisu, mutta tämän luokan muuttaminen geneeriseksi olisi siisti juttu. Prioriteettijono varaa aluksi käyttöönsä 11-paikkaisen taulukon, jonka koko tuplaantuu tarvittaessa. Ylärajaksi prioriteettijonon koolle asetin 4000000 alkiota. Jos prioriteettijonoon yritetään lisätä enemmän kuin 4 miljoonaa alkiota, niin saadaan ArrayIndexOutOfBoundsException. Näin ollen jos syötteessä on 2000001 erilaista merkkiä, niin sovellus kaatuu. (Erilaisten merkkien määrä on yhtä kuin lehtien määrä, ja näin ollen solmuja puussa on 2 * 2000001 - 1 = 4000001).

### Huffman algoritmi

Koodi sisältää monia kohtia, joihin en ole tyytyväinen. Siellä täällä on StringBuilder-olioita ja jotkut metodit saavat jopa parametreinaan StringBuilderin. Myös tiedostojen "header"-osioiden käsittely olisi pitänyt eriyttää selkeästi omiin metodeihinsa. Osa metodeista on private ja osa taas public (ei mitään tolkkua).

### Tiedoston lukeminen ja tiedostoon kirjoittaminen

Tällä hetkellä vain maksimissaan 500 kt:n tiedostojen lukeminen on mahdollista. En perehtynyt tähän tarkemmin, mutta näin oletan, koska asetin FileHandler-luokan metodissa readTextFromFile(), bufferin kooksi 506000.

### Käyttöliittymä

Käyttöliittymä estää muiden tyyppisten, kuin .txt ja .bin tiedostojen pakkaamisen ja purkamisen. Päädyin tähän ratkaisuun, koska en halunnut kuluttaa liikaa aikaa käyttöliittymän kanssa, mutta halusin kuitenkin käsitellä vääränlaiset syötteet. Erityyppisten tiedostojen pakkaaminen ja purkaminen olisi selkeä parannusehdotus.

Käyttöliittymästä saisi myös melko helposti siistin graafisen käyttöliittymän, jossa tiedostosijainnit olisi mahdollista hakea käyttöliittymää käyttäen (kuten vaikka 7-zip ja muut tiedoston pakkausohjelmat).

### Muut

ArrayList, Arrays.copy() ja StringBuilder jäivät toteuttamatta itse. Lisää tehokkuustestejä olisi ollut myös mukava tehdä.

## Lähteet

* [Techie Delight - Huffman coding compression algorithm](https://www.techiedelight.com/huffman-coding/)

* Jyrki Kivinen, Tietorakenteet ja algoritmit kurssimateriaali.

* [HashMap](https://dzone.com/articles/custom-hashmap-implementation-in-java)