package com.example.credit.creditForm.di

import com.example.credit.core.di.BaseComponent
import com.example.credit.core.networking.PostService
import com.example.credit.creditForm.dataManager.CreditBluePrint
import com.example.credit.creditForm.dataManager.CreditRemoteHandler
import com.example.credit.creditForm.dataManager.CreditRepository
import com.example.credit.creditForm.viewModel.CreditFormViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@CreditScope
@Component(dependencies = [BaseComponent::class], modules = [CreditModule::class])
interface CreditComponent {
    fun inject(viewModel: CreditFormViewModel)

}

@CreditScope
@Module
class CreditModule {

    @CreditScope
    @Provides
    fun getListRepo(remote: CreditBluePrint.Remote,
                    compositeDisposable: CompositeDisposable): CreditRepository = CreditRepository(remote, compositeDisposable)

    @CreditScope
    @Provides
    fun getRemote(postService: PostService): CreditBluePrint.Remote = CreditRemoteHandler(postService)

    @CreditScope
    @Provides
    fun getCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @CreditScope
    @Provides
    fun getPostService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)
}