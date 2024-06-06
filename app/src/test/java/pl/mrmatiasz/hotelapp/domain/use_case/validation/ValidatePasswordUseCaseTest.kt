package pl.mrmatiasz.hotelapp.domain.use_case.validation

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ValidatePasswordUseCaseTest {

    private lateinit var validatePassword: ValidatePasswordUseCase

    @Before
    fun setUp() {
        validatePassword = ValidatePasswordUseCase()
    }

    @Test
    fun `password is valid, returns success`() {
        val result = validatePassword.execute("12345678")

        assertEquals(result.isSuccess, true)
    }

    @Test
    fun `password has less than 8 characters, returns error`() {
        val result = validatePassword.execute("1234")

        assertEquals(result.isSuccess, false)
    }
}