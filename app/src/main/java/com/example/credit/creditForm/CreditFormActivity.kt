package com.example.credit.creditForm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.ilhasoft.support.validation.Validator
import com.example.credit.R
import com.example.credit.core.networking.DataResult
import com.example.credit.creditForm.dataManager.CreditModel
import com.example.credit.creditForm.dataManager.CreditResponse
import com.example.credit.creditForm.viewModel.CreditFormViewModel
import com.example.credit.databinding.ActivityMainBinding
import com.example.credit.utils.hideKeyboard
import com.example.credit.utils.visibilityToggle
import com.google.gson.Gson
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_main.*


class CreditFormActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(CreditFormViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val validator = Validator(binding)
        observeResponse()
        btnSubmit.setOnClickListener {
            validateExpiryDate()
            if (validator.validate()) {
                progress.visibilityToggle(true)
                val creditNo = edtCard.text.toString()
                val cvv = edtCvv.text.toString()
                val expMonth = edtMonth.text.toString()
                val expYear = edtYear.text.toString()
                val name = edtName.text.toString()
                viewModel.submitCreditInfo(CreditModel(cardNo = creditNo,
                        cvv = cvv.toInt(),
                        expiryMonth = expMonth.toInt(),
                        expiryYear = expYear.toInt(),
                        name = name)
                )
            }
        }
    }

    private fun validateExpiryDate() {
        val yearText = edtYear.text.toString()
        if (yearText.isNotEmpty()) {
            if (yearText.toInt() < 17) {
                edtYear.error = getString(R.string.year_error)
            } else {
                edtYear.error = null
            }
        }
    }

    private fun observeResponse() {
        viewModel.postDataRepository.observe(this, Observer<DataResult<JsonElement>> { result ->
            when (result) {
                is DataResult.Progress -> {
                    hideKeyboard()
                    progress.visibilityToggle(false)
                }
                is DataResult.Success -> {
                    val response = result.data
                    val json = response.asJsonObject
                    val gson = Gson()
                    if (json.get("success").asBoolean) {
                        val model = gson.fromJson(json, CreditResponse::class.java)
                        txtResult.setTextColor(Color.GREEN)
                        txtResult.visibility = View.VISIBLE
                        txtResult.text = getString(R.string.result, model.data.requestId.toString())
                    } else {
                        txtResult.setTextColor(Color.RED)
                        txtResult.visibility = View.VISIBLE
                        txtResult.text = json.get("data").asString
                    }
                }
                is DataResult.Failure -> {
                    result.e.printStackTrace()
                    txtResult.setTextColor(Color.RED)
                    txtResult.text = getString(R.string.error)
                }
            }

        })
    }
}
