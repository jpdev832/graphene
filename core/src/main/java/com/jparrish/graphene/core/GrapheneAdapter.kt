package com.jparrish.graphene.core

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class GrapheneAdapter constructor(
    private val callbackAdapter: CallbackAdapter = DefaultCallbackAdapter()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), LifecycleObserver {

    val cellModels = ArrayList<CellModel<out RecyclerView.ViewHolder>>()

    private val cellTypeMap = SparseArrayCompat<CellModel<out RecyclerView.ViewHolder>>()
    private val stateMap = mutableListOf<Pair<String, Int>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return cellTypeMap.get(viewType)!!.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (!payloads.isEmpty() && cellModels[position].bindViewHolder(holder, position, payloads)) {
            return
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        cellModels[position].bindViewHolder(holder, position)
    }

    override fun getItemCount() = cellModels.size
    override fun getItemViewType(position: Int) = cellModels[position].getViewTypeId()

    fun updateCells(newModels: List<CellModel<out RecyclerView.ViewHolder>>) {
        callbackAdapter.calculate(CellDiffCallback(stateMap, newModels)) { onResult(it, newModels) }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun cancel() {
        callbackAdapter.cancel()
    }

    private fun onResult(
        diffResult: DiffUtil.DiffResult,
        newModels: List<CellModel<out RecyclerView.ViewHolder>>
    ) {
        this.cellModels.clear()
        this.cellTypeMap.clear()
        this.stateMap.clear()

        newModels.forEach {
            this.cellModels.add(it)
            this.cellTypeMap.put(it.getViewTypeId(), it)
            this.stateMap.add(Pair(it.id, it.getContentHashCode()))
        }

        diffResult.dispatchUpdatesTo(this)
    }
}
