package com.solvd.lab.chat.threaded.config;

import com.solvd.lab.chat.threaded.config.base.BaseConfig;

import java.util.Set;

public class ServerConfig extends BaseConfig {
    private Set<String> availableClients;

    public ServerConfig(String host, int port, String msgPath, Set<String> availableClients) {
        super(host, port, msgPath);
        this.availableClients = availableClients;
    }

    public Set<String> getAvailableClients() {
        return availableClients;
    }

    public void setAvailableClients(Set<String> availableClients) {
        this.availableClients = availableClients;
    }

    @Override
    public String toString() {
        return String.format(
                "\n%s:%d\navailable clients[%s]\npath to listen %s",
                host, port, availableClients.toString(), msgPath
        );
    }
}
