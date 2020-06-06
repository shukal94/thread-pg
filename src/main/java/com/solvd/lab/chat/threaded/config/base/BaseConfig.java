package com.solvd.lab.chat.threaded.config.base;

public abstract class BaseConfig {
    protected String host;
    protected int port;
    protected String msgPath;

    public BaseConfig(String host, int port, String msgPath) {
        this.host = host;
        this.port = port;
        this.msgPath = msgPath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMsgPath() {
        return msgPath;
    }

    public void setMsgPath() {
        this.msgPath = msgPath;
    }
}
