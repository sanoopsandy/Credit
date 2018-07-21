package com.example.credit.core.di

import com.example.credit.core.BaseApplication
import com.example.credit.creditForm.di.CreditComponent
import com.example.credit.creditForm.di.DaggerCreditComponent
import javax.inject.Singleton

@Singleton
object DIHandler {

    private var creditComponent: CreditComponent? = null

    fun getCreditComponent(): CreditComponent {
        if (creditComponent == null)
            creditComponent = DaggerCreditComponent.builder().baseComponent(BaseApplication.baseComponent).build()
        return creditComponent as CreditComponent
    }

    fun destroyCreditComponent() {
        creditComponent = null
    }

}