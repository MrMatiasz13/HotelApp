package pl.mrmatiasz.hotelapp.domain.use_case.validation

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ValidateRepeatPasswordTest {

    private lateinit var validateRepeatPassword: ValidateRepeatPasswordUseCase

    @Before
    fun setUp() {
        validateRepeatPassword = ValidateRepeatPasswordUseCase()
    }

    @Test
    fun `passwords matches, returns success`() {
        val result = validateRepeatPassword
            .execute("12345678", "12345678")

        assertEquals(result.isSuccess, true)
    }

    @Test
    fun `passwords doesn't matches, returns error`() {
        val result = validateRepeatPassword
            .execute("1234567", "tomato")

        assertEquals(result.isSuccess, false)
    }
}