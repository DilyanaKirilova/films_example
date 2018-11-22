package pdp.va.com.personalgoal.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, posterPath: String?) {
    if (!posterPath.isNullOrEmpty()) {
        Glide.with(view.context)
                .load(posterPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
    }
}