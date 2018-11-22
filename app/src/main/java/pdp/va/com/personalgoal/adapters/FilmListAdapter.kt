package pdp.va.com.personalgoal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pdp.va.com.personalgoal.databinding.ListItemFilmBinding
import pdp.va.com.personalgoal.models.Film

class FilmListAdapter constructor(private val itemClickListener: OnItemClickListener) : ListAdapter<Film, FilmListAdapter.ViewHolder>(FilmDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = getItem(position)
        holder.apply {
            bind(createOnClickListener(film.id), film)
            itemView.tag = film
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    private fun createOnClickListener(id: Int): View.OnClickListener {
        return View.OnClickListener {
            itemClickListener.onItemClick(id)
        }
    }

    class ViewHolder(
            private val binding: ListItemFilmBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, film: Film) {
            binding.apply {
                clickListener = listener
                this.film = film
                executePendingBindings()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }
}
