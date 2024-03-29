package com.android.fundsapp.fundslist.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.fundsapp.R
import com.android.fundsapp.data.entity.FundResponse
import com.android.fundsapp.fundslist.adapter.FundsRecyclerAdapter
import java.text.NumberFormat


class FundsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val textViewFundTitle by lazy { view.findViewById<TextView>(R.id.text_view_funds_title) }
    private val textViewMinimumApplication by lazy { view.findViewById<TextView>(R.id.text_view_minimum_application) }
    private val layoutContainer by lazy { view.findViewById<View>(R.id.layout_container) }

    fun bind(model: FundResponse, listener: FundsRecyclerAdapter.OnItemClicked) {

        layoutContainer.setOnClickListener {
            listener.onClick()
        }

        textViewFundTitle.text = model.simpleName
        val currency = formatCurrency(model.operability.minimumApplication)

        val minimumApplicationText =
            textViewMinimumApplication.context.getString(R.string.minimum_application) + "\nR$currency"
        textViewMinimumApplication.text = minimumApplicationText
    }

    private fun formatCurrency(minimumApplication: Double): String {
        val formatter = NumberFormat.getCurrencyInstance()
        return formatter.format(minimumApplication)
    }

}
