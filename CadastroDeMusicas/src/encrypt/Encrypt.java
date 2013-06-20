package encrypt;

public class Encrypt {
	  public static byte[] encrypt(String x) throws Exception
	  {
	     java.security.MessageDigest d = null;
	     d = java.security.MessageDigest.getInstance("SHA-1");
	     d.reset();
	     d.update(x.getBytes());
	     
	     String secret = new String(d.digest());
	     
	     sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	     String encoded = encoder.encode(secret.getBytes());
	     
	     return  encoded.getBytes();	     
	     
	  }
}
