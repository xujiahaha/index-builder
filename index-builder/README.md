# index-builder

**index-builder**, an offline service, is responsible for building forwarded and inverted index to mysql and memcached that can be used by index server.

### Requirements
- Java JDK
- Maven
- MySQL
- memcached

### Get Started
1. Configure MySQL and memcached connection info in `config.properties`.
2. Create two tables in MySQL database.
```
# Table ad
CREATE TABLE ad (adId int(11), campaignId int(11), keyWords VARCHAR(1024), bidPrice DOUBLE, price DOUBLE, thumbnail MEDIUMTEXT, description MEDIUMTEXT, brand VARCHAR(100), detailUrl MEDIUMTEXT, category VARCHAR(1024), title VARCHAR(2048), PRIMARY KEY(adId));
# Table campaign
CREATE TABLE campaign (campaignId int(11), budget DOUBLE, PRIMARY KEY(campaignId));
```
3. Build and run
```
mvn clean install
java -jar ./target/index-builder-1.0-SNAPSHOT.jar adsDataFile budgetDataFile
```
