package com.example.springdatatest

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Transient
import kotlin.properties.Delegates

@Entity
class User(@Id val userName: String, val firstName: String, val lastName: String) {

    @Transient
    val propSetByInit: String

    init {
        propSetByInit = "foo" // In reality this would be calculated at runtime
    }

    @delegate:Transient
    var delegatedNameProp: String by Delegates.observable(userName) { _, _, _ -> println("Changing name") }
}

interface UserRepository : CrudRepository<User, String>