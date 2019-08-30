* Jacoco lisätty.
* Testausdokumentaation kirjoittamista aloiteltu.

* Yritetty luoda jonkinsortin suorituskyky testejä.
  * Ei ole selvinnyt miten suorituskykyä kannattaisi testata...Ei vaikuta hyvältä vain testata, että algoritmin suoritukseen jollain syötteellä kuluu aikaa esim. alle 50 ms..
  * Miten testataan aikvaativuudet huomioiden?

* Perehdytty tarkemmin hajaustaulun toteutukseen.
* Kivisen tira-materiaalista jäi hyvin epäselväksi uudelleenhajautusta koskevat lauseet.
  * Siellä mainitaan, että hajaustaulun kooksi m pitää valita alkuluku joka ei ole lähellä 2:n potensseja.
  * UUdelleenhajautus tapahtuu, kun lisäyksen yhteydessä suhde n/m (n on alkioiden lukumäärä), eli täyttösuhde ylittää määritellyn kynnyksen (esim. 0.75).
  * Uudelleenhajautuksessa materiaalin mukaan, varataan tällöin hajautustaululle uusi tila, joka on 2 * m. 
    * Tässä iski epävarmuus, koska 2 * m ei ole tietenkään alkuluku, niin miten se voisi olla uusi hajatustaulun koko??
  * Tässä vielä Kivisen sanoin:
    * "kun täyttösuhde n/m ylittää asetetun kynnysarvon kynnys (esim. Javan HashMapissä 0,75), varataan hajautustaululle lisäyksen yhteydessä uusi kooltaan kaksinkertainen tila"

* Tän epäselvyyden takia joudun todennäköisesti jättämään uudelleenhajautuksen toteuttamatta. Eli teen hajautustaulusta vaan riittävän suuren heti alusta alkaen.

* Suorituskykytestaus edennyt. En tiedä edelleenkään miten aikavaativuuksia testaisi, joten toteutin enemminkin raportin, joka luodaan kun testit ajetaan. Raportissa näkyy joitakin aikoja, joita sovelluksen joissain vaiheissa kestää (Tällä hetkellä vain prioriteettijono). Erästä raporttia voi tarkkailla Toteutusdokumentissa.
  * Tätä raportin sisältöä olisi tarkoitus analysoida pseudokoodin avulla.
* Joitakin testejä myös lisäilty.

* Seuraavaksi olisi hienoa, jos suorituskykyraportin saisi luotua jollain komentorivikomennolla. Tyyliin:
```
mvn test -raport
```
* Myös paljon aikavaativuusanalysointia on luvassa ja se hajautustaulu pitäisi toteuttaa.

Tunnit: 2 + 5 + 5 = 12
