package com.example.egida.data.localSource

import com.example.egida.domain.entity.User
import com.example.egida.domain.useCase.localsource.LocalSourceUserRepository

class LocalSourceUser : LocalSourceUserRepository {
    override var localUser: User = User()
}