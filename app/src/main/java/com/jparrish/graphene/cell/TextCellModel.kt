package com.jparrish.graphene.cell

import android.view.View
import com.jparrish.graphene.R
import com.jparrish.graphene.core.CellModel
import com.jparrish.graphene.holder.TextCellHolder

class TextCellModel(var text: String) : CellModel<TextCellHolder>(R.layout.cell_text_card) {
    override fun createItemViewHolder(view: View): TextCellHolder = TextCellHolder(view)

    override fun bindItemViewHolder(holder: TextCellHolder, absPosition: Int) {
        holder.bind(text)
    }

    override fun getContentHashCode(): Int = text.hashCode()
}