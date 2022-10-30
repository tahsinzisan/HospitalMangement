package com.example.hospital

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest{
    fun whenInputIsValid(){
        val name = ""
        val pass=""

        val result= Validator.validateInput(name, pass)
        //assertThat(result).isEqualTo(true)
    }
}