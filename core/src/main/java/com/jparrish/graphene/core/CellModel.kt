package com.jparrish.graphene.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class CellModel<T : androidx.recyclerview.widget.RecyclerView.ViewHolder>(
    var itemResourceId: Int,
    val partialBindEnabled: Boolean = false
) {
    /**
     * Default payload used to trigger partial bindings
     */
    companion object {
        val PAYLOAD = object {}
    }

    /**
     * Create a unique identifier for the cell model.
     */
    var id: String = UUID.randomUUID().toString()

    /**
     * Creates a new view holder for this cell model.
     *
     * This method is called from [createViewHolder].
     *
     * @param view parent view obtained from the [GrapheneAdapter]
     * @return cell model view holder
     */
    abstract fun createItemViewHolder(view: View): T

    /**
     * Binds view holder when only specific elements have changed.
     *
     * This method is called from [bindViewHolder].
     *
     * @param holder cell model view holder
     * @param absPosition absolute position in the [RecyclerView]
     */
    open fun partialBindItemViewHolder(holder: T, absPosition: Int) {
        bindViewHolder(holder, absPosition)
    }

    /**
     * Binds view holder.
     *
     * This method is called from [bindViewHolder].
     *
     * @param holder cell model view holder
     * @param absPosition absolute position in the [RecyclerView]
     */
    abstract fun bindItemViewHolder(holder: T, absPosition: Int)

    /**
     * Bind method called from the [GrapheneAdapter] when a payload is available.
     *
     * @param holder the [RecyclerView.ViewHolder] instance for this cell model
     * @param absPosition absolute position in the [RecyclerView]
     * @param payloads payload obtained from [getChangePayload]
     * @return boolean representing whether the bind process was handled
     */
    @Suppress("UNCHECKED_CAST")
    fun bindViewHolder(
        holder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
        absPosition: Int,
        payloads: MutableList<Any>
    ): Boolean {
        return if (partialBindEnabled) {
            partialBindItemViewHolder(holder as T, absPosition)
            true
        } else false
    }

    /**
     * Bind method called from the [GrapheneAdapter] when no payload is available.
     *
     * @param holder the [RecyclerView.ViewHolder] instance for this cell model
     * @param absPosition absolute position in the [RecyclerView]
     */
    @Suppress("UNCHECKED_CAST")
    fun bindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, absPosition: Int) {
        bindItemViewHolder(holder as T, absPosition)
    }

    /**
     * Creates a new view holder when [GrapheneAdapter] needs a new instance.
     *
     * @param parent parent view group
     * @return view holder for the cell model
     */
    fun createViewHolder(parent: ViewGroup): T {
        val view = LayoutInflater.from(parent.context).inflate(itemResourceId, parent, false)
        return createItemViewHolder(view)
    }

    /**
     * Get a payload that that triggers a partial bind.
     *
     * @return a data object representing a change in data
     */
    fun getChangePayload(): Any? {
        return if (partialBindEnabled) PAYLOAD else null
    }

    /**
     * Get the view type id that represents this cell models specific view type.
     *
     * @return view type of cell model
     */
    open fun getViewTypeId(): Int = itemResourceId

    /**
     * Unique hash code representing the content held by the cell model.
     *
     * This method is used by [CellDiffCallback.areContentsTheSame] to determine if the cell
     * has change at all.
     *
     * @return hash code representing the data held by the model
     */
    open fun getContentHashCode(): Int = 0
}