package ru.skillbranch.devintensive

import android.media.Image
import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.humanizeDiff
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.ImageMessage
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
    fun test_messages() {
        val m1 = BaseMessage.makeMessage(User.makeUser("Вася Пупкин"), Chat("1"), Date().add(-10, TimeUnits.MINUTE), "image", "http://ya.ru")
        println(m1.formatMessage())
        val m2 = BaseMessage.makeMessage(User.makeUser("Игнат Петров"), Chat("1"), Date().add(-1, TimeUnits.SECOND), "text", "привет")
        println(m2.formatMessage())
    }

    @Test
    fun test_dateUtils() {
        val d1 = Date()
        println(d1.format())
        val d2 = d1.add(1, TimeUnits.HOUR)
        println(d2.format())
        println(d1.format())
    }

    @Test
    fun test_initials() {
        val s1 = Utils.toInitials("john" ,"doe") //JD
        println(s1)
        val s2 = Utils.toInitials("John", null) //J
        println(s2)
        val s3 = Utils.toInitials(null, null) //null
        println(s3)
        val s4 = Utils.toInitials(" ", "") //null
        println(s4)
    }

    @Test
    fun test_transliterate() {
        val s1 = Utils.transliteration("Женя Стереотипов") //Zhenya Stereotipov
        println(s1)
        val s2 = Utils.transliteration("Amazing Петр","_") //Amazing_Petr
        println(s2)
    }

    @Test
    fun test_plural() {
        for (i in 0..125) {
            println(TimeUnits.DAY.plural(i))
        }
    }

    @Test
    fun test_dateHumanize() {
        val s1 = Date().add(-2, TimeUnits.HOUR).humanizeDiff() //2 часа назад
        println(s1)
        val s2 = Date().add(-5, TimeUnits.DAY).humanizeDiff() //5 дней назад
        println(s2)
        val s3 = Date().add(2, TimeUnits.MINUTE).humanizeDiff() //через 2 минуты
        println(s3)
        val s4 = Date().add(7, TimeUnits.DAY).humanizeDiff() //через 7 дней
        println(s4)
        val s5 = Date().add(-400, TimeUnits.DAY).humanizeDiff() //более года назад
        println(s5)
        val s6 = Date().add(400, TimeUnits.DAY).humanizeDiff() //более чем через год
        println(s6)
    }
}
