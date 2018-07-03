# Rekolekcje

[![Build Status](https://travis-ci.org/oaza-waw/rekolekcje-api.svg?branch=master)](https://travis-ci.org/oaza-waw/rekolekcje-api)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=rekolekcje-api%3Arekolekcje-engine&metric=alert_status)](https://sonarcloud.io/dashboard/index/rekolekcje-api:rekolekcje-engine)
[![codecov](https://codecov.io/gh/oaza-waw/rekolekcje-api/branch/develop/graph/badge.svg)](https://codecov.io/gh/oaza-waw/rekolekcje-api)

Aplikacja do przechowywania danych uczestników rekolekcji.

**Przed wprowadzaniem zmian w repozytorium przeczytaj [Contribution Guide](docs/CONTRIBUTING.md).**

## Zależności

Do kompilacji i uruchomienia aplikacji lokalnie potrzebne są następujące zależności:

- Java 8 (JDK 8)
- NodeJS (wersja 9.x)
- PostgreSQL (przynajmniej wersja 9.4). Należy stworzyć bazę o nazwie `rekolekcjedb`, lub skonfigurować aplikację do połączenia z inną bazą.


## Uruchomienie

Aplikację można uruchomić w dwóch trybach:
- produkcyjnym - system budowany jest jako pojedyncza aplikacja, która łaczy się z produkcyjną bazą danych i wymaga konfiguracji kont uzytkownikow
- developerskim - możliwe jest niezależne uruchomienie aplikacji backendowej i frontendowej, oraz użycie innej bazy danych, na przykład pamieciowej lub testowej

### Tryb produkcyjny

Aby uruchomić aplikację:
```$xslt
./gradlew bootRun
```

Aplikacja bedzie dostepna na porcie wyswietlonym w konsoli (`http://localhost:5000`)

### Tryb developerski

Aby uruchomic serwer (engine)
```$xslt
./gradlew bootRun
```

lub w trybie developerskim, na pamięciowej bazie danych:
```$xslt
./gradlew -Dspring.profiles.active=dev bootRun
```

Backend bedzie dostepny na porcie `5000`.
Domyślną konfigurację można zmienić poprzez utworzenie pliku `application-local.yml` i nadpisanie w nim odpowiednich properties.

Aby uruchomic frontend
```$xslt
npm start
```

Frontend bedzie dostepny na porcie `4200`.

### Testy automatyczne

Testy akceptacyjne wymagają dedykowanej bazy danych. Domyślną konfigurację tej bazy można znaleźć w `application-test.yml` w module `verify`. 
Konfigurację można zmienić poprzez utworzenie pliku `application-test-local.yml` i nadpisanie w nim odpowiednich wartości.

Wszystkie testy z konsoli:
```$xslt
./gradlew verify
```

Testy jednostkowe:
```$xslt
./gradlew test
```

Testy webowe jednostkowe (uruchamiane z modulu `rekolekcje-webapp`):
```$xslt
ng test
```

## Import projektu do IDE
Jedną z zależności jest [Project Lombok](https://projectlombok.org/). Ten procesor adnotacji wymaga instalacji pluginu *Lombok* w IntelliJ, oraz włączenia przetwarzania adnotacji:
`Settings -> Build, execution, deployment -> Compiler -> Enable annotation processing`.
