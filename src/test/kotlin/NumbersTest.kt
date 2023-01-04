import org.junit.Assert.assertEquals
import org.junit.Test

internal class NumbersTest {

    @Test
    fun test_sum_long() {
        val numbers: Numbers = Numbers.Base(2_000_000_000, 147483648)
        val isSumLong = numbers.isSumLong()
        val expected = true
        assertEquals(expected, isSumLong)

        val actual = numbers.sumLong()
        val expectedNumber: Long = Int.MAX_VALUE + 1L
        assertEquals(expectedNumber, actual)
    }

    @Test
    fun test_sum_int() {
        val numbers: Numbers = Numbers.Base(2_000_000_000, 147483647)
        val isSumLong = numbers.isSumLong()
        val expected = false
        assertEquals(expected, isSumLong)

        val actual: Int = numbers.sumInt()
        val expectedNumber: Int = Int.MAX_VALUE
        assertEquals(expectedNumber, actual)
    }

    @Test(expected = IllegalAccessException::class)
    fun test_sum_int_no_check() {
        val numbers: Numbers = Numbers.Base(2_000_000_000, 2_000_000_000)
        numbers.sumInt()
    }

    @Test(expected = IllegalAccessException::class)
    fun test_sum_long_no_check() {
        val numbers: Numbers = Numbers.Base(2_000_000_000, 2_000_000_000)
        numbers.sumLong()
    }

    @Test(expected = IllegalStateException::class)
    fun test_sum_long_called_but_for_long_sum() {
        val numbers: Numbers = Numbers.Base(2_000_000_000, 2_000_000_000)
        val actual = numbers.isSumLong()
        val expected = true
        assertEquals(expected, actual)

        numbers.sumInt()
    }

    @Test(expected = IllegalStateException::class)
    fun test_sum_long_called_but_for_int_sum() {
        val numbers: Numbers = Numbers.Base(2_000_000_000, 1_000_000)
        val actual = numbers.isSumLong()
        val expected = false
        assertEquals(expected, actual)

        numbers.sumLong()
    }

    @Test
    fun test_diff() {
        val numbers: Numbers = Numbers.Base(12, 4)
        val actual = numbers.diff()
        val expected = 8
        assertEquals(expected, actual)
    }

    @Test
    fun test_div() {
        val numbers: Numbers = Numbers.Base(12, 4)
        val actual = numbers.div()
        val expected = 3.0
        assertEquals(expected, actual, 0.000000000001)
    }

    @Test
    fun test_div_double() {
        val numbers: Numbers = Numbers.Base(12, 4)
        val actual = numbers.div()
        val expected = 3.0
        assertEquals(expected, actual, 0.00000000000001)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_div_zero() {
        val numbers: Numbers = Numbers.Base(12, 0)
        numbers.div()
    }
}