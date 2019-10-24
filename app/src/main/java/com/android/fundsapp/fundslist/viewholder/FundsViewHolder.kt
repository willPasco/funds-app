package com.android.fundsapp.fundslist.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.fundsapp.R
import com.android.fundsapp.data.entity.FundResponse
import com.android.fundsapp.fundslist.adapter.FundsRecyclerAdapter

class FundsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val textViewFundTitle by lazy { view.findViewById<TextView>(R.id.text_view_funds_title) }
    private val textViewMinimumApplication by lazy { view.findViewById<TextView>(R.id.text_view_minimum_application) }

    fun bind(model: FundResponse, listener: FundsRecyclerAdapter.OnItemClicked) {

        view.setOnClickListener {
            listener.onClick()
        }

        textViewFundTitle.text = model.simpleName

        val minimumApplicationText =
            textViewMinimumApplication.context.getString(R.string.minimum_application) + "\n${model.operability.minimumApplication}"
        textViewMinimumApplication.text = minimumApplicationText
    }

}
