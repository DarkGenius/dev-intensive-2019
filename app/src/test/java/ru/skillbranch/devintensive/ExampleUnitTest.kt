package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_factory() {
        val user1 = User.makeUser("John Skeet")
        val user2 = User.makeUser("James")
        val user3 = User.makeUser(" ")
        val user4 = User.makeUser("  ")
        val user5 = User.makeUser(null)
        println(user1)
        println(user2)
        println(user3)
        println(user4)
        println(user5)
    }

    @Test
    fun test_parseFullName() {
        val n1 = Utils.parseFullName(null) //null null
        println(n1)
        val n2 = Utils.parseFullName("") //null null
        println(n2)
        val n3 = Utils.parseFullName(" ") //null null
        println(n3)
        val n4 = Utils.parseFullName("John") //John null
        println("${n4.first} ${n4.second}")
        val n5 = Utils.parseFullName("John Skeet") //John null
        println("${n5.first} ${n5.second}")
    }

    @Test
    fun test_dateUtils() {
        val d1 = Date()
        println(d1.format())
        val d2 = d1.add(1, TimeUnits.HOUR)
        println(d2.format())
        println(d1.format())
    }
}
