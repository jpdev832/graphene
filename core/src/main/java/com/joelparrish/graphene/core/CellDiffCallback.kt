package com.joelparrish.graphene.core

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CellDiffCallback(
    private val oldModels: List<Pair<Any, Int>>,
    private val newModels: List<CellModel<out RecyclerView.ViewHolder>>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldModels.size
    override fun getNewListSize() = newModels.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldModels[oldItemPosition].first === newModels[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldModels[oldItemPosition].second == newModels[newItemPosition].getContentHashCode()
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return newModels[newItemPosition].getChangePayload()
    }
}