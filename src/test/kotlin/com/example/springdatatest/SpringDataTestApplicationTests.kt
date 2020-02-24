package com.example.springdatatest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringDataTestApplicationTests(@Autowired private val userRepository: UserRepository) {

    @Test
    fun `get user`() {
        // This test works
        val user = userRepository.findById("joe.bloggs").orElseThrow { Exception("joe.bloggs not found") }
        Assertions.assertEquals("Joe", user.firstName)
    }

    @Test
    fun `test prop set by init - manual`() {
        // This test works
        val user = User("job.bloggs", "Joe", "Bloggs")
        Assertions.assertEquals("foo", user.propSetByInit)
    }

    @Test
    fun `test prop set by init - jpa repository`() {
        // This test fails
        val user = userRepository.findById("joe.bloggs").orElseThrow { Exception("joe.bloggs not found") }
        Assertions.assertEquals("foo", user.propSetByInit)
    }
}
