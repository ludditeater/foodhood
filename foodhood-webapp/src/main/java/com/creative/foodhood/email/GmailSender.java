package com.creative.foodhood.email;

import java.security.Security;
import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GmailSender extends javax.mail.Authenticator {
		private String user;
		private String password;
		private Session session;
		
		static {
			Security.addProvider(new com.creative.foodhood.email.JSSEProvider());
		}

		public GmailSender(String user, String password) {
			this.user = user;
			this.password = password;

			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.quitwait", "false");

			session = Session.getDefaultInstance(props, this);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, password);
		}

		public synchronized boolean sendMail(MessageContent messageContent, String email) throws Exception {
			try {

				MimeMessage message = new MimeMessage(session);
				String body = "This is a auto generated message. Thanks FoodHood";
				if (messageContent.getBody() != null || messageContent.getBody() != "") {
					body = messageContent.getBody();
				}

				MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
				mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
				mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
				mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
				mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
				mc.addMailcap("image/gif;;  x-java-content-handler=com.sun.mail.handlers.image_gif");
				mc.addMailcap("image/jpeg;; x-java-content-handler=com.sun.mail.handlers.image_jpeg");
				mc.addMailcap("image/png;;  x-java-content-handler=com.sun.mail.handlers.image_png");
				CommandMap.setDefaultCommandMap(mc);

				message.setSender(new InternetAddress("FoodHood.noreply@gmail.com"));
				message.setSubject(messageContent.getSubject());
				Multipart mp = new MimeMultipart();
				MimeBodyPart contentpart = new MimeBodyPart();
				contentpart.setContent(body, "text/html");
				mp.addBodyPart(contentpart);
				message.setRecipient(Message.RecipientType.CC, new InternetAddress(email));
				message.setContent(mp);
				Transport.send(message);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

	}


