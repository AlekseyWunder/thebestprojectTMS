package com.example.egida

object Constants {
    //value entity Error code AUTH
    const val errorLoginCustomToken =
        "The custom token format is incorrect. Please check the documentation."
    const val errorLoginCustomTokenMismatch =
        "The custom token corresponds to a different audience."
    const val errorLoginCredentialMalformedOrExpired =
        "The supplied auth credential is malformed or has expired."
    const val errorLoginInvalidEmail = "The email address is badly formatted."
    const val errorLoginWrongPassword =
        "The password is invalid or the user does not have a password."
    const val errorLoginUserMismatch =
        "The supplied credentials do not correspond to the previously signed in user."
    const val errorLoginRequiresRecentLogin =
        "This operation is sensitive and requires recent authentication. Log in again before retrying this request."
    const val errorLoginAccountsExitsWithDifferentCredential =
        "An account already exists with the same email address but different sign -in credentials . Sign in using a provider associated with this email address."
    const val errorLoginEmailAlreadyInUse =
        "The email address is already in use by another account ."
    const val errorLoginCredentialAlreadyInUse =
        "This credential is already associated with a different user account."
    const val errorLoginUserDisabled =
        "The user account has been disabled by an administrator."
    const val errorLoginUserNotFound =
        "There is no user record corresponding to this identifier. The user may have been deleted."
    const val errorLoginOperationNotAllowed =
        "This operation is not allowed.You must enable this service in the console."
    const val errorLoginPasswordIsWeak = "The given password is invalid."
    const val errorLoginUserTokenExpired =
        "The user \'s credential is no longer valid. The user must sign in again"
    const val errorLoginInvalidUserToken =
        "The user \'s credential is no longer valid. The user must sign in again."
    const val registrationConfirmation = "Проверьте вашу почту для подтверждения регистрации"
    const val notNetworkConnect = "Проверьте подключение к сети"
    const val passwordRecovery = "Письмо для востановления пороля отправлено на почту"

    // value entity User
    const val id = ""
    const val firstName = ""
    const val lastName = ""
    const val checkAgreement = false
    const val phoneNumber: String = ""
    const val photoURL: String = ""
    const val height: Int = 180
    const val weight: Int = 80

    //value entity Day
    const val baseValueScoreBal: Int = 1000
    const val baseValueWork: Int = 8
    const val baseValueLeisure: Int = 3
    const val baseValueMeal: Int = 2000
    const val baseValueWater: Int = 180
    const val baseValueAlcohol: Int = 0
    const val baseValueRunning: Int = 0
    const val baseValueBikeRide: Int = 0
    const val baseValueSleep: Int = 7

}