package com.example.data.datasource

import android.content.Context
import com.example.data.storages.CacheSharedPreferences
import com.example.data.storages.models.RegistrationKeysModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

abstract class SharedPreferencesDataSource @Inject constructor(@ApplicationContext context: Context) :
    CacheSharedPreferences.Mutable {

    val keys = "API_KEYS"

    private val sharedPreferences = context.getSharedPreferences(keys, Context.MODE_PRIVATE)

    override fun<V> saveKey(key: String, value: V) {
        sharedPreferences.edit()
            .putString(RegistrationKeysModel.CLIENT_ID, value.toString()).apply()
    }

    override fun<V> saveKeys(keys: Map<String, V>) {
        val editSharedPreferences = sharedPreferences.edit()
        for (key in keys)
            editSharedPreferences.putString(key.key, key.value.toString())

        editSharedPreferences.apply()
    }

    override fun readKeys(keys: List<String>): Map<String, String?> {
        val gotKeys = mutableMapOf<String, String?>()
        for (key in keys)
            gotKeys[key] = sharedPreferences.getString(key, null)

        return gotKeys
    }

    override fun readByKey(key: String) =
        sharedPreferences.getString(key, null).toString()
}