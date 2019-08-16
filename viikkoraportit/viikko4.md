* Ohjelman ydintoiminta saatu valmiiksi.
* Pakattuun tiedostoon tallennetaan nyt myös purkamiseen tarvittavat tiedot
  * Huffmanin puu ja tiedostossa esiintyvät merkit.
  * Valitsin puun tallennettavaksi tiedostoon, koska sen ansiosta sai säästettyä joitakin tavuja.
    * Puu tallennettiin muodossa "1001010", jossa "1" ilmaisee sisäsolmuja ja "0" lehtisolmuja.
    * Näin puun tallentamiseen kuluu vain puun solmujen määrän bittejä.
    * Toinen vaihtoehto olisi ollut tallentaa merkit ja niiden esiintymistiheydet, jolloin bittejä olisi tarvittu 16 * merkkien määrä * 2.
  * Jokaiselle merkille on varattu 16 bittiä, koska esim. "€" ei mahdu Javassa 8 bittiin.

* Tiedostoon kirjoittaessa huomasin, että "uusi rivi"-merkkejä ei tallennettu tiedostoon, se hoidettiin Javan System.getProperty("line.separator") -metodin avulla.
  * Tällä hetkellä tiedostoon tulee tästä johtuen 1 ylimääräinen "uusi rivi" tiedoston loppuun. Se pitäis vielä korjata.

* Käyttöliittymä luotu:
  * Kysyy ensin halutun toimenpiteen (purku vai pakkaus)
  * Sitten tiedoston polun (mikä tahansa polku)
  * Jonka jälkeen suorittaa annetun toimenpiteen.
  * Tuloksena syntyy uusi tiedosto samaan polkuun, joka on alkuperäisen tiedoston nimestä riippuen (.txt => .bin TAI .bin => .txt).

* Testailua aloiteltu.

* Tunnit: 6 + 7 + 6 = 19
