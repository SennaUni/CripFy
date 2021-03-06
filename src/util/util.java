package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class util {
	
	public static boolean isNumber(String valor) {
		try {  
		    Double.parseDouble(valor);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}
	
	private static final String EMAIL_PATTERN = 
	        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
	
	public static boolean isEmail(String email) {
		try {  
			Matcher matcher = pattern.matcher(email);
		    return matcher.matches();
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}
	
	public static String getCaptcha() {
		 
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(8);

		for (int i = 0; i < 8; i++) {

		int index = (int)(AlphaNumericString.length() * Math.random());

		sb.append(AlphaNumericString
		      .charAt(index));
		}

		return sb.toString();
	}
}
