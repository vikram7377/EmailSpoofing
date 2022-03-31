package edu.skct.smartindiamilter.smartspoof;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import edu.skct.smartindiamilter.model.Constants;
import edu.skct.smartindiamilter.model.FromEmailAddressModel;
import edu.skct.smartindiamilter.model.ReceivedFromModel;
import edu.skct.smartinidamilter.dkim.AlgorithmDkimVerification;
import edu.skct.smartinidamilter.spf.AlgorithmSpfVerification;
import edu.skct.smartinidamilter.utils.SmartIndiaMilterUtils;

public class SmartSpoofDetection {
	
	private FromEmailAddressModel fromEmailAddressModel;
	private List<ReceivedFromModel> receivedFromModels;
	
	public boolean isSpoofed(Map<String,String> headers, InputStream messageInputStream)
	{
		init(headers);
		
		
		return (isFromDomainMatch() 
				&& isReverseDnsMatch()
				&& isSPFVerified()
				&& isDKIMVerified(messageInputStream))
				? true
				: false;
	}
	
	
	
	private void init(Map<String, String> headers)
	{
		SmartIndiaMilterUtils smartIndiaMilterUtils = new SmartIndiaMilterUtils();
		this.fromEmailAddressModel = smartIndiaMilterUtils.parseFromEmail(headers.get(Constants.HEADER_FROM));
		this.receivedFromModels = smartIndiaMilterUtils.parseReceivedFields(headers.get(Constants.HEADER_RECEIVED));
	}
	
	private boolean isSPFVerified()
	{
		AlgorithmSpfVerification spfVerification = new AlgorithmSpfVerification();
		return spfVerification.verifySPF(this.receivedFromModels.get(0).getReceivedIpAddress(), 
				this.fromEmailAddressModel.getFromEmail(), this.fromEmailAddressModel.getFromDomain());
	}
	
	private boolean isDKIMVerified(InputStream inputStream)
	{
		AlgorithmDkimVerification algorithmDkimVerification = new AlgorithmDkimVerification();
		return algorithmDkimVerification.verifyDkim(inputStream);
		
	}
	
	private boolean isFromDomainMatch()
	{
		System.out.println(this.receivedFromModels.get(0).getReceivedServer());
		System.out.println(this.receivedFromModels.get(0).getReceivedIpAddress());
		System.out.println(this.fromEmailAddressModel.getFromDomain());
		System.out.println(this.fromEmailAddressModel.getFromEmail());
		return (this.receivedFromModels.get(0).getReceivedServer().trim()
				.endsWith(this.fromEmailAddressModel.getFromDomain().trim()))
				?true
				:false;
	}
	
	private boolean isReverseDnsMatch()
	{
		System.out.println(this.receivedFromModels.get(0).getReceivedServer().trim());
		System.out.println(this.receivedFromModels.get(0).getReverseDns().trim());
		return (this.receivedFromModels.get(0).getReceivedServer().trim()
				.equals(this.receivedFromModels.get(0).getReverseDns().trim())) 
				? true 
				: false;
	}

}
