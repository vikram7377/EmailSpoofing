/**
 * 
 */
package edu.skct.smartinidamilter.praserFactory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Header;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * @author saseea
 *
 */
public class ReadFromFileParser implements Parser {

	@Override
	public Map<String, String> parseInput(Object input) throws MessagingException {
		Map<String, String> headerMap = new HashMap<>();
		InputStream inputStream = (InputStream)input;
		Session session = Session.getDefaultInstance(new Properties());
		MimeMessage mimeMessage = new MimeMessage(session, inputStream);
		
		mimeMessage.getAllHeaderLines();
		for (@SuppressWarnings("unchecked")
		Enumeration<Header> e = mimeMessage.getAllHeaders(); e.hasMoreElements();) {
		    Header h = e.nextElement();
		    if(headerMap.containsKey(h.getName()))
		    {
		    	headerMap.put(h.getName(), headerMap.get(h.getName())+"\r\n"+h.getValue());
		    }
		    else
		    	headerMap.put(h.getName(), h.getValue());
		    /*System.out.println(h.getName());
		    System.out.println(h.getValue());*/
		}
		return headerMap;

	}

}
