package edu.skct.smartinidamilter.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import edu.skct.smartindiamilter.model.Constants;
import edu.skct.smartindiamilter.model.FromEmailAddressModel;
import edu.skct.smartindiamilter.model.ReceivedFromModel;

public class SmartIndiaMilterUtils 
{
	
	public String reverseDnsLookup(String ipAddress)
	{
		try {
			InetAddress inetAddress = InetAddress.getByName(ipAddress);
			String canonicalName = inetAddress.getCanonicalHostName();
			return (canonicalName.equals(ipAddress))?Constants.RESULT_FAIL:canonicalName;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ReceivedFromModel> parseReceivedFields(String receivedContent) {
		String[] lines = receivedContent.split(Constants.NEWLINE);
		List<ReceivedFromModel> granular = new ArrayList<>();
		for(String line : lines)
		{
			if(line.startsWith("from"))
			{
				ReceivedFromModel data = new ReceivedFromModel();
				String receivedServer = line.substring(line.indexOf("from ")+5,line.indexOf("("));
				data.setReceivedServer(receivedServer);
				
				String receivedIpAddress = line.substring(line.indexOf("(")+1, line.indexOf(")"));
				if(receivedIpAddress.contains("[")&&receivedIpAddress.contains("]"))
					receivedIpAddress = receivedIpAddress.substring(receivedIpAddress.indexOf("[")+1,receivedIpAddress.indexOf("]"));
				data.setReceivedIpAddress(receivedIpAddress);
				data.setReverseDns(reverseDnsLookup(receivedIpAddress));
				granular.add(data);
			}
		}
		return granular;
	}
	
	public FromEmailAddressModel parseFromEmail(String fromEmail) {
		FromEmailAddressModel model = new FromEmailAddressModel();
		if(fromEmail.contains("<")&&fromEmail.contains(">"))
		{
			model.setFromName(fromEmail.substring(0,fromEmail.indexOf("<")));
			model.setFromEmail(fromEmail.substring(fromEmail.indexOf("<")+1, fromEmail.indexOf(">")));
			model.setFromDomain(model.getFromEmail().split("@")[1]);
			return model;
		}
		else
		{
			model.setFromName(fromEmail);
			model.setFromEmail(fromEmail);
			model.setFromDomain(model.getFromEmail().split("@")[1]);
			return model;
		}
	}
	
	public InputStream createInputStream(String filePath)
	{
		File file = new File(String.valueOf(filePath));
		BufferedReader bufferedReader;
		String content = "";
		String line = "";
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			while (null != (line = bufferedReader.readLine())) {
				content += line+Constants.NEWLINE;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStream inputStream = new ByteArrayInputStream(content.getBytes());
		return inputStream;
	}
	
	public boolean parseSPFResult(String result){
		return (result.trim()
				.equals(Constants.SPF_PASS)) 
				? true 
				: false;
	}
	
}
