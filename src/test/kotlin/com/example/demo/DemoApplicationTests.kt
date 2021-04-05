package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {

	@Value("\${spring.activemq.broker-url}")
	var value : String? = null

	@Test
	fun contextLoads() {
		println(value)
	}

}
