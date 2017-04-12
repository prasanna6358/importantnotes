package com.salesf.test;

import com.sforce.soap.partner.*;
import com.sforce.soap.partner.Error;
import com.sforce.soap.partner.sobject.*;
import com.sforce.ws.ConnectorConfig;
import com.sforce.ws.ConnectionException;

import java.io.*;
import java.util.Properties;

public class LoginSalesForce {
     private PartnerConnection partnerConnection = null;
     public static Properties getProperties()
     {
         try{
             Properties properties= new Properties();
             InputStream is = App.class.getClassLoader().getResourceAsStream("config.properties");
             if(is!=null) {
                 properties.load(is);
                 System.out.println(properties.getProperty("userName"));
             }
             else{
                 System.out.println("config.properties is not loaded");
             }
             return  properties;
         }
         catch(Exception ex)
         {
             System.out.println(ex.getMessage().toString());
         }
         return  null;
     }

     public boolean loginSalesForce()
     {
         try {
             Properties properties = getProperties();
             boolean success = false;
             ConnectorConfig config = new ConnectorConfig();
             config.setUsername(properties.getProperty("userName"));
             config.setPassword(properties.getProperty("password"));
             config.setAuthEndpoint(properties.getProperty("authEndPoint"));
             config.setTraceMessage(true);
             config.setPrettyPrintXml(true);
             config.setTraceFile("config.txt");
             partnerConnection = new PartnerConnection(config);
             success = true;
             return  success;
         }
         catch(Exception ex)
         {
             System.out.println(ex.getMessage().toString());
         }
         return false;
     }
     public void queryLeads()
     {
         try {

         }
         catch(Exception ex){
             System.out.println(ex.getMessage().toString());
         }
     }
}
