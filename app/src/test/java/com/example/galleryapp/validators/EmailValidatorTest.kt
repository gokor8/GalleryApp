package com.example.galleryapp.validators

import junit.framework.TestCase
import org.junit.Test

class EmailValidatorTest : TestCase() {

    val emailValidator = EmailValidator()

    @Test
    fun testGetValidatorType() {

    }

    @Test
    fun test_validate_valid() {
        val validValue = "aboba@mail.ru"

        val errorUrl = emailValidator.validate(validValue)

        assertNull(errorUrl)
    }

    //@ParameterizedTest
    //@ValueSource(ints = {10, 0, 15, 20})
    fun test_validate_invalid() {
        val validValue = "aboba"

        val errorUrl = emailValidator.validate(validValue)

        assertNull(errorUrl)
    }
}