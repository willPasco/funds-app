package com.android.fundsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundsapp.domain.FundsListView
import com.android.fundsapp.domain.presenter.FundsListPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), FundsListView {

    @Inject
    lateinit var presenter: FundsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppApplication.getMainComponent().inject(this)

        presenter.bind(this)
        presenter.getFunds()
    }
}
