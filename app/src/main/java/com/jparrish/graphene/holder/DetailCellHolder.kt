package com.jparrish.graphene.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_detail_card.view.*

class DetailCellHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(title: String, description: String, listener: () -> Unit) {
        itemView.title.text = title
        itemView.description.text = description
        itemView.setOnClickListener { listener() }
    }

    fun showArea(showArea: Boolean) {
        if (showArea) {
            itemView.area.visibility = View.VISIBLE
        } else {
            itemView.area.visibility = View.GONE
        }
    }
}