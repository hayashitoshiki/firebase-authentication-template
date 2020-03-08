package com.example.firebase_authentication_template.presentation.presenter

import com.example.firebase_authentication_template.domain.AuthenticationUseCase
import com.example.firebase_authentication_template.presentation.contact.MainContact

class MainPresenter(private val view:MainContact.View, private val authUseCase:AuthenticationUseCase): MainContact.Presenter {

    //ログイン認証
    override fun onStart(){
        authUseCase.firstCheck({
            view.changeView()
        },{

        })
    }

    //ログイン処理
    override fun signIn(email:String, password:String){
        if(email != "" && password != ""){
            authUseCase.signIn(email,password,{
                view.changeView()
            },{
                view.showErrorToast()
            })
        } else{
            view.showErrorEmailPassword()
        }
    }

    //新規作成処理
    override fun signUp(email:String, password:String){
        if(email != "" && password != "") {
            authUseCase.signUp(email, password, {
                view.changeView()
            }, {
                view.showErrorToast()
            })
        }else {
            view.showErrorEmailPassword()
        }
    }
}