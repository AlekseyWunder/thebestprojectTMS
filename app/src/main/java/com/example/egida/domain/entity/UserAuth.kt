package com.example.egida.domain.entity

class UserAuth {
    var email: String
    var password: String

    constructor(email: String, password: String) {
        this.email = email
        this.password = password
    }
}