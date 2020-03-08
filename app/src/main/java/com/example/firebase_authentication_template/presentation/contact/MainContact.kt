package com.example.firebase_authentication_template.presentation.contact

interface MainContact {
    interface View {
        //ログイン後画面へ遷移
        fun changeView()
        //エラー表示(Email or Password)
        fun showErrorEmailPassword()
        //エラートースト表示
        fun showErrorToast()
    }

    interface Presenter {
        //ログインチェック
        fun onStart()
        //ログイン処理(repositoryへ受け渡し)
        fun signIn(email:String, password:String)
        //新規作成処理(repositoryへ受け渡し)
        fun signUp(email:String, password:String)
    }
}