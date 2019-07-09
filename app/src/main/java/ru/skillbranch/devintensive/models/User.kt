package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.lang.Exception
import java.util.*

data class User(val id : String,
           var firstName : String?,
           var lastName : String?,
           var avatar : String?,
           var rating : Int = 0,
           var respect : Int = 0,
           var lastVisit : Date? = Date(),
           var isOnline : Boolean = false) {

    companion object Factory {
        private var lastId: Int = -1
        private val builder: IUserBuilder = UserBuilder()
        fun makeUser(fullName: String?): User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName, avatar = null)
        }
        fun Builder(): IUserBuilder = builder
    }
}

interface IUserBuilder {
    fun id(value: String): IUserBuilder
    fun firstName(value: String?): IUserBuilder
    fun lastName(value: String?): IUserBuilder
    fun avatar(value: String?): IUserBuilder
    fun rating(value: Int): IUserBuilder
    fun respect(value: Int): IUserBuilder
    fun lastVisit(value: Date?): IUserBuilder
    fun isOnline(value: Boolean): IUserBuilder
    fun build(): User
}

class UserBuilder: IUserBuilder {
    private var id : String? = null
    private var firstName : String? = null
    private var lastName : String? = null
    private var avatar : String? = null
    private var rating : Int = 0
    private var respect : Int = 0
    private var lastVisit : Date? = Date()
    private var isOnline : Boolean = false

    override fun id(value: String): IUserBuilder {
        id = value
        return this
    }

    override fun firstName(value: String?): IUserBuilder {
        firstName = value
        return this
    }

    override fun lastName(value: String?): IUserBuilder {
        lastName = value
        return this
    }

    override fun avatar(value: String?): IUserBuilder {
        avatar = value
        return this
    }

    override fun rating(value: Int): IUserBuilder {
        rating = value
        return this
    }

    override fun respect(value: Int): IUserBuilder {
        respect = value
        return this
    }

    override fun lastVisit(value: Date?): IUserBuilder {
        lastVisit = value
        return this
    }

    override fun isOnline(value: Boolean): IUserBuilder {
        isOnline = value
        return this
    }

    override fun build(): User {
        if (id == null) throw Exception("id must be specified")
        return User(
                id!!,
                firstName,
                lastName,
                avatar,
                rating,
                respect,
                lastVisit,
                isOnline
        )
    }

}
