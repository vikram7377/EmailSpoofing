package edu.skct.smartinidamilter.spf;

import org.apache.james.jspf.executor.SPFResult;
import org.apache.james.jspf.impl.DefaultSPF;

import edu.skct.smartinidamilter.utils.SmartIndiaMilterUtils;

public class AlgorithmSpfVerification {
	
	public boolean verifySPF(String fromIP, String fromEmail, String fromDomain)
	{
		SmartIndiaMilterUtils smartIndiaMilterUtils = new SmartIndiaMilterUtils();
		DefaultSPF spf = new DefaultSPF();
		SPFResult result = spf.checkSPF(fromIP,fromEmail,fromDomain);
		System.out.println(result.getResult());
		return smartIndiaMilterUtils.parseSPFResult(result.getResult());
	}

}
