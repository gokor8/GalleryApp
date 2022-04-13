package com.example.galleryapp.ui_displays

import android.content.Context
import com.example.galleryapp.validators.validators_impl.EmailSingleValidator
import com.example.galleryapp.validators.ParsableCloudValidator
import com.example.galleryapp.validators.validators_impl.PasswordSingleValidator
import com.example.galleryapp.validators.validators_impl.UsernameParsableValidator
import dagger.hilt.android.qualifiers.ApplicationContext

open class BaseServerErrorParser(@ApplicationContext private val context: Context) {

    protected val parsableCloudValidator: List<ParsableCloudValidator> = listOf(
        UsernameParsableValidator(""),
        EmailSingleValidator(""),
        PasswordSingleValidator("")
    )

    fun parse(error: String): Map<ParsableCloudValidator, String> {
        val map = mutableMapOf<ParsableCloudValidator, String>()

        val errors = error.split('.')
        for (validator in parsableCloudValidator) {
            map[validator] = ""

            validator.errorTrigger.let { trigger ->
                errors.firstOrNull {
                    it.contains(trigger)
                }?.apply {
                    if (validator.myErrorResponse != null)
                        map[validator] = context.resources.getString(validator.myErrorResponse!!)
                    else
                        map[validator] = substringAfter(trigger)
                }
            }
        }

        return map
    }

}