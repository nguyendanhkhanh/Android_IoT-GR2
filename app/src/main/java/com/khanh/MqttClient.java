package com.khanh;

import org.eclipse.paho.android.service.MqttAndroidClient;

public class MqttClient {
    private String URL;
    private String UserName;
    private String Pass;
    private String Topic;
    private String Content;
    private MqttAndroidClient mqttAndroidClient;
    public MqttClient(){

    }

    public MqttClient(String URL, String userName, String pass, String topic, String content) {
        this.URL = URL;
        UserName = userName;
        Pass = pass;
        Topic = topic;
        Content = content;
    }

    public String getPass() {
        return Pass;
    }

    public String getContent() {
        return Content;
    }

    public String getTopic() {
        return Topic;
    }

    public String getURL() {
        return URL;
    }

    public String getUserName() {
        return UserName;
    }
}
