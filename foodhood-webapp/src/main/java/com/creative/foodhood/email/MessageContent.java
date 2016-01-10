package com.creative.foodhood.email;

public class MessageContent {
     private String subject;
     private String body;
     private String sender;
     private String toRecipients;
	
     public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getToRecipients() {
		return toRecipients;
	}
	public void setToRecipients(String toRecipients) {
		this.toRecipients = toRecipients;
	}
}
