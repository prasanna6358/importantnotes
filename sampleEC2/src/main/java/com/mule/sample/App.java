package com.mule.sample;
public class App
{
    //final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(App.class.getName());
    public static void main( String[] args )
    {
        //Ec2Client.getSpecificClasses();
        App.getHello("prasanna");

    }
    public static void getHello(String name)
        {
            System.out.println("Hello "+name);
        }
}
