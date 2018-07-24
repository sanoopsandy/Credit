package com.example.credit.creditForm.dataManager

import android.arch.lifecycle.MutableLiveData
import com.example.credit.core.networking.DataResult
import com.example.credit.utils.*
import com.google.gson.JsonElement
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class CreditRepository(private val remote: CreditBluePrint.Remote,
                       private val compositeDisposable: CompositeDisposable) : CreditBluePrint.Repository {

    override val postSubmitCreditModelDataResult: PublishSubject<DataResult<JsonElement>> = PublishSubject.create<DataResult<JsonElement>>()

    override val creditLiveData: MutableLiveData<DataResult<JsonElement>> = MutableLiveData()

    override fun submitCreditInfo(creditModel: CreditModel) {
        creditLiveData.loading(true)
        remote.submitCredit(creditModel)
                .doOnBackOutOnMain()
                .subscribe({ response ->
                    creditLiveData.success(response)
                }, { handleError(it) })
                .addTo(compositeDisposable)
    }

    override fun handleError(error: Throwable) {
        postSubmitCreditModelDataResult.failure(error)
    }
}