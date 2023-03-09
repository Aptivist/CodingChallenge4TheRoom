package com.aptivist.roomchallengeone.di

import com.aptivist.roomchallengeone.data.remote.repositories.MemeRepository
import com.aptivist.roomchallengeone.domain.IMemeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IMemeRepository>{
        MemeRepository(get(), getProperty("BASE_URL", "https://localhost/"))
    }
}