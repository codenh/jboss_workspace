package com.adam.ejb.client.jboss.sessionbean;

import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    
    /*
     *  Jboss will create more than one stateless session bean
     *  and there is no guarantee that every time the client will
     *  get the same bean even through the same proxy interface.
     */
    public static void testSLSB2() throws NamingException {
    	Runnable task = new Runnable() {
    		@Override
    		public void run() {
    			try {
    				AddPrefix addPrefix = lookupAddPrefixEJB();
    				for (int i = 0; i < 50; i++) {
    					Person adam = new Person();
    					adam.setName("Adam Hu");
    					Person newAdam = addPrefix.addTitle(adam, "Mr." );
    					System.out.println(Thread.currentThread() + ": " + newAdam);
    				}
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	};
    	
    	ExecutorService pool = Executors.newFixedThreadPool(5);
    	for (int i = 0; i < 50; i++) {
    		pool.execute(task);
    	}
    	pool.shutdown();
    }
    
    public static void main(String[] args) throws NamingException {
    	System.out.println("test 2");
    	testSLSB2();
    }
}
