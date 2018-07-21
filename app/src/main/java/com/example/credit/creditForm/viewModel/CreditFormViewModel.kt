package com.example.credit.creditForm.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.credit.core.di.DIHandler
import com.example.credit.core.networking.DataResult
import com.example.credit.utils.toLiveData
import com.example.credit.creditForm.dataManager.CreditModel
import com.example.credit.creditForm.dataManager.CreditResponse
import com.example.credit.creditForm.dataManager.CreditRepository
import com.google.gson.JsonElement
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CreditFormViewModel : ViewModel() {

    init {
        DIHandler.getCreditComponent().inject(this)
    }

    @Inject
    lateinit var repo: CreditRepository

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    /*
    * Converting publish object to live data to observe updates in the result
    * */
    val postDataRepository: LiveData<DataResult<JsonElement>> by lazy {
        repo.postSubmitCreditModelDataResult.toLiveData(compositeDisposable)
    }

    fun submitCreditInfo(creditModel: CreditModel) {
        /*if (postDataRepository.value == null){

        }*/
        repo.submitCreditInfo(creditModel)
    }

    /*
    * Clear data when viewmodel is dismissed
    * */
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        DIHandler.destroyCreditComponent()
    }


}