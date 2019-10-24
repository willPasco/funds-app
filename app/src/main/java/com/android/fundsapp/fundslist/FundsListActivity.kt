package com.android.fundsapp.fundslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.fundsapp.AppApplication
import com.android.fundsapp.R
import com.android.fundsapp.data.entity.FundResponse
import com.android.fundsapp.domain.FundsListView
import com.android.fundsapp.domain.presenter.FundsListPresenter
import kotlinx.android.synthetic.main.activity_funds_list.*
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


    override fun showData(dataList: List<FundResponse>){
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = FundsRecyclerAdapter(dataList)
    }
}
