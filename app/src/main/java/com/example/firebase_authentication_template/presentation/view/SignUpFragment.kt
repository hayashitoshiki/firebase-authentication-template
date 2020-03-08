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

class SignUpFragment : Fragment() {

    private lateinit var mEmailField: EditText
    private lateinit var mPasswordField: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        mEmailField = view.findViewById(R.id.email_edit_text)
        mPasswordField = view.findViewById(R.id.password_edit_text)

        //新規作成ボタン
        val button = view.findViewById(R.id.sign_up_button) as Button
        button.setOnClickListener {
            presenter.signUp(mEmailField.text.toString(),mPasswordField.text.toString())
        }
        return view
    }


    companion object {
        private lateinit var presenter: MainContact.Presenter

        @JvmStatic
        fun newInstance(mPresemter: MainContact.Presenter): SignUpFragment {
            val fragment = SignUpFragment()
            presenter = mPresemter
            return fragment
        }
    }

}