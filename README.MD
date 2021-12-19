1.	Запустить Docker
2.	Выполнить docker-compose.yml `docker-compose up -d`
3. Тестирование с использованием СУБД MySQL:        
3.1. Запустить SUT `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app_db -jar artifacts/aqa-shop.jar`  
3.2. В *nix системах запуск `./gradlew clean test -Dselenide.headless=true -Ddatasource.url=jdbc:mysql://localhost:3306/app_db`  
    В Windows запуск `gradlew clean test -Dselenide.headless=true -Ddatasource.url=jdbc:mysql://localhost:3306/app_db`
4.  Тестирование с использованием СУБД PostgresSQL:  
4.1. Запустить SUT `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app_db -jar artifacts/aqa-shop.jar`  
4.2. В *nix системах запуск `./gradlew clean test -Dselenide.headless=true -Ddatasource.url=jdbc:postgresql://localhost:5432/app_db`  
    В Windows запуск `gradlew clean test -Dselenide.headless=true -Ddatasource.url=jdbc:postgresql://localhost:5432/app_db`
5.  `gradlew allureReport` - генерация отчета после прохождения
    тестов
6. Остановить SUT `CTRL+C`
7. Остановить БД `docker-compose stop`, выполнить `docker-compose down`