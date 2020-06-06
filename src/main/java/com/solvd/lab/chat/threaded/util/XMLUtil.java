package com.solvd.lab.chat.threaded.util;

import com.solvd.lab.chat.threaded.bo.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class XMLUtil {
    public static void writeMessage(String pathTo, Message msg) throws JAXBException, IOException {
        File msgFile = new File(pathTo).getAbsoluteFile();
        msgFile.createNewFile();
        JAXBContext context = JAXBContext.newInstance(Message.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(msg, msgFile);
    }

    public static Message readMessage(String pathTo) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Message.class);
        return (Message) context.createUnmarshaller()
                .unmarshal(new FileReader(pathTo));
    }
}
