package com.agostonr.multistyleapp.utils

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatTextView
import com.agostonr.multistyleapp.R
import com.google.android.material.theme.MaterialComponentsViewInflater

class MultiStyleViewInflater : MaterialComponentsViewInflater() {
    override fun createTextView(context: Context, attrs: AttributeSet?): AppCompatTextView {
        return super.createTextView(createContextIfMultiStyle(context, attrs), attrs)
    }

    private fun createContextIfMultiStyle(context: Context, attrs: AttributeSet?): Context {
        val styleAttributes = context.obtainStyledAttributes(attrs, R.styleable.TextView)
        val styles = extractStyles(styleAttributes)
        val createdContext = if (styles.any { it != 0 }) {
            val theme = context.resources.newTheme()
            theme.applyValidStyles(styles)
            ContextThemeWrapper(context, theme)
        } else {
            context
        }
        styleAttributes.recycle()
        return createdContext

    }

    private fun extractStyles(styleAttributes: TypedArray) = listOf(
        styleAttributes.getResourceId(R.styleable.TextView_style1, 0),
        styleAttributes.getResourceId(R.styleable.TextView_style2, 0),
        styleAttributes.getResourceId(R.styleable.TextView_style3, 0),
        styleAttributes.getResourceId(R.styleable.TextView_style4, 0),
        styleAttributes.getResourceId(R.styleable.TextView_style5, 0)
    )

    private fun Resources.Theme.applyValidStyles(styles: List<Int>) {
        styles.filterNot { it == 0 }.forEach { this.applyStyle(it, true) }
    }
}
