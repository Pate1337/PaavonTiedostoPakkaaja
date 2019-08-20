* Aloitettu prioriteettijonon toteuttamista itse.
* Yritin toteuttaa sitä geneerisenä luokkana, jonka luominen onnistuisi kutsulla
```
PriorityQueue<Node> p = new PriorityQueue<>((l, r) -> l.frequency - r.frequency);
```
* Kontruktorin sain vastaanottamaan Comparator-rajapinnan, mutta insert()-metodin toteutuksessa törmäsin suuriin ongelmiin.
```
1.  public void insert(T k) {
2.    this.heapSize++;
3.    int i = this.heapSize - 1;
4.    while (i > 0 && c.compare(this.a[this.a[(i - 2) / 2]], k) > 0) {
5.      this.a[i] = this.a[this.a[(i - 2) / 2]];
6.      i = this.a[(i - 2) / 2];
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