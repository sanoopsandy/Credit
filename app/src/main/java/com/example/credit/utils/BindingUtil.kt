package com.example.credit.utils

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.widget.TextView


class BindingUtil {

    companion object {

        @JvmStatic
        @BindingAdapter(value = arrayOf("android:text"))
        fun setText(view: TextView, value: Int) {
            view.text = Integer.toString(value)
        }

        @JvmStatic
        @InverseBindingAdapter(attribute = "android:text")
        fun getText(view: TextView): Int {
            return Integer.parseInt(view.text.toString());
        }
    }
}