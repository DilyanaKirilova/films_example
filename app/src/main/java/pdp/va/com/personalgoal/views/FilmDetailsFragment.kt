package pdp.va.com.personalgoal.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pdp.va.com.personalgoal.DIUtils
import pdp.va.com.personalgoal.databinding.FilmDetailsFragmentBinding
import pdp.va.com.personalgoal.viewmodels.FilmDetailsViewModel

class FilmDetailsFragment  : Fragment() {

    companion object {
        fun newInstance(filmId : Int) : FilmDetailsFragment{
            val fragment = FilmDetailsFragment()
            val args = Bundle()
            args.putInt("FILM_ID", filmId)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: FilmDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var filmId = 1
        if (arguments!!.containsKey("filmId")) {
            filmId = arguments!!.getInt("filmId")
        }

        val binding = FilmDetailsFragmentBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root
        val factory = DIUtils.getFilmDetailsViewModelFactory(context, filmId)
        viewModel = ViewModelProviders.of(this, factory).get(FilmDetailsViewModel::class.java)

        viewModel.getFilm().observe(this, Observer { film ->
            binding.film = film
        })

        return binding.root

    }
}