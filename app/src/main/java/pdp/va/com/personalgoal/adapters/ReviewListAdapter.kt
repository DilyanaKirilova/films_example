package pdp.va.com.personalgoal.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pdp.va.com.personalgoal.databinding.ListItemReviewBinding
import pdp.va.com.personalgoal.models.Review

class ReviewListAdapter : ListAdapter<Review, ReviewListAdapter.ViewHolder>(ReviewDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = getItem(position)
        holder.apply {
            bind(review)
            itemView.tag = review
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    class ViewHolder(
            private val binding: ListItemReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(review: Review) {
            binding.apply {
                this.review = review
                executePendingBindings()
            }
        }
    }
}