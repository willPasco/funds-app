package com.android.fundsapp.fundslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.fundsapp.R
import com.android.fundsapp.data.entity.FundResponse
import com.android.fundsapp.fundslist.viewholder.FundsViewHolder

class FundsRecyclerAdapter(private val fundsList: List<FundResponse>, private val listener: OnItemClicked) : RecyclerView.Adapter<FundsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.funds_item, parent, false)

        return FundsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fundsList.size
    }

    override fun onBindViewHolder(holder: FundsViewHolder, position: Int) {
        holder.bind(fundsList[position], listener)
    }

    interface OnItemClicked{
        fun onClick()
    }
}