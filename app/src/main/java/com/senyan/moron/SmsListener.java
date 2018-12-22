package com.senyan.moron;

import java.io.IOException;

public interface SmsListener {
    public void messageReceived(String messageText) throws IOException;
}