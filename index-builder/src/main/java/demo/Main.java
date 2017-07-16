package demo;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

@Slf4j
public class Main {
    private static String adsDataFilePath;
    private static String campaignDataFilePath;

    public static void main(String[] args) {
        if(args.length < 2) {
            log.error("No adsDataFilePath and campaignDataFilePath found. Usage example: java -jar ./target/index-builder-1.0-SNAPSHOT.jar ads.txt budget.txt");
            System.exit(0);
        }
        adsDataFilePath = args[0];
        campaignDataFilePath = args[1];
        Properties prop = new Properties();
        InputStream configInput = null;
        try {
            configInput = new FileInputStream("config.properties");

            // load a properties file
            prop.load(configInput);

            // get the property value and print it out
            String memcachedServer = prop.getProperty("memcachedServer");
            String memcachedPortal = prop.getProperty("memcachedPortal");
            String mysqlHost = prop.getProperty("mysqlHost");
            String mysqlDb = prop.getProperty("mysqlDb");
            String mysqlUser = prop.getProperty("mysqlUser");
            String mysqlPass = prop.getProperty("mysqlPass");

            System.out.println("memcachedServer: " + memcachedServer);
            System.out.println("memcachedPortal: " + memcachedPortal);
            System.out.println("mysqlHost: " + mysqlHost);
            System.out.println("mysqlDb: " + mysqlDb);
            System.out.println("mysqlUser: " + mysqlUser);
            System.out.println("mysqlPass: " + mysqlPass);
            long startTime = System.currentTimeMillis();
            IndexBuilder indexBuilder = new IndexBuilder(memcachedServer, memcachedPortal, mysqlHost, mysqlDb, mysqlUser, mysqlPass);
            indexBuilder.init(adsDataFilePath, campaignDataFilePath);
            double timeUsed = (System.currentTimeMillis()-startTime)/1000;
            System.out.println("Takes " + timeUsed + "s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
