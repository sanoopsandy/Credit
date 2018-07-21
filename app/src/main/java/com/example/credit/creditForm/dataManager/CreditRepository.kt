package com.example.credit.creditForm.dataManager

import com.example.credit.core.networking.DataResult
import com.example.credit.utils.*
import com.google.gson.JsonElement
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class CreditRepository(private val remote: CreditBluePrint.Remote,
                       private val compositeDisposable: CompositeDisposable) : CreditBluePrint.Repository {

    override val postSubmitCreditModelDataResult: PublishSubject<DataResult<JsonElement>> = PublishSubject.create<DataResult<JsonElement>>()

    override fun submitCreditInfo(creditModel: CreditModel) {
        postSubmitCreditModelDataResult.loading(true)
        remote.submitCredit(creditModel)
                .doOnBackOutOnMain()
                .subscribe({ response ->
                    postSubmitCreditModelDataResult.success(response)
                }, { handleError(it) })
                .addTo(compositeDisposable)
    }

    override fun handleError(error: Throwable) {
        postSubmitCreditModelDataResult.failure(error)
    }
}