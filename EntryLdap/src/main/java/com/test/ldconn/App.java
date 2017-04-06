package com.test.ldconn;

import javax.naming.directory.*;


public class App {
    DirContext ctx = null;
    public static void main(String[] args) {
       LDAPOperations ldapaOp = new LDAPOperations();
       String lookupDn = "cn= john kumar,ou=People,dc=billaprasannakumar,dc=local";
       ldapaOp.lookUp(lookupDn);
        Attribute cn = new BasicAttribute("cn", "john kumar");
        Attribute givenName = new BasicAttribute("givenname", "john");
        Attribute sn = new BasicAttribute("sn","kumar");
        Attribute objectclass = new BasicAttribute("objectclass", "posixAccount");
        Attribute objectStructuralclass = new BasicAttribute("objectclass", "inetOrgPerson");
        Attributes attr = new BasicAttributes(true);
        attr.put(cn);
        attr.put(givenName);
        attr.put(sn);
        attr.put(objectclass);
        attr.put(objectStructuralclass);
       ldapaOp.createUSerEntry(lookupDn,attr);
       }
    }
