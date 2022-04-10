package com.example.data.datasource

import android.content.Context
import com.example.data.storages.CacheService
import com.example.data.storages.KeysEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesDataSource @Inject constructor(@ApplicationContext context: Context) : CacheService {

    val keys = "KEYS"

    private val sharedPreferences = context.getSharedPreferences(keys, Context.MODE_PRIVATE)

    override fun saveKeys(keys: KeysEntity) {
        sharedPreferences.edit()
            .putString(KeysEntity.CLIENT_ID, keys.clientId)
            .putString(KeysEntity.RANDOM_ID, keys.randomId)
            .putString(KeysEntity.SECRET, keys.secret)
            .apply()
    }

    override fun getKeys() =
        KeysEntity(
            sharedPreferences.getString(KeysEntity.CLIENT_ID, null).toString(),
            sharedPreferences.getString(KeysEntity.RANDOM_ID, "Not Found").toString(),
            sharedPreferences.getString(KeysEntity.SECRET, "Not Found").toString()
        )

    override fun refreshKeys() {
        TODO("Not yet implemented")
    }
}