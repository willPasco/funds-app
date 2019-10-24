package com.android.fundsapp.fundslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundsapp.AppApplication
import com.android.fundsapp.R
import com.android.fundsapp.domain.FundsListView
import com.android.fundsapp.domain.presenter.FundsListPresenter
import javax.inject.Inject

class FundsListActivity : AppCompatActivity(), FundsListView {

    @Inject
    lateinit var presenter: FundsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funds_list)

        AppApplication.getMainComponent().inject(this)

        presenter.bind(this)
        presenter.getFunds()
    }
}
