package ibrahim.db.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setVisibility")
fun setVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}


@BindingAdapter("setRefreshing")
fun setRefreshing(swipeRefresher: SwipeRefreshLayout, isRefreshing: Boolean) {
    swipeRefresher.isRefreshing = isRefreshing
}

@BindingAdapter("setDate")
fun setDate(textView: MaterialTextView, date: String?) {
    if (!date.isNullOrEmpty()) {
        var input = date
        input = input.replace(' ', 'T')
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date = inputFormat.parse(input)
        textView.text = SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault()).format(date!!)
    }
}

@BindingAdapter("setTitle")
fun setTitle(textView: MaterialTextView, title: String?) {
    if (!title.isNullOrEmpty()) {
        // capitalize title
        textView.text = title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}