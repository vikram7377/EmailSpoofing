/**
 * 
 */
package edu.skct.smartinidamilter.praser;

import java.io.InputStream;
import java.util.Map;

import javax.mail.MessagingException;

import edu.skct.smartindiamilter.smartspoof.SmartSpoofDetection;
import edu.skct.smartinidamilter.praserFactory.Parser;
import edu.skct.smartinidamilter.praserFactory.ReadFromFileParser;
import edu.skct.smartinidamilter.utils.SmartIndiaMilterUtils;

/**
 * @author saseea
 *
 */
public class ParserMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Parser parser = new ReadFromFileParser();
		try {
			String file = "src/sample1.txt";
			
			//To parse the MIME Message
			SmartIndiaMilterUtils smartIndiaMilterUtils = new SmartIndiaMilterUtils();
			InputStream messageInputStream = smartIndiaMilterUtils.createInputStream(file);
			Map<String, String> resultMap = parser.parseInput(messageInputStream);
			
			
			//Reverse DNS Example
			/*
			System.out.println(smartIndiaMilterUtils.reverseDnsLookup("2607:f8b0:4001:c06::241"));*/
			
			// DKIM Verification Test
			/*AlgorithmDkimVerification dkimVerification = new AlgorithmDkimVerification();
			dkimVerification.verifyDkim(parserMain.createInputStream(file));*/

			//Retrieving Individual Values
			/*System.out.println(resultMap.get("X-Received"));
			System.out.println(resultMap.get("Received"));
			System.out.println(resultMap.get("Received-SPF"));
			*/
			SmartSpoofDetection spoofDetection = new SmartSpoofDetection();
			spoofDetection.isSpoofed(resultMap,messageInputStream);
			//Iteration Sample
			/*for(Map.Entry<String, String> entry : resultMap.entrySet())
			{
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
				System.out.println();
				System.out.println();
			}*/
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	
	

}
