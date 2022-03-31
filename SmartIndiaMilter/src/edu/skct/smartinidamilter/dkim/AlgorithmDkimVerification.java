package edu.skct.smartinidamilter.dkim;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.james.jdkim.DKIMVerifier;
import org.apache.james.jdkim.api.SignatureRecord;
import org.apache.james.jdkim.exceptions.FailException;

public class AlgorithmDkimVerification {
	
	public boolean verifyDkim(InputStream messageStream)
	{
		DKIMVerifier dkimVerifier = new DKIMVerifier();
		try {
			List<SignatureRecord> recordsList = dkimVerifier.verify(messageStream);
			return (null!=recordsList && recordsList.size()>0) ? true : false;
		} catch (IOException | FailException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
