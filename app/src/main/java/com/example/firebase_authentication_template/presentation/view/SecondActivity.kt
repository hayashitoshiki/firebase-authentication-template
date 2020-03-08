package com.example.firebase_authentication_template.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.firebase_authentication_template.R
import com.example.firebase_authentication_template.presentation.contact.SecondContact
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class SecondActivity : AppCompatActivity(), SecondContact.View{

    private val presenter: SecondContact.Presenter by inject { parametersOf(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_out)
    }

    //ログアウトボタン
    fun signOutButton(view:View){
        presenter.signOut()
    }
    //ユーザー削除ボタン
    fun deleteButton(view:View){
        presenter.deleteAccount()
    }
    //ユーザー削除ボタン
    fun setPasswordButton(view:View){
        presenter.setPassword()
    }

    //ログイン画面へ遷移する
    private fun changeView(){
        val intent = Intent(application, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    //エラートースト表示
    override fun showErrorToast() {
        Toast.makeText(applicationContext, "通信環境の良いところでお試しください", Toast.LENGTH_LONG).show()
    }

    //ログアウトトースト表示
    override fun signOut(){
        Toast.makeText(applicationContext, "ログアウト", Toast.LENGTH_LONG).show()
        changeView()
    }

    //ユーザー削除トースト表示
    override fun deleteAccount(){
        Toast.makeText(applicationContext, "ユーザー削除", Toast.LENGTH_LONG).show()
        changeView()
    }

    //パスワード設定トースト表示
    override fun setPassword(){
        Toast.makeText(applicationContext, "パスワード設定メール送信", Toast.LENGTH_LONG).show()
    }
}
