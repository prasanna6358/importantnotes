package com.test.ldconn;


import com.sun.jndi.ldap.LdapCtx;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LDAPOperations {
    Hashtable<String, String> environment;
    private static DirContext ctx;
    private static final String url = "ldap://localhost";
    private static final String connectionType = "simple";
    private static final String adminDN = "cn=admin,dc=billaprasannakumar,dc=local";
    private static final String password = "pr@sanna6358";

    public LDAPOperations()
    {
        environment = new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, url);
        environment.put(Context.OBJECT_FACTORIES,"java.naming.factory.object");
        environment.put(Context.SECURITY_AUTHENTICATION, connectionType);
        environment.put(Context.SECURITY_PRINCIPAL, adminDN);
        environment.put(Context.SECURITY_CREDENTIALS, password);
    }

    public void connection()
    {
        try {
            ctx = new InitialDirContext(environment);
            System.out.println("Connection is created");
        }
        catch(NamingException exc) {
            System.out.println(exc.getResolvedName().toString());
            System.err.println(exc.getMessage().toString());
        }
    }
    public void lookUp(String lookupDn)
    {
        try {
            ctx = new InitialDirContext(environment);
            Object obj= ctx.lookup(lookupDn);
            LdapCtx ldapctx = (LdapCtx)ctx.lookup(lookupDn);
            System.out.println(obj.getClass());
            System.out.println(obj.toString());
        }
        catch(NamingException exc) {
            System.out.println(exc.getResolvedName().toString());
            System.err.println(exc.getMessage().toString());
        }
    }
    public void createUSerEntry(String entryDN,Attributes attributes)
    {
        try {
            ctx = new InitialDirContext(environment);
            ctx.createSubcontext(entryDN,attributes);
        }
        catch (NamingException exc)
        {
            System.out.println(exc.getResolvedName().toString());
            System.err.println(exc.getMessage().toString());
        }
    }
}
