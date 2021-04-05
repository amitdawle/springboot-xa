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
    
   /*
    From application.yml,
    spring:
      jta:
        datasource:
          accounts:
    
   */
    @Bean(name = ["jdbcTemplateAccounts"])
    fun jdbcTemplateAccounts(@Qualifier("accounts") ds: DataSource?): JdbcTemplate? {
        return ds?.let { JdbcTemplate(it) }
    }

  /*
    From application.yml,
    spring:
      jta:
        datasource:
          boolings:
    
   */
    @Bean(name = ["jdbcTemplateBookings"])
    fun jdbcTemplateBookings(@Qualifier("bookings") ds: DataSource?): JdbcTemplate? {
        return ds?.let { JdbcTemplate(it) }
    }

    /*
    Configuration in application.yml under
    
    spring:
      jta:
        datasource:
          bookings:
         ...
    */
    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.bookings")
    fun dataSourceBookings(): DataSource? {
        return AtomikosDataSourceBean()
    }

     /*
    Configuration in application.yml under
    
    spring:
      jta: 
        datasource:
          accounts:
         ...
    */
    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.accounts")
    fun dataSourceAccounts(): DataSource? {
        return AtomikosDataSourceBean()
    }

     /*
    Configuration in application.yml under
    
    spring:
      jta:
        atomikos:
          connectionfactory:
            notifications:
         ...
    */
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
