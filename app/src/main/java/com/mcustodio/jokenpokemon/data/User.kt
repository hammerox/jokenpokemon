package com.mcustodio.jokenpokemon.data

data class User(
    var email: String,
    var password: String,
    var fullName: String,
    var nickname: String,
    var gender: Gender

) {
    enum class Gender {
        Male, Female
    }
}