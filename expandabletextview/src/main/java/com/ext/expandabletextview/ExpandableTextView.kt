package com.ext.expandabletextview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat

class ExpandableTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val contentTextView = AppCompatTextView(context)
    private val actionTextView = AppCompatTextView(context)

    private var collapsedLines = 2
    private var readMoreText = "Read More"
    private var readLessText = "Read Less"
    private var isExpanded = false

    private var listener: ExpandableTextListener? = null
    private var initialText: CharSequence? = null


    init {
        orientation = VERTICAL

        val a = context.obtainStyledAttributes(attrs, intArrayOf(android.R.attr.text))
        initialText = a.getText(0)
        a.recycle()

        context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView).apply {
            collapsedLines =
                getInt(R.styleable.ExpandableTextView_collapsedLines, 2)

            readMoreText =
                getString(R.styleable.ExpandableTextView_readMoreText) ?: readMoreText

            readLessText =
                getString(R.styleable.ExpandableTextView_readLessText) ?: readLessText

            val readMoreColor =
                getColor(
                    R.styleable.ExpandableTextView_readMoreTextColor,
                    ContextCompat.getColor(context, android.R.color.holo_blue_dark)
                )

            val readMoreTextSize =
                getDimension(
                    R.styleable.ExpandableTextView_readMoreTextSize,
                    actionTextView.textSize
                )

            val isBold =
                getBoolean(R.styleable.ExpandableTextView_readMoreBold, false)

            actionTextView.setTextColor(readMoreColor)
            actionTextView.textSize = readMoreTextSize / resources.displayMetrics.scaledDensity
            actionTextView.setTypeface(
                null,
                if (isBold) Typeface.BOLD else Typeface.NORMAL
            )

            recycle()
        }

        contentTextView.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        actionTextView.layoutParams =
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        actionTextView.text = readMoreText
        actionTextView.visibility = View.GONE

        actionTextView.setOnClickListener { toggle() }

        addView(contentTextView)
        addView(actionTextView)

        if (!initialText.isNullOrEmpty()) {
            setText(initialText!!)
        }
    }

    // ---------------- PUBLIC API ----------------

    fun setText(text: CharSequence) {
        contentTextView.text = text
        contentTextView.maxLines = collapsedLines

        post {
            actionTextView.visibility =
                if (contentTextView.lineCount > collapsedLines) View.VISIBLE
                else View.GONE
        }
    }

    fun expand() {
        if (isExpanded) return
        isExpanded = true

        contentTextView.maxLines = Int.MAX_VALUE
        actionTextView.text = readLessText

        listener?.onExpanded()
        listener?.onToggle(true)
    }

    fun collapse() {
        if (!isExpanded) return
        isExpanded = false

        contentTextView.maxLines = collapsedLines
        actionTextView.text = readMoreText

        listener?.onCollapsed()
        listener?.onToggle(false)
    }

    fun toggle() {
        if (isExpanded) collapse() else expand()
    }

    fun isExpanded(): Boolean = isExpanded

    fun setExpandableTextListener(listener: ExpandableTextListener) {
        this.listener = listener
    }

    // ---------------- LISTENER ----------------

    interface ExpandableTextListener {
        fun onExpanded()
        fun onCollapsed()
        fun onToggle(isExpanded: Boolean)
    }
}
