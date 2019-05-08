package leCampusServer.utility;

import java.math.BigDecimal;

public class NumberProcessor {
	
	public static double round(double value, int scale) {  
        BigDecimal bd = new BigDecimal(value);  
        bd = bd.setScale(scale, BigDecimal.ROUND_DOWN);  
        double result = bd.doubleValue();  
        bd = null;  
        return result;  
    }

}
