package com.android.fundsapp.core

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundsapp.AppApplication
import javax.inject.Inject

@SuppressLint("Registered")
class BaseActivity : AppCompatActivity(), BaseView {

    @Inject
    lateinit var presenter: BasePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppApplication.getMainComponent().inject(this)
        presenter.bind(this)
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }
}