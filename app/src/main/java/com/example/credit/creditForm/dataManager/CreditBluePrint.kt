package com.example.credit.creditForm.dataManager

import android.arch.lifecycle.LiveData
import com.example.credit.core.networking.DataResult
import com.google.gson.JsonElement
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject

interface CreditBluePrint {
    interface Repository {
        val postSubmitCreditModelDataResult: PublishSubject<DataResult<JsonElement>>
        val creditLiveData: LiveData<DataResult<JsonElement>>
        fun submitCreditInfo(creditModel: CreditModel)
        fun handleError(error: Throwable)
    }

    interface Remote {
        fun submitCredit(creditModel: CreditModel): Flowable<JsonElement>
    }
}