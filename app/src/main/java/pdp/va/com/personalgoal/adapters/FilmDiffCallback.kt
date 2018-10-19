package pdp.va.com.personalgoal.adapters

import androidx.recyclerview.widget.DiffUtil
import pdp.va.com.personalgoal.models.Film

class FilmDiffCallback : DiffUtil.ItemCallback<Film>() {

    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}