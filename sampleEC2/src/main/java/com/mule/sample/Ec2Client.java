package com.mule.sample;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import org.apache.log4j.Logger;

public class Ec2Client {
    AmazonEC2 ec2Client;
    final static Logger logger = org.apache.log4j.Logger.getLogger(App.class.getName());

    public  Ec2Client()
    {

       AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard().withRegion("us-west-2").withCredentials(new AWSStaticCredentialsProvider(new MCredentialsprovider().getCredentials())).build();
       if(ec2Client!=null)
       {
           logger.debug("instance has created");
       }
    }
    public void createGroup() {
        CreateSecurityGroupRequest sgRequest = new CreateSecurityGroupRequest();
        sgRequest.withGroupName("MuleSecurityGroup").withDescription("mule security group with description");
        ec2Client.createSecurityGroup(sgRequest);
    }

    public static Map<String, Object> getClassNamesFromJar(String jarfilepath) {
        List listofClasses = new ArrayList<String>();
        Map<String, Object> crunchifyObject = new HashMap<String, Object>();
        try {
            JarInputStream JarFile = new JarInputStream(new FileInputStream(jarfilepath));
            JarEntry jarEntries;
            while (true) {
                jarEntries = JarFile.getNextJarEntry();
                if (jarEntries == null) {
                    break;
                }
                if ((jarEntries.getName().endsWith(".class"))) {
                    String className = jarEntries.getName().replaceAll("/", "\\.");
                    String myClass = className.substring(0, className.lastIndexOf('.'));
                    listofClasses.add(myClass);
                }
            }
            crunchifyObject.put("List of Class", listofClasses);
        } catch (Exception e) {
            logger.debug("Oops.. Encounter an issue while parsing jar" + e.toString());
        }
        return crunchifyObject;
    }

    public static void getSpecificClasses() {
        try {

            Map<String, Object> myList = Ec2Client.getClassNamesFromJar("/home/prasanna/Downloads/aws-java-sdk-ec2-1.11.119.jar");
            for (Map.Entry<String, Object> entry : myList.entrySet()) {
                ArrayList list = (ArrayList) entry.getValue();
                for (Object item : list) {
                    if (item.toString().contains("com.amazonaws.services.ec2.AmazonEC2")) ;
                    {
                        Class cls = Class.forName("com.amazonaws.services.ec2.AmazonEC2");
                        Method m[] = cls.getMethods();
                        for(int i = 0; i < m.length; i++) {
                            Method method = m[i];
                            Class<?>[] parameters= method.getParameterTypes();
                            logger.debug("no of parameters in "+method.getName()+ " :"+parameters.length);
                            for(Class param:parameters)
                            {
                                logger.info(param.getName());
                            }
                            Class<?> returnType = method.getReturnType();
                            logger.debug("the return type fo the method "+method.getName()+ " is: "+returnType.getName());
                            System.out.println();
                            }
                        }
                    }

                }
        }
        catch(Exception ex)
            {
                logger.error(ex.getMessage().toString());
            }
    }
}

