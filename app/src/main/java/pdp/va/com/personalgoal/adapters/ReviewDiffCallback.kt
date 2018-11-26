package pdp.va.com.personalgoal.adapters

import androidx.recyclerview.widget.DiffUtil
import pdp.va.com.personalgoal.models.Review

class ReviewDiffCallback : DiffUtil.ItemCallback<Review>() {

    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}