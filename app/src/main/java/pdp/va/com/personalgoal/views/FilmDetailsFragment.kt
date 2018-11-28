package pdp.va.com.personalgoal.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.film_details_fragment.*
import kotlinx.android.synthetic.main.reviews_list.view.*
import pdp.va.com.personalgoal.DIUtils
import pdp.va.com.personalgoal.R
import pdp.va.com.personalgoal.adapters.ReviewListAdapter
import pdp.va.com.personalgoal.databinding.FilmDetailsFragmentBinding
import pdp.va.com.personalgoal.models.Status
import pdp.va.com.personalgoal.network.NetworkUtil.Companion.isNetworkAvailable
import pdp.va.com.personalgoal.viewmodels.FilmDetailsViewModel


class FilmDetailsFragment : Fragment() {

    companion object {
        private val KEY_ID: String = "KEY_ID"
        fun newInstance(id: Int): FilmDetailsFragment {
            val fragment = FilmDetailsFragment()
            val args = Bundle()
            args.putInt(KEY_ID, id)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: FilmDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var id = 1
        if (arguments!!.containsKey(KEY_ID)) {
            id = arguments!!.getInt(KEY_ID)
        }

        val binding = FilmDetailsFragmentBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root
        val factory = DIUtils.getFilmDetailsViewModelFactory(context, id)
        viewModel = ViewModelProviders.of(this, factory).get(FilmDetailsViewModel::class.java)

        viewModel.getFilm().observe(this, Observer { film ->
            binding.film = film
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        see_film_reviews.setOnClickListener { v ->
            if (!isNetworkAvailable(context!!)) {
                showSnackBarMessage("Please check your internet connection.")
            } else {
                showProgressBar()
                subscribeUi()
            }
        }
    }

    private fun subscribeUi() {
        val adapter = ReviewListAdapter()
        viewModel.getReviews().observe(this, Observer { response ->
            when (response.status) {
                Status.LOADING -> {
                    showProgressBar()
                }
                Status.SUCCESS -> {
                    hideProgressBar()
                    if (response.data!!.isEmpty()) {
                        showSnackBarMessage("There are no reviews for this film.")
                    } else {
                        adapter.submitList(response.data)
                        showAlertDialog(adapter)
                    }

                }
                Status.ERROR -> {
                    hideProgressBar()
                    showSnackBarMessage("Something went wrong. Please try again later.")
                }
            }
        })
    }

    private fun showAlertDialog(adapter: ReviewListAdapter) {
        val builder = AlertDialog.Builder(context!!).create()
        val layout = this.layoutInflater.inflate(R.layout.reviews_list, null)
        builder.setView(layout)
        layout.reviews_recycler_view.layoutManager = LinearLayoutManager(context)
        layout.reviews_recycler_view.adapter = adapter
        builder.setTitle(R.string.reviews)
        builder.show()
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
        see_film_reviews.visibility = View.VISIBLE
    }

    private fun showSnackBarMessage(message: String) {
        Snackbar.make(getActivity()!!.details_layout, message, Snackbar.LENGTH_LONG).show()
    }
}