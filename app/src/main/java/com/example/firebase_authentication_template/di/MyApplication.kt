package com.example.firebase_authentication_template.di

import android.app.Application
import com.example.firebase_authentication_template.data.FirebaseRepository
import com.example.firebase_authentication_template.data.FirebaseRepositoryImp
import com.example.firebase_authentication_template.domain.AuthenticationUseCase
import com.example.firebase_authentication_template.domain.AuthenticationUseCaseImp
import com.example.firebase_authentication_template.presentation.contact.MainContact
import com.example.firebase_authentication_template.presentation.contact.SecondContact
import com.example.firebase_authentication_template.presentation.presenter.MainPresenter
import com.example.firebase_authentication_template.presentation.presenter.SecondPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(applicationContext)
            modules(module)
        }
    }

    // Koinモジュール
    private val module: Module = module {
        factory <MainContact.Presenter>{ (v: MainContact.View) -> MainPresenter(v, get()) }
        factory <SecondContact.Presenter>{ (v: SecondContact.View) -> SecondPresenter(v, get()) }

        factory <AuthenticationUseCase>{ AuthenticationUseCaseImp(get()) }

        factory <FirebaseRepository>{ FirebaseRepositoryImp() }

    }
}


