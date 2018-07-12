package com.example.im_data;

/**
 * 消息类型
 */
public class Message {

    /**
     * 消息的 ID
     */
    private long id;

    /**
     * 消息是发送的 or 接收的
     */
    private Direct direct;

    /**
     * 消息的类型
     */
    private Type type;

    /**
     * 消息的内容
     */
    private String content;

    /**
     * 消息的附带信息
     */
    private String tag;


    public enum Type {
        TEXT,IMAGE,VOICE,CUSTOM
    }


    public enum Direct {
        SEND,RECEIVE
    }

    public long getId() {
        return id;
    }

    public Message setId(long id) {
        this.id = id;
        return this;
    }

    public Direct getDirect() {
        return direct;
    }

    public Message setDirect(Direct direct) {
        this.direct = direct;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Message setType(Type type) {
        this.type = type;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public Message setTag(String tag) {
        this.tag = tag;
        return this;
    }
}
