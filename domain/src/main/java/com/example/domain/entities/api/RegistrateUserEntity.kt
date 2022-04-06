package com.example.domain.entities.api

class RegistrateUserEntity : ArrayList<RegistrateUserEntityItem>() {

    companion object {
        inline fun <S> map(
            source: S,
            mapLogic: (source: S) -> RegistrateUserEntityItem
        ): RegistrateUserEntity {
            val registrateUserModel = RegistrateUserEntity()
            registrateUserModel[0] = mapLogic(source)

            return registrateUserModel
        }
    }
}