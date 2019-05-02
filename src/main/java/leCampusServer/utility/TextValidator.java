package leCampusServer.utility;

import org.apache.commons.lang3.StringUtils;

public class TextValidator {
	
	public boolean isEmptyString(String string) {
        return string == null ||
                string.equalsIgnoreCase("null") ||
                (StringUtils.equals(string, "null")) ||
                (StringUtils.isEmpty(string));
    }

}
