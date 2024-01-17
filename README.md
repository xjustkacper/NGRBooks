# NGRBooks
9. Instrukcja konfiguracji

Pliki do pobrania:
Liberica 17 SDK - https://bell-sw.com/pages/downloads/
MySQL Workbench - https://dev.mysql.com/downloads/workbench/
MySQL Community Server - https://dev.mysql.com/downloads/mysql/
Przeglądarka internetowa - https://www.mozilla.org/en-US/firefox/new/
IDE  - https://www.jetbrains.com/idea/download/?section=windows

Krok 1: Instalacja potrzebnego oprogramowania wymienionego w “Pliki do pobrania” zgodnie z instrukcjami.
Krok 2: Uruchomienie MySQL Workbench i utworzenie nowej bazy danych poprzez przycisk “+” na stronie głównej. Connection name wpisać dowolnie, a resztę pozostawić domyślnie.
Krok 3: (Opcjonalny): Stworzyć nową Scheme poprzez “Create Schema” w okienku “Schemas”.
Krok 4: Przejść do “Server” -> “Data import”. Wybrać “Import from Self-Contained File” i wyszukać ścieżkę do pliku .sql z bazą danych. Wybrać “Default target Schema” (Jeśli krok 3 został wykonany, wybrać nowo stworzoną scheme, a jeśli nie, to wybrać “sys”). Kliknąć “Start Import”.
Krok 5: Uruchomić IntelliJ IDEA, wejść do “Project Structure” (CTRL + ALT + SHIFT + S) i wybrać SDK jako Liberica-17.
Krok 6: Wejść w ustawienia (CTRL + ALT + S), wyszukać “File Encoding” i ustawić wszędzie “UTF-8” (Pamiętać o “Default encoding for property files” na dole okna).
Krok 7: Wejść w File -> New -> Project from Exiting Sources -> Znaleźć folder źrodłowy -> Import project from external model -> Maven -> Create
Krok 8: Wyszukać w strukturze projektu “application.yml”, w datasource: zmienić username oraz password na ten, który ustawiliśmy podczas instalowania MySQL Community Server, a w URL zmienić “registration_db” na nazwe Schemy, do której zimportowaliśmy naszą bazę danych.
Krok 9: Wyszukać w strukturze projektu “NgrBooksApplication”, kliknąć prawym przyciskiem myszy i wybrać “Run ‘NgrBooksApplication’”.
Krok 10: Prześledzić terminal do momentu uruchomienia aplikacji, otworzyć przeglądarkę i wyszukać “http://localhost:9191”.
