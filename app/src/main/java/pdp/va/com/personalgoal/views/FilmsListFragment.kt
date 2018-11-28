package pdp.va.com.personalgoal.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.film_details_fragment.*
import kotlinx.android.synthetic.main.films_list_fragment.*
import pdp.va.com.personalgoal.DIUtils
import pdp.va.com.personalgoal.adapters.FilmListAdapter
import pdp.va.com.personalgoal.adapters.FilmListAdapter.OnItemClickListener
import pdp.va.com.personalgoal.databinding.FilmsListFragmentBinding
import pdp.va.com.personalgoal.models.Status
import pdp.va.com.personalgoal.viewmodels.FilmListViewModel

class FilmsListFragment : Fragment() {

    companion object {
        fun newInstance() = FilmsListFragment()
    }

    private lateinit var viewModel: FilmListViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = FilmsListFragmentBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

        val factory = DIUtils.getFilmListViewModelFactory(context)
        viewModel = ViewModelProviders.of(this, factory).get(FilmListViewModel::class.java)

        val itemCLick = object : OnItemClickListener {
            override fun onItemClick(id: Int) {
                (activity as MainActivity).addFragment(FilmDetailsFragment.newInstance(id))
            }
        }
        val adapter = FilmListAdapter(itemCLick)
        binding.filmList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        film_list.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
    }

    private fun subscribeUi(adapter: FilmListAdapter) {
        viewModel.getFilms(this).observe(this, Observer { response ->
            when (response.status) {
                Status.LOADING -> {
                    showProgressBar()
                }
                Status.SUCCESS -> {
                    hideProgressBar()
                    if (response.data!!.isEmpty()) {
                        showSnackBarMessage("There are no films to display")
                    } else {
                        adapter.submitList(response.data)
                    }
                }
                Status.ERROR -> {
                    hideProgressBar()
                    showSnackBarMessage("Something went wrong. Please try again later.")
                }
            }
        })
    }

    private fun showProgressBar() {
        films_list_progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        films_list_progress_bar.visibility = View.GONE
    }

    private fun showSnackBarMessage(message: String) {
        Snackbar.make(getActivity()!!.list_layout, message, Snackbar.LENGTH_LONG).show()
    }

}
