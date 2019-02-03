package com.jparrish.graphene.core

import androidx.recyclerview.widget.DiffUtil

class DefaultCallbackAdapter : CallbackAdapter {
    override fun calculate(diffCallback: DiffUtil.Callback, resultCallback: (DiffUtil.DiffResult) -> Unit) {
        resultCallback(DiffUtil.calculateDiff(diffCallback))
    }

    override fun cancel() {
    }
}