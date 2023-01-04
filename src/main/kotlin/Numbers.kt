import java.lang.IllegalStateException

interface Numbers {

    fun diff(): Int

    fun div(): Double

    fun isSumLong(): Boolean

    fun sumLong(): Long

    fun sumInt(): Int

    class Base(
        private val number1: Int,
        private val number2: Int
    ) : Numbers {

        private var wasSumLongCalled = false
        private var wasSumLong = false

        override fun isSumLong(): Boolean {
            wasSumLongCalled = true
            val rest = Int.MAX_VALUE - number1
            val result = number2 > rest
            wasSumLong = result
            return result
        }

        override fun sumLong(): Long {
            if (wasSumLongCalled) {
                if (wasSumLong)
                    return number1.toLong() + number2
                throw IllegalStateException("you shouldn't use this method: sumLong")
            }
            throw IllegalAccessException("first you should check the sum by method isSumLong")
        }

        override fun sumInt(): Int {
            if (wasSumLongCalled) {
                if (wasSumLong)
                    throw IllegalStateException("you shouldn't use this method: sumInt")
                return number1 + number2
            }
            throw IllegalAccessException("first you should check the sum by method isSumLong")
        }

        override fun diff(): Int {
            return number1 - number2
        }

        override fun div(): Double {
            if (number2 == 0)
                throw IllegalArgumentException("can't divide by zero")
            return number1.toDouble() / number2
        }
    }
}