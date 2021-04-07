package com.agostonr.multistyleapp.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.agostonr.multistyleapp.utils.MultiStyleCreator

class MultiStyleTextView(context: Context, attrs: AttributeSet) :
    AppCompatTextView(MultiStyleCreator.createThemedContext(context, attrs), attrs)
