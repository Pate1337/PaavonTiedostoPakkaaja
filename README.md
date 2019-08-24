# PaavonTiedostoPakkaaja

[Määrittelydokumentti](https://github.com/Pate1337/PaavonTiedostoPakkaaja/blob/master/documentation/maarittelydokumentti.md)

[Toteutusdokumentti](https://github.com/Pate1337/PaavonTiedostoPakkaaja/blob/master/documentation/toteutusdokumentti.md)

[Testausdokumentti](https://github.com/Pate1337/PaavonTiedostoPakkaaja/blob/master/documentation/testausdokumentti.md)

[Käyttöohje](https://github.com/Pate1337/PaavonTiedostoPakkaaja/blob/master/documentation/kayttoohje.md)

## Viikkoraportit

[Viikko 1](https://github.com/Pate1337/PaavonTiedostoPakkaaja/blob/master/viikkoraportit/viikko1.md)

[Viikko 2](https://github.com/Pate1337/PaavonTiedostoPakkaaja/blob/master/viikkoraportit/viikko2.md)

[Viikko 3](https://github.com/Pate1337/PaavonTiedostoPakkaaja/blob/master/viikkoraportit/viikko3.md)

[Viikko 4](https://github.com/Pate1337/PaavonTiedostoPakkaaja/blob/master/viikkoraportit/viikko4.md)

[Viikko 5](https://github.com/Pate1337/PaavonTiedostoPakkaaja/blob/master/viikkoraportit/viikko5.md)

## Hyödyllisiä linkkejä

[Wikipedia - Tiedonpakkaus](https://fi.wikipedia.org/wiki/Tiedonpakkaus)

[Techie Delight - Huffman coding compression algorithm](https://www.techiedelight.com/huffman-coding/)

## Komentorivitoiminnot

### .jar-tiedoston luonti ja suorittaminen

Komento
```
mvn package
```
generoi hakemistoon target suoritettavan jar-tiedoston Tiedostopakkaaja-1.0-SNAPSHOT.jar

.jar-tiedoston suorittaminen onnistuu juurihakemistosta komennolla
```
java -jar target/Tiedostopakkaaja-1.0-SNAPSHOT.jar 
```

### JavaDocin luominen

JavaDoc generoidaan hakemistossa /Tiedostopakkaaja komennolla
```
mvn javadoc:javadoc
```

Javadocia voi tarkastella selaimella osoitteessa /target/site/apidocs.

### Testaus

Testien ajaminen onnistuu hakemistossa /Tiedostopakkaaja komennolla
```
mvn test
```

Testikattavuusraportin voi luoda komennolla
```
mvn test jacoco:report
```

Testikattavuusraporttia pääsee tarkastelemaan selaimella osoitteessa /target/site/jacoco/index.html



