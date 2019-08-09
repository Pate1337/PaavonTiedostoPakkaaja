* Huffmanin algoritmi toteutettu käyttäen Javan valmiita tietorakenteita.
* Koodia jäsennelty omiin luokkiinsa.
* Bitteihin liittyviä ominaisuuksia:
  * Käsitelty tapaukset, joissa Huffmanin algoritmin luoma bittijonon pituus ei ole jaollinen 8:lla.
  * Viimeiseksi tavuksi tiedostoon laitetaan aina viimeistä muutettua bittiä vastaava indeksi. Näin purettaessa pakattua tiedostoa, tiedetään mihin indeksiin tiedoston viimeinen tavu päättyy.
  * Merkkijonot, joissa on vain yksi merkki tai joissa on vain samaa merkkiä (esim. "aaaaa").
    * Näissä tapauksissa Huffman palautttaa vain merkkejä vastaavan määrän 0 bittejä.
* Sovellus toimii nyt siten, että se pakkaa annetun merkkijonon binääritiedostoon ja välittömästi lukee ja purkaa binääritiedoston takaisin alkuperäiseksi merkkijonoksi.

* Ongelmia:
  * (KORJATTU) Tällä hetkellä decoodaaminen ei vielä toimi kunnolla. Merkkijonot joissa viimeinen merkki esiintyy useamman kerran peräkkäin, jättää viimeisen merkin läpikäymättä.
    * Esim. merkkijono "aaabbbaaa" palautuu binääritiedostosta merkkijonona "aaabbbaa" (viimeinen merkki puuttuu). Tämä johtuu decoodaamisvaiheesta.
  * Miten saan tallennettua tiedostoon HashMapin, joka ilmaisee jokaista merkkiä vastaavan esiintymistiheyden? Pitääkö tämäkin tallentaa bitteinä samaan tiedostoon?
    * Se on välttämätön binääritiedoston dekoodaamisen kannalta.

* Seuraavaksi alan kirjoittamaan testejä ja javadoccia.
* Sen jälkeen toteutan kunnollisen käyttöliittymän.

* Javadoccia jo aloiteltu.
    
* Tunnit: 6 + 4 + 2 = 12
