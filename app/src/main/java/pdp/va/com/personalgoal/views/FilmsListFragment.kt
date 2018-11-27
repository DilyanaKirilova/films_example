package pdp.va.com.personalgoal.views

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.films_list_fragment.*
import pdp.va.com.personalgoal.DIUtils
import pdp.va.com.personalgoal.adapters.FilmListAdapter
import pdp.va.com.personalgoal.adapters.FilmListAdapter.OnItemClickListener
import pdp.va.com.personalgoal.database.AppDatabase
import pdp.va.com.personalgoal.databinding.FilmsListFragmentBinding
import pdp.va.com.personalgoal.models.Result
import pdp.va.com.personalgoal.retrofit.IFilmAPI
import pdp.va.com.personalgoal.retrofit.RetrofitClient
import pdp.va.com.personalgoal.viewmodels.FilmListViewModel

class FilmsListFragment : Fragment() {

    companion object {
        fun newInstance() = FilmsListFragment()
    }

    private lateinit var viewModel: FilmListViewModel
    private var movieAPI: IFilmAPI? = null
    private val compositeDisposable = CompositeDisposable()

    init {
        val retrofit = RetrofitClient.getInstance()
        movieAPI = retrofit?.create(IFilmAPI::class.java)
    }

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
        fetchData(binding.root)
        binding.filmList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun fetchData(view: View) {
        movieAPI!!.getUpcoming()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(object : DisposableObserver<Result>() {
                    override fun onNext(result: pdp.va.com.personalgoal.models.Result) {
                        AppDatabase.getInstance(context)?.filmDao()?.insertAllFilms(result.movies)
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "Error seeding database", e)
                    }

                    override fun onComplete() {
                    }
                })?.let { compositeDisposable.add(it) }
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        film_list.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
    }

    private fun subscribeUi(adapter: FilmListAdapter) {
        viewModel.getFilms().observe(this, Observer { films ->
            if (films != null) adapter.submitList(films)
        })
    }

    private fun showProgressBar(showProgressBar: Boolean) {
        films_list_progress_bar.setVisibility(View.VISIBLE)
    }
}
