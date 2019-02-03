package com.joelparrish.graphene.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_text_card.view.*

class TextCellHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(text: String) {
        itemView.text.text = text
    }
}