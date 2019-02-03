package com.joelparrish.graphene.cell

import android.view.View
import com.joelparrish.graphene.R
import com.joelparrish.graphene.core.CellModel
import com.joelparrish.graphene.holder.TextCellHolder

class TextCellModel(var text: String) : CellModel<TextCellHolder>(R.layout.cell_text_card) {
    override fun createItemViewHolder(view: View): TextCellHolder = TextCellHolder(view)

    override fun bindItemViewHolder(holder: TextCellHolder, absPosition: Int) {
        holder.bind(text)
    }

    override fun getContentHashCode(): Int = text.hashCode()
}