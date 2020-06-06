package com.solvd.lab.chat.threaded.bo;

import com.solvd.lab.chat.threaded.bo.adapter.DateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "message")
@XmlType(propOrder = {"content", "author", "date" })
public class Message {
    private String content;
    private String author;
    private Date date;

    public Message() {

    }

    public Message(String content, String author) {
        this.content = content;
        this.author = author;
        this.date = new Date();
    }


    @XmlElement(name = "content")
    public void setContent(String content) {
        this.content = content;
    }

    @XmlElement(name = "author")
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message [" + content + " - said " + date.getTime() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass()) {
            return false;
        }
        if (o == null) {
            return false;
        }
        return content.equals(((Message) o).getContent()) &&
                author.equals(((Message) o).getAuthor()) &&
                date.compareTo(((Message) o).getDate()) == 0;
    }
}