1.	Запустить Docker
2.	Выполнить docker-compose.yml `docker-compose up`
3.	Запустить SUT `java -jar artifacts/aqa-shop.jar`
4.	В *nix системах запуск `./gradlew clean test -Dselenide.headless=true`  
    В Windows запуск `gradlew clean test -Dselenide.headless=true`
5.  `gradlew allureReport` - генерация отчета после прохождения
    тестов
6. Остановить SUT
7. Остановить БД, выполнить `docker-compose down`