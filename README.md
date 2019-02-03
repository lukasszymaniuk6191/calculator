# calculator

## Uruchamianie aplikacji w termianalu
### -należy pobrać aplikację 
### -w termianalu należy przejść do folderu calculator
### -aby uruchomić aplikację należy wpisać w termianalu "mvn spring-boot:run" 
### -w folderze resources znajdują się trzy pliki z poleceniami do obliczeń (domyślnie odczytywanym plikiem w aplication.properties jest file2.txt) można wybrać konkretny plik za pomocą polecenia  "mvn spring-boot:run -Dspring-boot.run.arguments=src/main/resources/file1.txt"

## Uruchamianie aplikacji w śrowdowisku uruchomieniowym
### - należy zaimportować projekt
### - w application.properties można ustawić z którego pliku dane będą odczytywane
### - uruchomienie aplikacji

## Uruchamianie testów jednostkowych
### - tesy jednostkowe możne uruchomić poleceniem "mvn test -DtestGrou=com/storware/calculator/components"



