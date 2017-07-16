# index-builder

**index-builder**, an offline service, is responsible for building forwarded and inverted index to mysql and memcached that can be used by index server.

### Requirements
- Java JDK
- Maven
- MySQL
- memcached

### Get Started
1. Configure MySQL and memcached connection info in `config.properties`.
2. Build and run
```
mvn clean install
java -jar ./target/index-builder-1.0-SNAPSHOT.jar adsDataFile budgetDataFile
```
