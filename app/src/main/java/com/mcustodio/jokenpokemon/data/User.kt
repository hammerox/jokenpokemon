package com.mcustodio.jokenpokemon.data

data class User(
    var email: String,
    var fullName: String,
    var nickname: String,
    var gender: Gender,
    var password: String

) {
    enum class Gender {
        Male, Female
    }
}