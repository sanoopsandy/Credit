package com.example.credit.utils

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import com.example.credit.core.networking.DataResult
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


/*
* Extension for Observable objects
* */
fun <T> Observable<T>.doOnBackOutOnMain(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

/*
* Extension for Flowable objects
* */
fun <T> Flowable<T>.doOnBackOutOnMain(): Flowable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

/*
* Extension for Completable objects
* */
fun Completable.doOnBack(): Completable {
    return this.subscribeOn(Schedulers.io())
}

/*
* Convert a Subject to liveData to observe in activity
* */
fun <T> PublishSubject<T>.toLiveData(compositeDisposable: CompositeDisposable): LiveData<T> {
    val data = MutableLiveData<T>()
    compositeDisposable.add(this.subscribe({ t: T -> data.value = t }))
    return data
}


/**
 * Function to handle loading logic and push the value forward
 * */
fun <T> PublishSubject<DataResult<T>>.failure(e: Throwable) {
    with(this) {
        loading(false)
        onNext(DataResult.failure(e))
    }
}

/**
 * Function to handle loading logic and push the value forward
 * */
fun <T> PublishSubject<DataResult<T>>.success(t: T) {
    with(this) {
        loading(false)
        onNext(DataResult.success(t))
    }
}

/**
 * Function to push the loading status to the observing outcome
 * */
fun <T> PublishSubject<DataResult<T>>.loading(isLoading: Boolean) {
    this.onNext(DataResult.loading(isLoading))
}

/**
 * Extension function to add a Disposable to a CompositeDisposable
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

/*
* Progress visibikity toggle
* */
fun ProgressBar.visibilityToggle(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}


/**
 * Function to handle loading logic and push the value forward
 * */
fun <T> MutableLiveData<DataResult<T>>.failure(e: Throwable) {
    with(this) {
        loading(false)
        value = (DataResult.failure(e))
    }
}

/**
 * Function to handle loading logic and push the value forward
 * */
fun <T> MutableLiveData<DataResult<T>>.success(t: T) {
    with(this) {
        loading(false)
        value = (DataResult.success(t))
    }
}

/**
 * Function to push the loading status to the observing outcome
 * */
fun <T> MutableLiveData<DataResult<T>>.loading(isLoading: Boolean) {
    value = DataResult.loading(isLoading)
}
