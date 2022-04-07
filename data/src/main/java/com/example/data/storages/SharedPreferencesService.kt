package com.example.data.storages

import android.content.Context
import android.provider.Settings.Global.getString
import com.example.data.R

class SharedPreferencesService(context: Context) : CacheService {

    val keys = "KEYS"

    private val sharedPreferences = context.getSharedPreferences(keys, Context.MODE_PRIVATE)

    override fun saveKeys(keys: KeysEntity) {
        sharedPreferences.edit()
            .putString(KeysEntity.RANDOM_ID, keys.randomId)
            .putString(KeysEntity.SECRET, keys.secret)
            .apply()
    }

    override fun getKeys() =
        KeysEntity(
            sharedPreferences.getString(KeysEntity.RANDOM_ID, "Not Found").toString(),
            sharedPreferences.getString(KeysEntity.SECRET, "Not Found").toString()
        )
}