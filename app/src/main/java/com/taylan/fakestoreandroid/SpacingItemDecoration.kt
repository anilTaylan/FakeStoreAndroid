import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
    private val horizontalSpacing: Int,
    private val verticalSpacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val spanCount = 2
        val column = position % spanCount

        outRect.left = horizontalSpacing - column * horizontalSpacing / spanCount
        outRect.right = (column + 1) * horizontalSpacing / spanCount

        // Add top spacing for all rows except the first
        if (position >= spanCount) {
            outRect.top = verticalSpacing
        }
    }
}