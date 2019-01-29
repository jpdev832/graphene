package com.joelparrish.graphene.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class CellModel<T : androidx.recyclerview.widget.RecyclerView.ViewHolder>(var itemResourceId: Int) {
    /**
     * Create a unique identifier for the cell model.
     */
    var id: String = UUID.randomUUID().toString()

    /**
     * Creates a new view holder for this cell model.
     *
     * This method is called from [createViewHolder].
     *
     * @param view parent view obtained from the [DynamicAdapter]
     * @return cell model view holder
     */
    abstract fun createItemViewHolder(view: View): T

    /**
     * Binds view holder when only specific elements have changed. The payload includes the data
     * that was set in [getChangePayload].
     *
     * This method is called from [bindViewHolder].
     *
     * @param holder cell model view holder
     * @param absPosition absolute position in the [RecyclerView]
     * @param payloads payload obtained from [getChangePayload]
     * @return boolean representing whether the bind process was handled
     */
    abstract fun bindItemViewHolder(holder: T, absPosition: Int, payloads: MutableList<Any>): Boolean

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
     * Get the view type id that represents this cell models specific view type.
     *
     * @return view type of cell model
     */
    abstract fun getViewTypeId(): Int

    /**
     * Bind method called from the [DynamicAdapter] when a payload is available.
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
        return bindItemViewHolder(holder as T, absPosition, payloads)
    }

    /**
     * Bind method called from the [DynamicAdapter] when no payload is available.
     *
     * @param holder the [RecyclerView.ViewHolder] instance for this cell model
     * @param absPosition absolute position in the [RecyclerView]
     */
    @Suppress("UNCHECKED_CAST")
    fun bindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, absPosition: Int) {
        bindItemViewHolder(holder as T, absPosition)
    }

    /**
     * Creates a new view holder when [DynamicAdapter] needs a new instance.
     *
     * @param parent parent view group
     * @return view holder for the cell model
     */
    fun createViewHolder(parent: ViewGroup): T {
        val view = LayoutInflater.from(parent.context).inflate(itemResourceId, parent, false)
        return createItemViewHolder(view)
    }

    /**
     * Unique hash code representing the content held by the cell model.
     *
     * This method is used by [DynamicCellDiffCallback.areContentsTheSame] to determine if the cell
     * has change at all.
     *
     * @return hash code representing the data held by the model
     */
    open fun getContentHashCode(): Int = 0

    /**
     * Get a payload that contains elements that were changed. By default, this will return null.
     * Forcing the adapter to call bindViewHolder without a payload.
     *
     * @return a data object representing the differences between this model and the new model
     */
    open fun getChangePayload(): Any? {
        return null
    }
}