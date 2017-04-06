package com.sample.logs;

import org.apache.log4j.*;

import java.io.InputStream;
import java.util.Properties;

public class App
{
    final static Logger logger = Logger.getLogger(App.class.getName());
    public static void main( String[] args ) {
        try {
            logger.debug("simple test");
            logger.fatal("This is the  message..");
            logger.debug("test  debug");
            logger.error("error");
            System.out.println("Your logic executed successfully....");
            Properties properties = new Properties();
            String propFileName = "config.properties";
            InputStream inputStream =  App.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream!=null) {
                properties.load(inputStream);
                String bucketName = properties.getProperty("s3.bucketName");
                String secretKey = properties.getProperty("s3.secretKey");
                String accesskey = properties.getProperty("s3.accessKey");
                System.out.println(bucketName+"....."+secretKey+"......"+accesskey);
            }
            else
            {
                System.out.println("not loaded");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }
}
