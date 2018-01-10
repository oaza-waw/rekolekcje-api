# Rekolekcje

[![Build Status](https://travis-ci.org/oaza-waw/rekolekcje-api.svg?branch=master)](https://travis-ci.org/oaza-waw/rekolekcje-api)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=rekolekcje-api:rekolekcje-engine)](https://sonarcloud.io/dashboard/index/rekolekcje-api:rekolekcje-engine)

Aplikacja do przechowywania danych uczestników rekolekcji.

## Uruchomienie

Aplikację mozna uruchomić w dwóch trybach:
- produkcyjnym - system budowany jest jako pojedyncza aplikacja, która laczy się z produkcyjna baza danych i wymaga konfiguracji kont uzytkownikow
- developerskim - mozliwe jest niezalezne uruchomienie aplikacji backendowej i frontendowej, oraz uzycie innej bazy danych, na przyklad pamieciowej lub testowej

### Tryb produkcyjny

Zaleznosci:
- Java 8 (JDK 8)
- PostgreSQL (przynajmniej wersja 9.4)

Aby uruchomić aplikację:
```$xslt
./gradlew bootRun
```

Aplikacja bedzie dostepna na porcie wyswietlonym w konsoli (`http://localhost:5000`)

### Tryb developerski

Zaleznosci:
- Java 8
- PostgreSQL (opcjonalnie)
- NodeJS

Aby uruchomic serwer (engine)
```$xslt
./gradlew -Dspring.profiles.active=dev bootRun 
```

Backend bedzie dostepny na porcie `5000`.

Aby uruchomic frontend
```$xslt
npm start
```

Frontend bedzie dostepny na porcie `4200`.

