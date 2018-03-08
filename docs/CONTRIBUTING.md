
Jeśli chcesz pomóc rozwijać tę aplikację, pamiętaj o paru prostych zasadach, które tu stosujemy:
- **Pracuj na branchach** - każdą nową funkcjonalność lub poprawkę rozwijaj na branchu opartym o `develop`. Nazwa brancha powinna odpowiadać implementowanej funkcjonalności.
- **Używaj pull requestów** - gdy skończysz implementację, zrób pull request swojego brancha do developa. Dzięki temu będzie można łatwo przeanalizować zmiany, uruchomić automatyczną weryfikację oraz wprowadzić ewentualne poprawki.
- **Rób rebase** - gdy w trakcie Twojej pracy na prywatnym branchu pojawią się jakieś nowe zmiany na branchu `develop`, zrób rebase swojego brancha na szczyt `developa`, żeby pracować na najnowszej wersji aplikacji, oraz żeby własnoręcznie rozwiązać wszelkie konflikty, które Twoja zmiana wprowadza. **Pamiętaj:**, że rebase jest szczególnie ważny przed opublikowaniem pull requesta!
- **Commituj z głową** - niech każdy commit stanowi jakąś logiczną całość oraz zawiera wartościowy commit message. Używaj tylko języka angielskiego, pisz w czasie teraźniejszym i trybie rozkazującym. Więcej o tworzeniu dobrych opisów commitów przeczytasz np [tutaj](https://chris.beams.io/posts/git-commit/)
- **Pisz testy, uruchamiaj testy** - dzięki testom aplikacja jest stabilna oraz łatwiej wprowadzać złożone zmiany, gdy szybko można sprawdzić, czy wszystko nadal działa poprawnie.
- **Utrzymuj spójny styl kodu** - kod tworzony jest w oparciu o [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
