package com.solvd.lab.chat.threaded;

import com.solvd.lab.chat.threaded.bo.Message;
import com.solvd.lab.chat.threaded.config.ServerConfig;
import com.solvd.lab.chat.threaded.config.configurator.BaseConfigurator;
import com.solvd.lab.chat.threaded.constant.IOConstant;
import com.solvd.lab.chat.threaded.exception.UnableToReadException;
import com.solvd.lab.chat.threaded.util.XMLUtil;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class);
    private static ServerConfig config = init();

    private static final String HOST = config.getHost();
    private static final int PORT = config.getPort();
    private static final String PATH_TO_LISTEN = config.getMsgPath();
    private static final Set<String> AVAILABLE_CLIENTS = config.getAvailableClients();

    private static List<Message> history = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {
        listen();
    }

    private static ServerConfig init() {
        return BaseConfigurator.getInstance().serverConfig();
    }

    private static void listen() throws InterruptedException{


        while (true) {
            String[] fileLs = new File(PATH_TO_LISTEN).getAbsoluteFile().list();
            int numOfMsgs = fileLs.length;
            if (numOfMsgs > 0) {
                for (String filePath : fileLs) {
                    Connection conn = new Connection(PATH_TO_LISTEN + IOConstant.PATH_SEP + filePath);
                    conn.run();
                    conn.join();
                }
            }
            LOGGER.info("Active threads : ".concat(String.valueOf(Thread.activeCount())));
            LOGGER.info("History now is : ".concat(history.toString()));
            Thread.sleep(10000);
        }
    }

    static class Connection extends Thread {

        private String path;

        public Connection(String path) {
            this.path = path;
        }

        public void run() {
            try {
                Message msg = readMessage(path);
                LOGGER.info(msg);
                history.add(msg);
            } catch (UnableToReadException e) {
                e.printStackTrace();
                currentThread().interrupt();
            }
        }

    }

    private static Message readMessage(String pathTo) throws UnableToReadException {
        try {
            return XMLUtil.readMessage(pathTo);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
            throw new UnableToReadException("Something went wrong while reading!");
        }
    }
}
