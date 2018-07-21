package com.example.credit.core

import android.app.Application
import com.example.credit.core.di.AppModule
import com.example.credit.core.di.BaseComponent
import com.example.credit.core.di.DaggerBaseComponent
import com.example.credit.core.di.NetModule

class BaseApplication : Application() {

    companion object {
        lateinit var baseComponent: BaseComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        baseComponent = DaggerBaseComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(Constants.BASE_URL))
                .build()
    }

}