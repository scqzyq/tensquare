package com.scqzy.user.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/15 21:13
 */
@Service
@Slf4j
public class SmsListener {

    @RabbitListener(queues = "sms")
    public void receiveSms(Map<String, String> map) {
        System.out.println(map);
    }
}
