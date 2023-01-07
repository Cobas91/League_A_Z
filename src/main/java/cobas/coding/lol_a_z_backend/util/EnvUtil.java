package cobas.coding.lol_a_z_backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component public class EnvUtil {
	@Autowired Environment environment;

	private String port;
	private String hostname;

	/**
	 * Get port.
	 *
	 * @return
	 */
	public String getPort() {
		if (port == null)
			port = environment.getProperty("local.server.port");
		return port;
	}

	/**
	 * Get port, as Integer.
	 *
	 * @return
	 */
	public Integer getPortAsInt() {
		return Integer.valueOf(getPort());
	}

	/**
	 * Get hostname.
	 *
	 * @return
	 */
	public String getHostname() {
		try {
			if (hostname == null)
				hostname = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostname;
	}

	public String getServerUrlPrefi() throws UnknownHostException {
		return "http://" + getHostname() + ":" + getPort();
	}
}
