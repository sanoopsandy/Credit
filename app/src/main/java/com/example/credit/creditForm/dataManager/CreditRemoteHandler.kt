package com.example.credit.creditForm.dataManager

import com.example.credit.core.networking.PostService
import com.google.gson.JsonElement
import io.reactivex.Flowable

class CreditRemoteHandler(private val postService: PostService) : CreditBluePrint.Remote {
    override fun submitCredit(creditModel: CreditModel): Flowable<JsonElement> {
        return postService.submitCreditDetails(creditModel)
    }
}