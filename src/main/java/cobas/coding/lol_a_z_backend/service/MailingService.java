package cobas.coding.lol_a_z_backend.service;

import cobas.coding.lol_a_z_backend.util.EnvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service public class MailingService {
	@Autowired private JavaMailSender emailSender;
	@Autowired private EnvUtil envUtil;
	@Value("${spring.mail.from}") String origin;

	public void sendPasswordResetMail(String destination, String token) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(origin);
		message.setTo(destination);
		message.setSubject("League of Legends A-Z - Passwort zurücksetzen");
		message.setText(buildPasswordResetMessage(token));
		emailSender.send(message);
	}

	private String buildPasswordResetMessage(String token) {
		//TODO E-Mail Stylen
		StringBuilder message = new StringBuilder();
		message.append("Link um das Passwort zurückzusetzen: ");
		message.append(buildPasswordResetLink(token));
		return message.toString();
	}

	private String buildPasswordResetLink(String token) {
		//TODO Link dynamisch zusammenbauen
		StringBuilder link = new StringBuilder();
		link.append("http://");
		link.append(envUtil.getHostname());
		link.append(":");
		link.append(envUtil.getPort());
		link.append("/password?token=");
		link.append(token);
		return link.toString();
	}
}
