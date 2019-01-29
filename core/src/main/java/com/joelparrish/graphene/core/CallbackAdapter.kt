package com.joelparrish.graphene.core

import androidx.recyclerview.widget.DiffUtil

interface CallbackAdapter {
    fun calculate(diffCallback: DiffUtil.Callback, resultCallback: (DiffUtil.DiffResult) -> Unit)

    fun cancel()
}