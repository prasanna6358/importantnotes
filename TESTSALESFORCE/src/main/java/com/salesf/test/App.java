package com.salesf.test;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        LoginSalesForce lsf = new LoginSalesForce();
        System.out.println(lsf.loginSalesForce());
        //LoginSalesForce.getProperties();
    }
}
