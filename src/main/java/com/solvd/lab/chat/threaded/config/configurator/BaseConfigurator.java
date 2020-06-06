package com.solvd.lab.chat.threaded.config.configurator;

import com.solvd.lab.chat.threaded.config.ClientConfig;
import com.solvd.lab.chat.threaded.config.ServerConfig;
import com.solvd.lab.chat.threaded.constant.ConfigConstant;
import com.solvd.lab.chat.threaded.constant.SplitConstant;
import com.solvd.lab.chat.threaded.exception.BadConfigException;
import com.solvd.lab.chat.threaded.util.PropertyArgResolver;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class BaseConfigurator {
    private final static Logger LOGGER = Logger.getLogger(BaseConfigurator.class);
    private static BaseConfigurator instance;
    private PropertyArgResolver config;

    private String host;
    private int port;
    private String token;
    private Set<String> availableClients;
    private String msgPath;

    public static BaseConfigurator getInstance() {
        if (instance == null) {
            synchronized (BaseConfigurator.class) {
                if (instance == null) {
                    instance = new BaseConfigurator();
                }
            }
        }
        return instance;
    }

    private BaseConfigurator() {
        try {
            config = PropertyArgResolver.getInstance();
            host = config.get(ConfigConstant.HOST_KEY);
            port = Integer.valueOf(config.get(ConfigConstant.PORT_KEY));
            msgPath = config.get(ConfigConstant.MESSAGE_PATH_KEY);
            availableClients =
                Arrays.stream(
                    config.get(ConfigConstant.AVAILABLE_CLIENTS_KEY).split(SplitConstant.COLON))
                    .collect(Collectors.toSet()
                );
            token = config.get(ConfigConstant.TOKEN_KEY);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new BadConfigException("Something went wrong while configuring!");
        }
    }

    public ServerConfig serverConfig() {
        ServerConfig config = new ServerConfig(host, port, msgPath, availableClients);
        LOGGER.debug(String.format("\nInit server configuration %s", config));
        return config;
    }

    public ClientConfig clientConfig() {
        ClientConfig config = new ClientConfig(host, port, msgPath, token);
        LOGGER.debug(String.format("\nInit client configuration %s", config));
        return config;
    }

}
