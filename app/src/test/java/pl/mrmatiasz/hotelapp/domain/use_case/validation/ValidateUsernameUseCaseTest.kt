package pl.mrmatiasz.hotelapp.domain.use_case.validation

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ValidateUsernameUseCaseTest {

    private lateinit var validateUsernameUseCase: ValidateUsernameUseCase

    @Before
    fun setUp() {
        validateUsernameUseCase = ValidateUsernameUseCase()
    }

    @Test
    fun `username is valid, returns success`() {
        val result = validateUsernameUseCase.execute("Matiasz")

        assertEquals(result.isSuccess, true)
    }

    @Test
    fun `username starts with lower-case, returns error`() {
        val result = validateUsernameUseCase.execute("matiasz")

        assertEquals(result.isSuccess, false)
    }
}