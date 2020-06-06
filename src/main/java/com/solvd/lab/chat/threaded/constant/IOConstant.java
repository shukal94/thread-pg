package com.solvd.lab.chat.threaded.constant;

public class IOConstant {
    public static final String CWD = System.getProperty("user.dir");
    public static final String PATH_SEP = System.getProperty("file.separator");
    public static final String CONFIG_PATH = String.format(
            "%s%s%s%s%s%s%s%s%s",
            CWD, PATH_SEP, "src", PATH_SEP, "main", PATH_SEP, "resources", PATH_SEP, "config.properties");
    public static final String XML = ".xml";
}
