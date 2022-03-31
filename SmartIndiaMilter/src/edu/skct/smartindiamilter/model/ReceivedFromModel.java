package edu.skct.smartindiamilter.model;

public class ReceivedFromModel 
{
	private String receivedServer;
	private String receivedIpAddress;
	private String reverseDns;
	
	public String getReceivedServer() {
		return receivedServer;
	}
	public void setReceivedServer(String receivedServer) {
		this.receivedServer = receivedServer;
	}
	public String getReceivedIpAddress() {
		return receivedIpAddress;
	}
	public void setReceivedIpAddress(String receivedIpAddress) {
		this.receivedIpAddress = receivedIpAddress;
	}
	public String getReverseDns() {
		return reverseDns;
	}
	public void setReverseDns(String reverseDns) {
		this.reverseDns = reverseDns;
	}
	@Override
	public String toString() {
		return "ReceivedFromModel [receivedServer=" + receivedServer + ", receivedIpAddress=" + receivedIpAddress
				+ ", reverseDns=" + reverseDns + "]";
	}
	
	
}
