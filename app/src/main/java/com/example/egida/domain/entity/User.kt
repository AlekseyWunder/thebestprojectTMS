package com.example.egida.domain.entity

data class User(
    var id: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var checkAgreement: Boolean = false,
    var phoneNumber: String = "",
    var photoURL: String = "",
    var height: Int = 180,
    var weight: Int = 80
)
