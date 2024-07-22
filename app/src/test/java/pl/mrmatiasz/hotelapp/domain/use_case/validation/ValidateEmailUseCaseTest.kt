package pl.mrmatiasz.hotelapp.domain.use_case.validation

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ValidateEmailUseCaseTest {

    private lateinit var validateEmail: ValidateEmailUseCase


    @Before
    fun setUp() {
        validateEmail = ValidateEmailUseCase()
    }

    @Test
    fun `email is valid, returns success`() {
        val result = validateEmail.execute("mateusz21@gmail.com")

        assertEquals(result.isSuccess, true)
    }

    @Test
    fun `email is valid(different way), returns success`() {
        val result = validateEmail.execute("mateusz@wp.com")

        assertEquals(result.isSuccess, true)
    }

    @Test
    fun `email is blank, returns error`() {
        val result = validateEmail.execute("")

        assertEquals(result.isSuccess, false)
    }

    @Test
    fun `email is not valid, returns error`() {
        val result = validateEmail.execute("mateusz.com")

        assertEquals(result.isSuccess, false)
    }

    @Test
    fun `email is valid despite space at the end, returns success`() {
        val result = validateEmail.execute("email@wp.pl ")

        assertEquals(result.isSuccess, true)
    }

    @Test
    fun `email is not valid(space in mid of email), returns error`() {
        val result = validateEmail.execute("email @wp.pl")

        assertEquals(result.isSuccess, false)
    }

}