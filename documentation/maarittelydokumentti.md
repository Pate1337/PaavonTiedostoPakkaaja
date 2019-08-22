# Määrittelydokumentti

* Mitä algoritmeja ja tietorakenteita toteutat työssäsi
* Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet
* Mitä syötteitä ohjelma saa ja miten näitä käytetään
* Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)
* Lähteet


### Käytettävät algoritmit

* Huffmanin koodaus


### Käytettävät tietorakenteet

* Prioriteettijono
  * Jotta tiedostossa esiintyvät merkit saadaan pidettyä esiintymistiheyden mukaisessa järjestyksessä puuta luodessa.
* Binääripuu
  * Tiedostossa esiintyvät merkit asetetaan binääripuuhun, jonka luominen aloitetaan alimmista lehdistä.
* Hajatustaulu, johon tallennetaan kutakin merkkiä vastaava esiintymistiheys

### Ongelma

* Miten saada tekstiedosto pakattua alkuperäistä tiedostoa 40-60% pienempään tilaan ja palautettua täysin ennalleen?

### Ohjelman syötteet

* Tekstitiedosto, jossa suuri määrä tekstiä.
  * Huffmanin koodaus saavuttaa parhaan suorituskykynsä suurilla syötteillä.

### Ongelman ratkaisu

#### Huffmanin koodaus

Huffmanin koodauksen perusidea on laskea tekstisyötteessä esiintyvien merkkien esiintymistiheydet ja luoda niiden pohjalta jokaiselle merkille uniikki esitysmuoto, joka koostuu vain 0 ja 1 biteistä. Kun kaikille merkeille on luotu uniikki bittijonoesitys, voidaan alkuperäinen teksti esittää pelkästään näitä bittijonoja käyttämällä.

#### Miten merkit muutetaan bittijonoiksi?

Kuten edellä mainittiin, aloitetaan ongelman ratkaisu laskemalla tekstissä esiintyvien merkkien lukumäärät (esiintymistiheydet). Merkit ja niiden esiintymistiheydet tallennetaan hajautustauluun, avaimena merkki ja arvona esiintymistiheys.

Seuraavaksi jokainen hajautustaulun avain-arvoparista luodaan puun lehtisolmut, joilla on kentät character, frequency, left ja right. (left ja right ovat luodun solmun lapsisolmut, lehtisolmujen tapauksessa niiden arvot ovat null). Jokainen solmu lisätään prioriteettijonoon, jossa solmut ovat järjestyksessä niiden esiintymistiheyksien mukaan. Suurimman prioriteetin saa solmu, jonka esiintymistiheys on pienin.

Olkoon esimerkiksi alkuperäinen teksti merkkijono, joka koostuu merkeistä A, B, C, D ja E. Merkkien esiintymistiheydet ovat järjestyksessä 15, 7, 6, 6 ja 5. Seuraava kuva havainnollistaa tilannetta, jossa lehtisolmut ovat esiintymistiheyksien mukaisessa järjestyksessä prioriteettijonossa:

<img src="https://raw.githubusercontent.com/Pate1337/PaavonTiedostoPakkaaja/master/documentation/kuvat/kuva1.jpg" width="750">

Seuraavaksi luodaan Huffman puu. Puun luominen tapahtuu suorittamalla askelia a. - c., niin kauan, kun prioriteettijonossa on enemmän kuin yksi solmu. 
  a. Poistetaan kaksi pienimmän esiintymistiheyden omaavaa solmua jonosta.
  b. Luodaan uusi sisäsolmu, jonka oikeaksi lapseksi asetetaan ensimmäisenä jonosta poistettu solmu ja vasemmaksi lapseksi jälkimmäisenä poistettu solmu. Sisäsolmun esiintymistiheydeksi tulee lapsisolmujen esintyymistiheyksien summa.
  c. Lisätään uusi solmu prioriteettijonoon.

Viimeisenä prioriteettijonossa oleva solmu, on puun juurisolmu.

Seuraava kuvat havainnollistavat Huffman puun luontia.

<img src="https://raw.githubusercontent.com/Pate1337/PaavonTiedostoPakkaaja/master/documentation/kuvat/kuva2.jpg" width="750">

<img src="https://raw.githubusercontent.com/Pate1337/PaavonTiedostoPakkaaja/master/documentation/kuvat/kuva3.jpg" width="750">

<img src="https://raw.githubusercontent.com/Pate1337/PaavonTiedostoPakkaaja/master/documentation/kuvat/kuva4.jpg" width="750">

<img src="https://raw.githubusercontent.com/Pate1337/PaavonTiedostoPakkaaja/master/documentation/kuvat/kuva5.jpg" width="750">

Huffman puun avulla jokaiselle lehtisolmussa olevalle merkille, saadaan uniikki bittijonoesitys kuvan (?) esittämällä tavalla.

<img src="https://raw.githubusercontent.com/Pate1337/PaavonTiedostoPakkaaja/master/documentation/kuvat/kuva6.jpg" width="750">

Huffman koodit merkeille ovat siis
A: 1,
B: 000,
C: 001,
D: 010,
E: 011

Jos alkuperäinen merkkijono oli esimerkiksi "ABCDEABCDEABCDEABCDEABCDEABCDABAAAAAAAA", voidaan se esittää Huffman koodeilla muodossa:
"100000101001110000010100111000001010011100000101001110000010100111000001010100011111111"

#### Mitä hyötyä bittijonoesityksestä on?

Edellisen esimerkin tapauksessa merkkijono "ABCDEABCDEABCDEABCDEABCDEABCDABAAAAAAAA" käyttää muistia 39 * 8 = 312 bittiä, sillä jokainen merkki käyttää 8 bittiä.

Huffman koodien avulla luotu bittijono sen sijaan tarvitsee vain 88 bittiä.

Esimerkin tapauksessa Huffman koodauksella voidaan säästää siis jopa 72 % tilaa.


### Tavoitteelliset aika- ja tilavaativuudet

* Prioriteettijonoon asettaminen tapahtuu aikavaativuudella O(log(n)), jossa n on alkioiden lukumäärä.
* Binäärihakupuun läpikäyminen tapahtuu aikavaativuudella O(n).
* Näin ollen koko algoritmin aikavaativuus tulisi olla O(nlog(n)).
* Binäärihakupuun läpikäymisen tilavaativuus on O(n).

### Lähteet

* [Techie Delight - Huffman coding compression algorithm](https://www.techiedelight.com/huffman-coding/)

* Jyrki Kivinen, Tietorakenteet ja algoritmit kurssimateriaali.
