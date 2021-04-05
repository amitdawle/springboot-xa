package com.example.demo.services

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
open class BookingService(@Qualifier("jdbcTemplateBookings") val bookings: JdbcTemplate?,
                          @Qualifier("jdbcTemplateAccounts") val accounts: JdbcTemplate?,
                          val jmsTemplate: JmsTemplate?){
    private val logger = LoggerFactory.getLogger(BookingService::class.java)

    @Transactional
    open fun book(vararg persons: String?) {
        bookUsers(persons)
        addAccounts(persons)
    }


    private fun bookUsers(persons: Array<out String?>) {
        for (person in persons) {
            logger.info("Booking $person in a seat...")
            bookings?.update("insert into BOOKINGS(FIRST_NAME) values (?)", person)
        }
    }

    fun addAccounts(persons: Array<out String?>) : Unit {
        for (person in persons) {
            logger.info("Adding accounts for $person ...")
            accounts?.update("insert into ACCOUNTS(FIRST_NAME) values (?)", person)
        }
    }

}