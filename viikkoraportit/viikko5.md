* Aloitettu prioriteettijonon toteuttamista itse.
* Yritin toteuttaa sitä geneerisenä luokkana, jonka luominen onnistuisi kutsulla
```
PriorityQueue<Node> p = new PriorityQueue<>((l, r) -> l.frequency - r.frequency);
```
* Kontruktorin sain vastaanottamaan Comparator-rajapinnan, mutta insert()-metodin toteutuksessa törmäsin suuriin ongelmiin. Koko koodi on katastrofi, mutta esimerkki kuvastaa mitä haluaisin saada aikaan.
```
1.  public void insert(T k) {
2.    this.heapSize++;
3.    int i = this.heapSize - 1;
4.    while (i > 0 && c.compare(this.a[this.a[(i - 1) / 2]], k) > 0) {
5.      this.a[i] = this.a[this.a[(i - 1) / 2]];
6.      i = this.a[(i - 1) / 2];
7.    }
8.    this.a[i] = k;
9.  }
```

* Koodi esimerkissä PriorityQueue on luotu edellä mainitulla kutsulla, joten tyyppi T on Node.
* Rivillä 6 yritetään int-tyyppiseen muuttujaan i asettaa arvoksi taulukon a (this.a) arvoa, joka on myöskin tyyppiä T (Node tässä tapauksessa).
  * Tästä kääntäjä tietenkin valittaa.
* Yksinkertaisinhan olisi tietenkin muuttaa rivi 6 muotoon:
  ```
  i = this.a[(i - 2) / 2].frequency;
  ```
  * (frequency on Node-olion kenttä.)
* Mutta tämä aiheuttaisi heti virhettä, jos tyyppi T olisikin jotain muuta kuin Node, vaikkapa Integer.
* Miten ihmeessä tämmöinen on Javan PriorityQueue:ssa toteutettu?


* Todennäköisesti luon prioriteettijonon pelkästään Node-olioille, jotta saan toteutuksen joskus valmiiksi.

* Aloitettu toteuttamaan prioriteettijonoa pelkästään Node-olioille.

* Aloitin sovelluksen testaamisen samalla kun luon uutta koodia.

* Testien vuoksi jouduin ongelmiin Node-olion equals()-metodin kanssa, koska se joutuu rekursiivisesti tarkistamaan, että myös vasen ja oikea lapsi ovat yhtä suuret.
  * Tämä saatiin toteutettua.

* Määrittelydokumenttia korjailtu.

* Prioriteettijono toteutus saatu tehtyä.
  * Täytyy tehdä jotain suorituskyky vertailua Javan PriorityQueueen.
  * Tein prioriteettijonon siten, että taulukon koko tuplaantuu aina, kun tarve.
    * Saattaa hidastaa toimintaa..

* Ohjelman toimintaa paranneltu. Viime viikolla ohjelma hukkasi joitakin bittejä tiedostosta, johtuen rivinvaihto merkeistä tiedostoa lukiessa ja tiedostoon kierjoitettaiessa.
* Nyt tiedosto pysyy identtisenä alkuperäisen tiedoston kanssa.

* Kirjoitin vihdoinkin testin, joka suorittaa koko sovelluksen ja tarkastaa, että tiedostot pysyvät samoina kun ne puretaan/pakataan.
    * Testin koodi on järkyttävää, mutta siitä huolimatta hyödyllisin testi tässä vaiheessa.

* Uuden testin myötä huomasin uutena tiedostona repositoriossani tiedoston .DS_Store
  * Pelästyin, että se on joku muistiylivuodon aiheuttama katastrofi
  * Se onkin jonkin sortin datasäilö hakemiston attribuuteille Maceissa.

Tunnit: 3 + 2 + 2 + 3  + 2 = 12