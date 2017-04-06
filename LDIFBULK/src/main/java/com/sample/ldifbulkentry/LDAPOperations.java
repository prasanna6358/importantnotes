package com.sample.ldifbulkentry;


import com.sun.jndi.ldap.LdapCtx;
import org.springframework.ldap.core.LdapAttributes;
import org.springframework.ldap.ldif.parser.LdifParser;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.io.File;
import java.util.Hashtable;

public class LDAPOperations {
    public static Hashtable<String, String> environment;
    private static DirContext ctx;
    private static final String url = "ldap://localhost";
    private static final String connectionType = "simple";
    private static final String adminDN = "cn=admin,dc=billaprasannakumar,dc=local";
    private static final String password = "pr@sanna6358";

    public LDAPOperations() {
        environment = new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, url);
        environment.put(Context.OBJECT_FACTORIES, "java.naming.factory.object");
        environment.put(Context.SECURITY_AUTHENTICATION, connectionType);
        environment.put(Context.SECURITY_PRINCIPAL, adminDN);
        environment.put(Context.SECURITY_CREDENTIALS, password);
        connection();
    }

    public static void connection() {
        try {
            ctx = new InitialDirContext(environment);
            System.out.println("Connection is created");
        } catch (NamingException exc) {
            System.out.println(exc.getResolvedName().toString());
            System.err.println(exc.getMessage().toString());
        }
    }

    /*simple lookup operation using JNDI */
    public void lookUp(String lookupDn) {
        try {
            Object obj = ctx.lookup(lookupDn);
            LdapCtx ldapctx = (LdapCtx) ctx.lookup(lookupDn);
            System.out.println(obj.getClass());
            System.out.println(obj.toString());
            System.out.println("entry exist in server");
        } catch (NamingException exc) {
            System.out.println(exc.getResolvedName().toString());
            System.err.println(exc.getMessage().toString());
        }
    }

    /* creating simple user entry to the LDAP server
     * EntryDn is the Dn and is of String Type
     * Attributes we have to add when creating an Entry*/
    public void createUSerEntry(String entryDN, Attributes attributes) {
        try {
            ctx.createSubcontext(entryDN, attributes);
        } catch (NamingException exc) {
            System.out.println(exc.getResolvedName().toString());
            System.err.println(exc.getMessage().toString());
        }
    }

    /*Loading the LDIF file using org.springframework.ldap
    here LdifParser,LdapAttributes belongs to  org.springframework.ldap
    loading the LDIF file using Ldifparser class from that we are getting the LdapAttributes
     from the LdapAttributes I'm getting DN
     using that DN and LdapAttributes, Adding entries with Jndi API by calling createSubcontext method
     maven Repository
       <groupId>org.springframework.ldap</groupId>
      <artifactId>spring-ldap-ldif-core</artifactId>
      <version>2.0.4.RELEASE</version>
    </dependency>
    using org.springframework.ldap loading LDAP .ldif file
    */
    public void loadLdifFileSpring() {
        try {
            LdifParser parser = new LdifParser(new File("/home/prasanna/testldif.ldif"));
            parser.open();
            while (parser.hasMoreRecords()) {
                LdapAttributes attributes = parser.getRecord();
                System.out.println(attributes);
                String entryDn = attributes.getName().toString();
                ctx.createSubcontext(entryDn, attributes);
            }
            parser.close();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage().toString());
        }
    }

    /* using org.apache.directory.api
     * maven Repository
      <dependency>
      <groupId>org.apache.directory.api</groupId>
      <artifactId>api-all</artifactId>
      <version>1.0.0-M33</version>
    </dependency>
    using LdapNetworkConnection getting LdapConnection class object binding LdapConnection class object with DN and Password
    Reading LDIF file by using LdifReader from that we are getting entries,
    and adding those entries directly to the ldap server by using connection object by method add using foreach loop

     NOTE:here I have not added the dependency in pom.xml if you want you can add

    public void loadLdifFile() {
        String filepath = "/home/prasanna/testldif.ldif";
        try {
            LdapConnection connection = new LdapNetworkConnection("localhost", 389);
            connection.bind(adminDN, password);
            LdifReader reader = new LdifReader(new File(filepath));
            for (LdifEntry entry : reader) {
                connection.add(entry.getEntry());
            }
            connection.unBind();
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    } */
}
