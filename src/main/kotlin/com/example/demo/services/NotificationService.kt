package com.example.demo.services

import org.slf4j.LoggerFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
open class NotificationService(val jmsTemplate: JmsTemplate?){
    private val logger = LoggerFactory.getLogger(NotificationService::class.java)

    @Transactional
    open fun doPublish() {
        println("Sending message to counters.out.queue")
        jmsTemplate?.convertAndSend("counters.out.queue" , "BOOKED")
        println("Sent")
    }
}