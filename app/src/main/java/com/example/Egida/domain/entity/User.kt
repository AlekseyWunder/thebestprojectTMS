package com.example.Egida.domain.entity

class User {
   // private lateinit var login: String
    lateinit var email: String
    lateinit var password: String

    constructor(/*login: String*/ email: String, password: String) {
       // this.login = login
        this.email = email
        this.password = password
    }
    constructor(){}
}