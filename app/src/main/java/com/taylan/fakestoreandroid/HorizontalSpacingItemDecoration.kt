package com.taylan.fakestoreandroid
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpacingItemDecoration(
    private val horizontalSpacing: Int,
    private val verticalSpacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = verticalSpacing
        outRect.bottom = verticalSpacing
        outRect.left = horizontalSpacing / 2
        outRect.right = horizontalSpacing / 2
    }
}