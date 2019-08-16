# Käyttöohje

### Ohjelman suorittaminen

* Aloita ohjelman suorittaminen luomalla ajettava jar-tiedosto.
  * Mene hakemistoon /Tiedostopakkaaja
  * Suorita komento
  ```
  mvn package
  ```
  * Tietokoneellesi tulee siis olla asennettuna Maven
    * Tässä on hyvä video mikäli käytössäsi on Mac https://www.youtube.com/watch?v=j0OnSAP-KtU
* Aja jar tiedosto hakemistossa /Tiedostopakkaaja komennolla
```
java -jar target/Tiedostopakkaaja-1.0-SNAPSHOT.jar
```

### Ohjelman käyttäminen

* Ohjelma kysyy ensimmäiseksi käyttäjältä toimenpidettä
```
(1) Pakkaa tekstitiedosto binääritiedostoksi
(2) Pura binääritiedosto tekstitiedostoksi
```
* Valitse toimenpide painamalla näppäimistöllä 1 tai 2 ja paina lopuksi enter
* Seuraavaksi ohjelma kysyy tiedoston polkua, jolle purku tai pakkaaminen halutaan suorittaa.
  * Polku on oltava absoluuttinen, eli esimerkiksi Macilla
  ```
  /Users/paavohemmo/anypath/text.txt
  ```
  tai Windowsilla
  ```
  C:\paavohemmo\anypath\text.txt
  ```
  * Ohjelma hyväksyy vain .txt-muotoisia tiedostoja pakkaamiseen ja .bin-muotoisia tiedostoja pakkaamiseen.

* Kun käyttäjä on syöttänyt hyväksyttävän tiedostopolun, suoritetaan haluttu toimenpide.
* Ohjelma luo samaan sijaintiin uuden tiedoston, jonka pääte on pakkaamisen tuloksena .bin ja purkamisen tuloksena .txt
* Lopuksi ohjelma tulostaa pakkaamisella saavutetun tilan säästön.
