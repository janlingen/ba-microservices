# Requirements zum installieren:

- JDK 17 (java —version)
- Node 20+ (node —version), NPM 10+ (npm —version)
- Docker und Docker CLI (docker --version)

# Reihenfolge zum starten der Services und des Frontends (jeweils nahezu identisch bei ecom-synchronous und ecom-asynchronous):

bei ecom-asynchronous ist die asynchrone Kommunikation implementiert zwischen:

- user-service und konto-service
- bestell-service, inventar-service und bezahl-service

0. nur bei ecom-asynchronous muss einem im root-Verzeichnis `docker compose up` gemacht werden um die Apache Kafka Instanz zu starten

1. discovery-server:
   - ./gradlew bootRun
2. produkt-service:
   - docker compose up
   - ./gradlew bootRun
3. inventar-service:
   - docker compose up
   - ./gradlew bootRun
4. bestell-service:
   - docker compose up
   - ./gradlew bootRun
5. bezahl-service:
   - docker compose up
   - ./gradlew bootRun
6. mitarbeiter-service:
   - docker compose up
   - ./gradlew bootRun
7. user-service:
   - docker compose up
   - ./gradlew bootRun
8. zustell-service:
   - docker compose up
   - ./gradlew bootRun
9. api-service:
   - ./gradlew bootRun
10. frontend:
    - npm install
    - ng serve

# Test Credentials Mitarbeiter Frontend (localhost:4200)

Username: testMit
Passwort: test
