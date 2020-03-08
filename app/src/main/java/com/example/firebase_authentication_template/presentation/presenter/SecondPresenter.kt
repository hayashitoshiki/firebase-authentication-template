package com.example.firebase_authentication_template.presentation.presenter

import com.example.firebase_authentication_template.domain.AuthenticationUseCase
import com.example.firebase_authentication_template.presentation.contact.SecondContact

class SecondPresenter(private val view: SecondContact.View, private val authUseCase:AuthenticationUseCase):
    SecondContact.Presenter {

    //ログアウト
    override fun signOut() {
        authUseCase.signOut({
            view.signOut()
        },{
            view.showErrorToast()
        })
    }

    //ユーザー削除
    override fun deleteAccount(){
        authUseCase.delete({
            view.deleteAccount()
        },{
            view.showErrorToast()
        })
    }
    //パスワード設定
    override fun setPassword(){
        authUseCase.changePassword({
            view.setPassword()
        },{
            view.showErrorToast()
        })
    }
}