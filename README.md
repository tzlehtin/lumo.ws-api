# Oman Lumo.ws-adapterin toteutusopas

Tämä opas neuvoo, miten voit kehittää ja integroida oman, räätälöidyn adapterin Lumo.ws-alustaan. Adapterit ovat Lumo.ws:n ydin: ne toimivat siltana tekoälyn ja ulkoisten järjestelmien (kuten chatin, sähköpostin tai omien taustajärjestelmiesi) välillä.

## 1. Edellytykset

Ennen kuin aloitat, varmista, että sinulla on asennettuna seuraavat työkalut:

*   **Java Development Kit (JDK), versio 23** tai uudempi.
*   **Apache Maven** projektien hallintaan ja rakentamiseen.
*   **Git** versionhallintaan.

## 2. Vaihe 1: Hae ja asenna Lumo.ws API -kirjasto

Jotta voit kehittää adapteria, tarvitset `lumo-api`-kirjaston, joka sisältää kaikki tarvittavat rajapinnat ja tietomallit (kuten `Adapter` ja `ChatMessage`).

**1. Kloonaa API-projekti GitHubista:**

```bash
git clone https://github.com/tzlehtin/lumo.ws-api.git
```

**2. Asenna kirjasto paikalliseen Maven-repositoryysi:**

Siirry kloonattuun hakemistoon ja aja seuraava komento. Tämä kääntää kirjaston ja asentaa sen paikalliseen `.m2`-varastoosi, jolloin voit käyttää sitä muissa projekteissasi.

```bash
cd lumo.ws-api
mvn clean install
```

## 3. Vaihe 2: Luo uusi Maven-projekti adapterillesi

Luo uusi, tyhjä Maven-projekti omalle adapterillesi. Tässä on esimerkki `pom.xml`-tiedostosta, jonka voit ottaa pohjaksi.

**`pom.xml`**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.mycompany</groupId>
    <artifactId>my-echo-adapter</artifactId>
    <version>1.0.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>23</maven.compiler.source>
        <maven.compiler.target>23</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- Riippuvuus Lumo.ws API -kirjastoon -->
        <dependency>
            <groupId>ws.lumo</groupId>
            <artifactId>lumo-api</artifactId>
            <version>0.9.7-SNAPSHOT</version>
            <!-- 
                Skooppi 'provided' on tärkeä, koska pääsovellus (lumo-service)
                tarjoaa tämän riippuvuuden ajonaikaisesti.
            -->
            <scope>provided</scope>
        </dependency>
    </dependencies>

</project>
```

## 4. Vaihe 3: Toteuta `Adapter`-rajapinta

Luo uusi Java-luokka, joka toteuttaa `ws.lumo.api.Adapter`-rajapinnan. Tässä on yksinkertainen esimerkki "Echo Adapterista", joka ei tee muuta kuin kirjaa lokiin sille annetut työkalukutsut.

**`src/main/java/com/mycompany/adapter/EchoAdapter.java`**
```java
package com.mycompany.adapter;

import ws.lumo.api.Adapter;
import ws.lumo.api.ToolSpecification;
import ws.lumo.api.provider.AdapterConfig;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Yksinkertainen esimerkkiadapteri, joka ainoastaan tulostaa sille annetut käskyt.
 */
public class EchoAdapter implements Adapter {

    private final AdapterConfig config;

    public EchoAdapter(AdapterConfig config) {
        this.config = config;
    }

    @Override
    public String getAdapterId() {
        // Palauttaa adapterin uniikin ID:n, joka on määritelty konfiguraatiossa.
        return this.config.adapterId();
    }

    @Override
    public int getContextLength() {
        // Palauttaa keskusteluhistorian pituuden konfiguraatiosta.
        return this.config.contextLength() != null ? this.config.contextLength() : 7;
    }

    @Override
    public List<ToolSpecification> getToolSpecifications() {
        // Tämä adapteri ei tarjoa työkaluja tekoälylle, joten palautetaan tyhjä lista.
        return Collections.emptyList();
    }

    @Override
    public String executeTool(String toolName, Map<String, Object> arguments) {
        // Tämä metodi suoritettaisiin, jos tekoäly kutsuisi työkalua.
        // Nyt vain kirjaamme kutsun ja palautamme viestin.
        System.out.println("Executed tool: " + toolName + " with arguments: " + arguments);
        return "Tool '" + toolName + "' executed successfully by EchoAdapter.";
    }
}
```

## 5. Vaihe 4: Paketoi ja ota käyttöön

Kun olet toteuttanut oman adapterisi, sinun tulee paketoida se JAR-tiedostoksi (`mvn package`) ja lisätä se `lumo-service`-pääsovelluksen riippuvuudeksi. Tämän jälkeen sinun tulee vielä opettaa `AdapterLifecycleService` tunnistamaan ja alustamaan uusi adapterityyppisi.

## 6. Vaihe 5: Konfiguroi adapteri Lumo.ws:n hallintapaneelissa

Lopuksi voit luoda hallintapaneelissa uuden adapterin, antaa sille oman, uniikin tyyppisi (esim. `ECHO_ADAPTER`) ja konfiguroida sen asetukset. Kun `lumo-service` käynnistyy, se löytää ja alustaa uuden adapterisi automaattisesti.