package com.redsponge.redutils.console;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum ConsoleMSG {

    ADD("+"),
    REMOVE("-"),
    ERROR("!!!"),
    WARNING("!"),
    INFO("i");

    private String prefix;

    ConsoleMSG(String prefix) {
        this.prefix = prefix;
    }

    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
        String strDate = sdf.format(new Date());
        return strDate;
    }

    public void info(Object message, Object sender) {
        System.out.println(getTime() + "[<" + sender.getClass().getSimpleName() + ">][" + prefix + "] " + message);
    }

    public void info(Object message) {
        System.out.println(getTime() + "[" + prefix + "] " + message);
    }

}
