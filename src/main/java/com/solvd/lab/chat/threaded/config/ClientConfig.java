package com.solvd.lab.chat.threaded.config;

import com.solvd.lab.chat.threaded.config.base.BaseConfig;

public class ClientConfig extends BaseConfig {
    private String token;

    public ClientConfig(String host, int port, String msgPath, String token) {
        super(host, port, msgPath);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return String.format(
                "\n%s:%d\ntoken[%s]\npath to push %s",
                host, port, token, msgPath
        );
    }
}
