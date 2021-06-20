package com.example.Egida.domain.entity

class UserDB {
    lateinit var id: String
    lateinit var login: String
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var phoneNumber: String
    lateinit var photo: String
    lateinit var height: String
    lateinit var weight: String

    constructor(id: String, login: String) {
        this.id = id
        this.login = login
    }

    constructor(
        id: String,
        login: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        photo: String,
        height: String,
        weight: String
    ) {
        this.id = id
        this.login = login
        this.firstName = firstName
        this.lastName = lastName
        this.phoneNumber = phoneNumber
        this.photo = photo
        this.height = height
        this.weight = weight
    }

}