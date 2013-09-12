package com.adam.ejb.client.jboss.sessionbean;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.adam.ejb.model.Person;
import com.adam.ejb.sessionbeans.stateless.AddPrefix;

public class SessionBeanClient {
	private final static Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
	static {
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	}
	
	private static AddPrefix lookupAddPrefixEJB() throws NamingException {
		final Context context = new InitialContext(jndiProperties);
		return (AddPrefix) context.lookup("ejb:/SessionBeans/AddPrefixBean!com.adam.ejb.sessionbeans.stateless.AddPrefix");		   
	}
	
    public static void testSLSB() throws NamingException {
    	AddPrefix addPrefix = lookupAddPrefixEJB();
    	Person adam = new Person();
    	adam.setName("Adam Hu");
    	System.out.println(adam);
    	Person newAdam = addPrefix.addTitle(adam, "Mr." );
    	System.out.println(adam);
    	System.out.println(newAdam);
    }
    
    public static void main(String[] args) throws NamingException {
    	testSLSB();
    }
}
