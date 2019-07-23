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

* Miten saada tekstiedosto pakattua alkuperäistä tiedostoa 30-40% pienempään tilaan ja palautettua täysin ennalleen?
* Käytetään Huffmanin koodausta. Syy seuraavassa 'Ongelman ratkaisu'.

### Ohjelman syötteet

* Tekstitiedosto, jossa suuri määrä tekstiä.
  * Huffmanin koodaus saavuttaa parhaan suorituskykynsä suurilla syötteillä.

### Ongelman ratkaisu

* Luo solmut jokaisesta merkistä.
  * Tallenna tieto esiintymistiheydestä
* Lisää solmut prioriteettijonoon, suurimman esiintymistiheyden omaava solmu ensimmäiseksi.
* Käy prioriteettijonoa läpi niin kauan, kunnes jonossa on enää yksi solmu
  * Ota kaksi pienimmän esiintymistiheyden omaavaa solmua jonosta.
  * Luo solmu, jonka lapsiksi edellä mainitut solmut lisätään ja jonka esiintymistiheydeksi tulee lapsisolmujen esiintymistiheyksien summa.
  * Lisää uusi solmu prioriteettijonoon.
* Jäljelle jäävä solmu on juurisolmu.

* Jokaiselle merkille saadaan Huffmanin koodi seuraavasti:
  * Aloita puun läpikäyminen juuresta.
  * Aina kun siirrytään lapsisolmuun, joka on vasemmalla, lisätään Huffmanin koodiin bitti 0. Kun oikealle, lisätään bitti 1.
  
* Käy läpi alkuperäinen merkkijono.
  * Määritä jokaisen merkin kohdalla merkin Huffmanin koodi.

* Kun jokaisella merkillä on Huffmanin koodi, luo alkuperäistä vastaava merkkijono käyttäen merkkejä vastaavia Huffmanin arvoja.
* Käy Huffmanin koodeista muodostuva merkkijono läpi, ja luo taulukko jossa on yhtä monta alkiota, jonka arvo on merkistä '0' ja '1' riippuen true tai false.

* Dekoodaaminen samaan tapaan, mutta aloittaen edellisen listan lopusta.

* Huffmanin koodaus on häviötön pakkausalgoritmi (lossless), joten pakattu tiedosto saadaan palautettua täysin samanlaiseksi, kuin alkuperäinen tiedosto. Tämän takia se soveltuu juuri tekstin pakkaamiseen. 
  * Kuvien pakkaamiseen käytetään usein häviöllisiä pakkausalgoritmeja.

### Tavoitteelliset aika- ja tilavaativuudet

* Prioriteettijonoon asettaminen tapahtuu aikavaativuudella O(log(n)), jossa n on alkioiden lukumäärä.
* Binäärihakupuun läpikäyminen tapahtuu aikavaativuudella O(n).
* Näin ollen koko algoritmin aikavaativuus tulisi olla O(nlog(n)).
* Binäärihakupuun läpikäymisen tilavaativuus on O(n).

### Lähteet

* [Techie Delight - Huffman coding compression algorithm](https://www.techiedelight.com/huffman-coding/)

* Jyrki Kivinen, Tietorakenteet ja algoritmit kurssimateriaali.
