package cobas.coding.lol_a_z_backend.config;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.List;

@Slf4j @NoArgsConstructor public class Jeffrey {

	private static final List<Path> directories = List.of(Path.of("./cert"));
	private static final String KEYSTOREPASSWORD = "password";
	private static final String salt = "dsfgfdsaewsads234234sdf!asd.!%asd0812=))9702";

	public static void generateKeyStore() {
		try {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			char[] pwdArray = KEYSTOREPASSWORD.toCharArray();
			keyStore.load(null, pwdArray);
			FileOutputStream fileOutputStream = new FileOutputStream("./cert/keystore.jks");

			KeyFactory kf = KeyFactory.getInstance("RSA");
			BigInteger inta = BigInteger.ZERO.setBit(512);
			BigInteger intb = BigInteger.ZERO.setBit(512);
			RSAPrivateKeySpec keysp = new RSAPrivateKeySpec(inta, intb);
			Key privateKey = kf.generatePrivate(keysp);
			X509Certificate[] certificateChain = new X509Certificate[2];
			keyStore.setKeyEntry("sso-signing-key", privateKey, pwdArray, certificateChain);

			keyStore.store(fileOutputStream, pwdArray);

		} catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static SecretKey getKeyFromPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
		SecretKey originalKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		return originalKey;
	}

	public static void initFolderStructure() {
		for (Path entry : Jeffrey.directories) {
			File directory = new File(entry.toUri());
			if (!directory.exists()) {
				boolean created = directory.mkdir();
				log.info("{} created = {}", directory.getName(), created);
			}
		}

	}
}
