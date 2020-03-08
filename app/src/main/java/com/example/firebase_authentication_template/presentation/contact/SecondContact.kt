package com.example.firebase_authentication_template.presentation.contact

interface SecondContact {
    interface View {
        //エラートースト表示
        fun showErrorToast()
        //ログアウト(トースト表示)
        fun signOut()
        //ユーザー削除(トースト表示)
        fun deleteAccount()
        //パスワード再設定(トースト表示)
        fun setPassword()
    }

    interface Presenter {
        //ログアウト
        fun signOut()
        //ユーザー削除
        fun deleteAccount()
        //パスワード設定
        fun setPassword()
    }
}