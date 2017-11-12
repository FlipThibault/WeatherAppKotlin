package base

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.flip.kotlinbook.R

/**
 * Created by pthibau1 on 2017-11-11.
 */
class SimpleLoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.simple_loading_view, this)
    }

}