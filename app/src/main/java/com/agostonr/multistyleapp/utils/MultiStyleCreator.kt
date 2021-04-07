package com.agostonr.multistyleapp.utils

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import com.agostonr.multistyleapp.R

object MultiStyleCreator {

    fun createThemedContext(context: Context, attrs: AttributeSet): ContextThemeWrapper {
        val styleAttributes = context.obtainStyledAttributes(attrs, R.styleable.MultiStyleTextView)
        val theme = context.resources.newTheme()
        val styles = createStyles(styleAttributes)
        theme.applyValidStyles(styles)
        styleAttributes.recycle()
        return ContextThemeWrapper(context, theme)
    }

    private fun createStyles(styleAttributes: TypedArray): List<Int> {
        return listOf(
            styleAttributes.getResourceId(R.styleable.MultiStyleTextView_style1, 0),
            styleAttributes.getResourceId(R.styleable.MultiStyleTextView_style2, 0),
            styleAttributes.getResourceId(R.styleable.MultiStyleTextView_style3, 0),
            styleAttributes.getResourceId(R.styleable.MultiStyleTextView_style4, 0),
            styleAttributes.getResourceId(R.styleable.MultiStyleTextView_style5, 0)
        )
    }

    private fun Resources.Theme.applyValidStyles(styles: List<Int>) {
        styles.filterNot { it == 0 }.forEach { this.applyStyle(it, true) }
    }
}
