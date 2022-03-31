package edu.skct.smartinidamilter.praserFactory;

import java.util.Map;

import javax.mail.MessagingException;

public interface Parser {

	public Map<String, String> parseInput(Object input) throws MessagingException;
}
