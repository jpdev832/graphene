package com.jparrish.graphene.cell

import android.view.View
import com.jparrish.graphene.R
import com.jparrish.graphene.core.CellModel
import com.jparrish.graphene.holder.DetailCellHolder
import com.jparrish.graphene.model.Detail

class DetailCellModel(val detail: Detail, val clickListener: (DetailCellModel) -> Unit) :
    CellModel<DetailCellHolder>(R.layout.cell_detail_card, partialBindEnabled = true) {
    var showArea = false

    override fun createItemViewHolder(view: View): DetailCellHolder = DetailCellHolder(view)

    override fun partialBindItemViewHolder(holder: DetailCellHolder, absPosition: Int) {
        holder.showArea(showArea)
    }

    override fun bindItemViewHolder(holder: DetailCellHolder, absPosition: Int) {
        holder.bind(detail.title, detail.description) { clickListener(this) }
        holder.showArea(showArea)
    }

    override fun getContentHashCode(): Int = showArea.hashCode()
}