package com.sample.ldifbulkentry;

public class App 
{
   public static void main(String[] args)
   {
       LDAPOperations operations = new LDAPOperations();
       String dn="cn=anil kumar,ou=People,dc=billaprasannakumar,dc=local";
       operations.lookUp(dn);
       /*creating userEntry,group Entry, Organization Entry */
       //operations.createUSerEntry(here we have to pass EntryDn, pass Attributes for the Enrty);
       operations.loadLdifFileSpring();

   }

}
