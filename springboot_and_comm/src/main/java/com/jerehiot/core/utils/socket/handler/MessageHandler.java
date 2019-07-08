package com.jerehiot.core.utils.socket.handler;
import com.jerehiot.core.utils.socket.dto.ServerReceiveDto;
import com.jerehiot.core.utils.socket.server.Connection;

public interface MessageHandler {

    /**
     * 获得消息的处理函数
     *
     * @param connection 封装了客户端的socket
     * @param dto        接收到的dto
     */
    void onReceive(Connection connection, ServerReceiveDto dto);
}