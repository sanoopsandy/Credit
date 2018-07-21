package com.example.credit.core.networking

import com.example.credit.creditForm.dataManager.CreditModel
import com.example.credit.creditForm.dataManager.CreditResponse
import com.google.gson.JsonElement
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST

interface PostService {
    @POST("app/dummy-card-details/submit")
    fun submitCreditDetails(@Body creditModel: CreditModel): Flowable<JsonElement>
}