package com.mule.sample;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;

import java.io.InputStream;
import java.util.Properties;
public class MCredentialsprovider implements AWSCredentialsProvider {

    public AWSCredentials getCredentials()
    {
        try
        {
            Properties properties = new Properties();
            InputStream inputStream = MCredentialsprovider.class.getClassLoader().getResourceAsStream("config.properties");
            if(inputStream!=null)
            {
                properties.load(inputStream);
                AWSCredentials credentials = new BasicAWSCredentials(properties.getProperty("ec2.accesskey"),properties.getProperty("ec2.secretkey"));
                System.out.println("authenticated");
                return credentials;
            }
            else
                System.out.println("properties are not loaded");
        }
        catch (Exception ex)
        {
            System.out.println("exception "+ex.getMessage().toString());
        }
        return  null;
    }
    public void refresh()
    {

    }
}
