package com.solvd.lab.chat.threaded;

import com.solvd.lab.chat.threaded.bo.Message;
import com.solvd.lab.chat.threaded.config.ClientConfig;
import com.solvd.lab.chat.threaded.config.configurator.BaseConfigurator;
import com.solvd.lab.chat.threaded.constant.IOConstant;
import com.solvd.lab.chat.threaded.exception.UnableToReadException;
import com.solvd.lab.chat.threaded.exception.UnableToWriteException;
import com.solvd.lab.chat.threaded.util.XMLUtil;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Client {
    private static ClientConfig config = init();

    private static final String HOST = config.getHost();
    private static final int PORT = config.getPort();
    private static final String PATH_TO_PUSH = config.getMsgPath();
    private static final String token = config.getToken();

    public static void main(String[] args) throws InterruptedException{
        while (true) {
            Message msg = new Message("hello", String.valueOf((int)(Math.random() * 10000)));
            try {
                writeMessage(msg);
            } catch (UnableToWriteException e) {
                System.exit(1);
            }
            Thread.sleep(30000);
        }

    }

    private static ClientConfig init() {
        return BaseConfigurator.getInstance().clientConfig();
    }

    private static void writeMessage(Message msg) throws UnableToWriteException {
        try {
            String filePath = PATH_TO_PUSH + IOConstant.PATH_SEP + (int)(Math.random() * 1000000000) + IOConstant.XML;
            XMLUtil.writeMessage(filePath, msg);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            throw new UnableToWriteException("Something went wrong while writing msg!");
        }
    }
}
