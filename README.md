# Rekolekcje

[![Build Status](https://travis-ci.org/oaza-waw/rekolekcje-api.svg?branch=master)](https://travis-ci.org/oaza-waw/rekolekcje-api)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=rekolekcje-api%3Arekolekcje-engine&metric=alert_status)](https://sonarcloud.io/dashboard/index/rekolekcje-api:rekolekcje-engine)
[![codecov](https://codecov.io/gh/oaza-waw/rekolekcje-api/branch/develop/graph/badge.svg)](https://codecov.io/gh/oaza-waw/rekolekcje-api)
[![Sputnik](https://sputnik.ci/conf/badge)](https://sputnik.ci/app#/builds/oaza-waw/rekolekcje-api)

Aplikacja do przechowywania danych uczestników rekolekcji.

**Przed wprowadzaniem zmian w repozytorium przeczytaj [Contribution Guide](docs/CONTRIBUTING.md).**

Zobacz też: [Git 101](docs/git-101.md)

## Zależności

Do kompilacji i uruchomienia aplikacji lokalnie potrzebne są następujące zależności:

- Java 8 (JDK 8)
- NodeJS (wersja 9.x)
- [Docker](https://docs.docker.com/install/linux/docker-ce/ubuntu/)
- [Docker-compose](https://docs.docker.com/compose/install/)

Jeśli chcesz uruchomić bazę danych lokalnie/nie masz Dockera, potrzebny jest:
- PostgreSQL (przynajmniej wersja 9.4). Należy stworzyć bazę o nazwie `rekolekcjedb`,
która będzie działać na porcie `localhost:5430` lub skonfigurować aplikację do połączenia z inną bazą.


## Uruchomienie

Aplikację można uruchomić w dwóch trybach:
- produkcyjnym - system budowany jest jako pojedyncza aplikacja, która łaczy się z produkcyjną bazą danych i wymaga konfiguracji kont uzytkownikow
- developerskim - możliwe jest niezależne uruchomienie aplikacji backendowej i frontendowej, oraz użycie innej bazy danych, na przykład pamieciowej lub testowej

### Tryb produkcyjny

Aby uruchomić aplikację: <br/>
Uruchom terminal i po wejściu do głównego katalogu projektu wpisz:

```$xslt
./env.sh start
```
a następnie
```$xslt
./gradlew bootRun
```


Skrypt `./env.sh start` zbuduje kontenery dockerowe z bazą danych PostreSQL wymagane do prawidłowego działania aplikacji
oraz prawidłowego wykonania się testów integracyjnych. <br/>
Produkcyjna baza danych będzie dostępna przez `localhost:5430`, a baza testowa na `localhost:5431`. <br/>
Skrypt `./gradlew bootRun` zbuduje `fat jar` i go uruchomi. <br/>
Aplikacja bedzie dostepna na porcie wyswietlonym w konsoli (`http://localhost:5000`) <br/>
Po zakończonej pracy wykonaj skrypt:
`./env.sh wipe`, co spowoduje usunięcie powstałych wcześniej kontenerów. <br/>

#### Uruchomienie z dockerem
Możliwe jest uruchomienie aplikacji w kontenerze dockerowym. <br/>
Aby to zrobić, będąc w katalogu głównym projektu wykonaj komendę:
`docker-compose up`. <br/>
Aplikacja dostępna będzie na `localhost:5000`, a jej baza na `localhost:5433`.
Po zakończonej pracy wykonaj `docker-compose down`.

### Tryb developerski


##### Uruchomienie backendu (engine)
Będąc w katalogu głównym projektu wpisz w terminalu:
```$xslt
./gradlew bootRun
```

lub w trybie developerskim, na pamięciowej bazie danych:
```$xslt
./gradlew -Dspring.profiles.active=dev bootRun
```

Backend bedzie dostepny na porcie `5000`.
Domyślną konfigurację można zmienić poprzez utworzenie pliku `application-local.yml` i nadpisanie w nim odpowiednich properties.

#### Uruchomienie frontendu (webapp):
Będąc w katalogu `./rekolekcje-webapp/src/app/` wpisz w terminalu:
```$xslt
npm start
```

Frontend będzie dostepny na porcie `4200`. <br/>

### Testy automatyczne

Testy akceptacyjne wymagają dedykowanej bazy danych. Domyślną konfigurację tej bazy można znaleźć w `application-test.yml` w module `verify`. 
Konfigurację można zmienić poprzez utworzenie pliku `application-test-local.yml` i nadpisanie w nim odpowiednich wartości.

Wszystkie testy z konsoli:
```$xslt
./gradlew verify
```
Pamiętaj, że przed wywołaniem testów jednostkowych musi zostać zbudowany
kontener dockerowy z bazą danych!  (`./enh.sh start`)

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
