package com.thide11.translation;

import java.util.Locale;
import java.util.ResourceBundle;

public class Translation {

    ResourceBundle messages;

    public Translation() {
        this.messages = getMessageBundle();
    }

    public String getString(String sourceId) {
        return messages.getString(sourceId);
    }

    /** Dont recomment because read the file */
    public static String getStringStatic(String sourceId) {
        return getMessageBundle().getString(sourceId);
    }

    static private ResourceBundle getMessageBundle(){
        Locale locale = Locale.getDefault();
        return ResourceBundle.getBundle("com.thide11.translation.language", locale);
    }

}


