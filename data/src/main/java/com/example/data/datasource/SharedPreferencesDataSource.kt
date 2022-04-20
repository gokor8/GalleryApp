package com.example.data.datasource

import android.content.Context
import com.example.data.storages.CacheService
import com.example.data.storages.RegistrationKeysModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesDataSource @Inject constructor(@ApplicationContext context: Context) : CacheService {

    val keys = "KEYS"

    private val sharedPreferences = context.getSharedPreferences(keys, Context.MODE_PRIVATE)

    override fun saveKey(key: String, value: String) {
        sharedPreferences.edit()
            .putString(RegistrationKeysModel.CLIENT_ID, value).apply()
    }

    override fun saveKeys(keys: Map<String, String>) {
        val editSharedPreferences = sharedPreferences.edit()
        for (key in keys)
            editSharedPreferences.putString(key.key, key.value)

        editSharedPreferences.apply()
    }

    override fun getKeys(keys: List<String>): Map<String,String?> {
        val gotKeys = mutableMapOf<String, String?>()
        for (key in keys)
            gotKeys[key] = sharedPreferences.getString(key, null)

        return gotKeys
    }

    override fun getKey(key: String) =
        sharedPreferences.getString(key, null).toString()
}