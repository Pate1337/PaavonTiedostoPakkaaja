# Testausdokumentti

### Miten on testattu?

Testit on kirjoitettu käyttämällä JUnitia.

### Mitä on testattu?

Algoritmin toimintaa on testattu useilla yksikkötesteillä, jotka varmistavat, että jokainen metodien encodeTextToBitString ja decodeBitStringToText alimetodi toimii niin kuin pitää. Yksikkötestit varmistavat ensinnäkin, että julkiset metodit encodeTextToBitString ja decodeBitStringToText palauttavat annetuilla argumenteilla oikean arvon. Mikäli jompikumi näistä testeistä epäonnistuu, on yksikkötestein testattu vielä ei-julkiset (ovat toistaiseksi vielä julkisia 26.8.2019) alimetodit, joita metodien suorituksessa kutsutaan. 

Nämä testit varmistavat muun muassa, että tiedostoa pakatessa luodaan oikeanlainen header-bittijono tiedostoon, Huffman koodit jokaiselle merkille luodaan oikein, merkkien lukumäärä tiedostossa lasketaan oikein, Huffman puu luodaan oikein. Testit varmistavat lisäksi erikoistapauksen, jossa tiedoston teksti koostuu vain yhdestä merkistä.

Tiedostoa purkaessa testit varmistavat, että tiedoston header-bittijonosta saadaan tarvittava data tiedoston dekoodamiseen. Näitä ovat jokainen merkki ja Huffman puu.

Omat tietorakenteet on myös testattu laajoin yksikkötestein.

Koko sovelluksen toiminta on testattu hakemistossa /Tiedostopakkaaja/testfiles olevien tekstitiedostojen avulla. Jokaista tiedostoa kohden testataan sovelluksen toiminta molempiin suuntiin:
  1. Ensin pakataan tekstitiedosto binääritiedostoon.
  2. Sen jälkeen binääritiedosto puretaan takaisin tekstitiedostoksi.

Testi varmistaa, että alkuperäinen tekstitiedosto on sama, kuin kohdassa 2 luotu tekstitiedosto. Lisäksi se varmistaa, että käyttöliittymä toimii kuten pitää ja että tiedostoon kirjoittaminen ja tiedostosta lukeminen toimivat. Testin suorituksen aikana luodut uudet tiedostot poistetaan automaattisesti testin suorituksen jälkeen, jolloin jäljelle jäävät vain alkuperäiset testitiedostot.

### Testauksessa käytettävät syötteet

Hakemistossa /Tiedostopakkaaja/testfiles on 4 tekstitiedostoa: alice29.txt, asyoulik.txt, lcet10.tct ja plrabn12.txt. Nämä tiedostot on ladattavissa [täältä](http://www.data-compression.info/Corpora/CanterburyCorpus/index.html). Tiedostot ovat usein käytössä testisyötteinä tiedoston pakkaamista ja purkamista testatessa.

### Testien toistaminen

Testit voidaan suorittaa hakemistossa /Tiedostopakkaaja komennolla
```
mvn test
```

Jos haluat suorittaa lisäksi tehokkuustestejä, jotka saattavat viedä useita minuutteja, se onnistuu komennolla:

```
mvn test -Defficiency=true
```

Testien rivikattavuusraportin voi luoda komennolla
```
mvn test jacoco:report
```
Testien rivikattavuusraporttia voi tarkastella selaimella osoitteessa /target/site/jacoco/index.html.