package com.example.im_data;

/**
 * @author linzheng
 */
public class MessageFactory {

    public static Message createTextMessage(String text) {
        return createMessage(text, Message.Type.TEXT);
    }

    public static Message createImageMessage(String imageUrl) {
        return createMessage(imageUrl, Message.Type.IMAGE);
    }

    public static Message createMessage(String content, Message.Type type) {
        Message message = new Message();
        message.setContent(content);
        message.setDirect(Message.Direct.SEND);
        message.setType(type);
        return message;
    }

}
