package com.example.credit.creditForm.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.credit.core.di.DIHandler
import com.example.credit.core.networking.DataResult
import com.example.credit.creditForm.dataManager.CreditModel
import com.example.credit.creditForm.dataManager.CreditRepository
import com.google.gson.JsonElement
import javax.inject.Inject

class CreditFormViewModel : ViewModel() {

    init {
        DIHandler.getCreditComponent().inject(this)
    }

    @Inject
    lateinit var repo: CreditRepository

    /*
    * Observe LiveData
    * */
    val submitData: LiveData<DataResult<JsonElement>> by lazy {
        repo.creditLiveData
    }

    fun submitCreditInfo(creditModel: CreditModel) {
//        if (submitData.value == null) {
//        }
        repo.submitCreditInfo(creditModel)
    }

    /*
    * Clear data when viewmodel is dismissed
    * */
    override fun onCleared() {
        super.onCleared()
        DIHandler.destroyCreditComponent()
    }


}