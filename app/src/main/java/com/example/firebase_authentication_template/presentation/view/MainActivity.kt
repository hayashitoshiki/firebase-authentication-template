package com.example.firebase_authentication_template.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.firebase_authentication_template.R
import com.example.firebase_authentication_template.presentation.contact.MainContact
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainContact.View {

    private val presenter:MainContact.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //自動ログイン認証
    public override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    //ログイン後画面へ遷移する
    override fun changeView(){
        val intent = Intent(application, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }

    //ログイン切り替えボタン
    fun signInButton(view: View){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, SignInFragment.newInstance(presenter))
            .commit()
    }

    //新規作成切り替えボタン
    fun signUpButton(view: View){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, SignUpFragment.newInstance(presenter))
            .commit()
    }

    //エラー表示
    override fun showErrorEmailPassword(){
        Toast.makeText(applicationContext, "EmailとPasswordを入力してください", Toast.LENGTH_LONG).show()
    }
    //エラー表示
    override fun showErrorToast() {
        Toast.makeText(applicationContext, "通信環境の良いところでお試しください", Toast.LENGTH_LONG).show()
    }
}
