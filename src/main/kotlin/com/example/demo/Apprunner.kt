package com.example.demo

import com.example.demo.services.BookingService
import com.example.demo.services.NotificationService
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
internal class AppRunner(private val bookingService: BookingService,
                         private val notificationService: NotificationService) : CommandLineRunner {
    @Throws(Exception::class)

    @Transactional
    override fun run(vararg args: String) {
        logger.info("Booking for Alice, Bob and Carol")
        bookingService.book("Alice", "Bob", "Carol")
        notificationService.doPublish()
        logger.info("Done booking")
    }


    companion object {
        private val logger = LoggerFactory.getLogger(AppRunner::class.java)
    }
}