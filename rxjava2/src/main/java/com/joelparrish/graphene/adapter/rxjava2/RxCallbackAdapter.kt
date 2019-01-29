package com.joelparrish.graphene.adapter.rxjava2

import androidx.recyclerview.widget.DiffUtil
import com.joelparrish.graphene.core.CallbackAdapter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

class RxCallbackAdapter : CallbackAdapter {
    private var disposable = Disposables.disposed()

    override fun calculate(diffCallback: DiffUtil.Callback, resultCallback: (DiffUtil.DiffResult) -> Unit) {
        disposable.dispose()
        disposable = Single.fromCallable { DiffUtil.calculateDiff(diffCallback) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> resultCallback(result) }
    }

    override fun cancel() {
        disposable.dispose()
    }
}