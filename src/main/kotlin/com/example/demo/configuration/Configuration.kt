package com.example.demo.configuration

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jta.atomikos.AtomikosConnectionFactoryBean
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jms.core.JmsTemplate
import javax.sql.DataSource


@Configuration
class Configuration {

    @Bean(name = ["jdbcTemplateAccounts"])
    fun jdbcTemplateAccounts(@Qualifier("accounts") ds: DataSource?): JdbcTemplate? {
        return ds?.let { JdbcTemplate(it) }
    }


    @Bean(name = ["jdbcTemplateBookings"])
    fun jdbcTemplateBookings(@Qualifier("bookings") ds: DataSource?): JdbcTemplate? {
        return ds?.let { JdbcTemplate(it) }
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.bookings")
    fun dataSourceBookings(): DataSource? {
        return AtomikosDataSourceBean()
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.accounts")
    fun dataSourceAccounts(): DataSource? {
        return AtomikosDataSourceBean()
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.connectionfactory.notifications")
    fun activeMq(): AtomikosConnectionFactoryBean {
        return AtomikosConnectionFactoryBean()
    }

    @Bean
    fun notificationsTemplate(cf: javax.jms.ConnectionFactory): JmsTemplate? {
        var template = JmsTemplate(cf)
        template.isSessionTransacted = true
        template.sessionAcknowledgeMode = 0
        return template
    }


}