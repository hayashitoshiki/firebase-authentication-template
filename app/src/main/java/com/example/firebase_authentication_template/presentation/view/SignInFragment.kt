package com.example.firebase_authentication_template.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import com.example.firebase_authentication_template.R
import com.example.firebase_authentication_template.presentation.contact.MainContact

class SignInFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        val mEmailField = view.findViewById(R.id.email_edit_text) as EditText
        val mPasswordField = view.findViewById(R.id.password_edit_text) as EditText


        //ログインボタン
        val button = view.findViewById(R.id.sign_in_button) as Button
        button.setOnClickListener {
            presenter.signIn(mEmailField.text.toString(),mPasswordField.text.toString())
        }
        return view
    }

    companion object {
        private lateinit var presenter: MainContact.Presenter

        @JvmStatic
        fun newInstance(mPresemter:MainContact.Presenter): SignInFragment {
            val fragment = SignInFragment()
            presenter = mPresemter
            return fragment
        }
    }


}
