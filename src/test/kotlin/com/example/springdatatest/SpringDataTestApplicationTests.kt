package com.example.springdatatest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringDataTestApplicationTests(@Autowired private val userRepository: UserRepository) {

    @Test
    fun `test loading entity from repository has normal data-bound properties set`() {
        // This test passes
        val user = userRepository.findById("joe.bloggs").orElseThrow { Exception("joe.bloggs not found") }
        Assertions.assertEquals("Joe", user.firstName)
    }

    @Test
    fun `test prop set by init when entity manual constructed`() {
        // This test passes
        val user = User("joe.bloggs", "Joe", "Bloggs")
        Assertions.assertEquals("foo", user.propSetByInit)
    }

    @Test
    fun `test prop set by init when entity loaded from repository`() {
        // This test fails
        val user = userRepository.findById("joe.bloggs").orElseThrow { Exception("joe.bloggs not found") }
        Assertions.assertEquals("foo", user.propSetByInit)
    }

    @Test
    fun `test delegated prop when entity manually loaded`() {
        // This test passes
        val user = User("joe.bloggs", "Joe", "Bloggs")
        Assertions.assertEquals("joe.bloggs", user.delegatedNameProp)
    }

    @Test
    fun `test delegated prop when entity loaded from repository`() {
        // This test fails
        val user = userRepository.findById("joe.bloggs").orElseThrow { Exception("joe.bloggs not found") }
        Assertions.assertEquals("joe.bloggs", user.delegatedNameProp)
    }
}
