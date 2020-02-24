package com.example.springdatatest

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(@Id val userName: String, val firstName: String, val lastName: String) {

    @Transient
    val propSetByInit: String

    init {
        propSetByInit = "foo" // In reality this would be a value calculated at runtime
    }
}

interface UserRepository: CrudRepository<User, String>