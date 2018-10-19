package pdp.va.com.personalgoal.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import pdp.va.com.personalgoal.DIUtils

import pdp.va.com.personalgoal.adapters.FilmListAdapter
import pdp.va.com.personalgoal.databinding.MainFragmentBinding
import pdp.va.com.personalgoal.viewmodels.FilmListViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: FilmListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

        val factory = DIUtils.getFilmListViewModelFactory(context)
        viewModel = ViewModelProviders.of(this, factory).get(FilmListViewModel::class.java)

        val adapter = FilmListAdapter()
        binding.filmList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: FilmListAdapter) {
        viewModel.getFilms().observe(viewLifecycleOwner, Observer { films ->
            if (films != null) adapter.submitList(films)
        })
    }

}
