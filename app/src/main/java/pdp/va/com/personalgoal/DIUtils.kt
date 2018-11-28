package pdp.va.com.personalgoal

import android.content.Context
import pdp.va.com.personalgoal.database.AppDatabase
import pdp.va.com.personalgoal.repository.FilmRepository
import pdp.va.com.personalgoal.repository.FilmRepository.Companion.getInstance
import pdp.va.com.personalgoal.retrofit.IFilmAPI
import pdp.va.com.personalgoal.retrofit.RetrofitClient
import pdp.va.com.personalgoal.viewmodels.FilmDetailsViewModelFactory
import pdp.va.com.personalgoal.viewmodels.FilmsListViewModelFactory

object DIUtils {

    private fun getFilmRepository(context: Context): FilmRepository {
        return getInstance(AppDatabase.getInstance(context)!!.filmDao(), getFilmAPI(), context)
    }

    private fun getFilmAPI(): IFilmAPI {
        val retrofit = RetrofitClient.getInstance()
        return retrofit!!.create(IFilmAPI::class.java)
    }

    fun getFilmListViewModelFactory(context: Context): FilmsListViewModelFactory {
        return FilmsListViewModelFactory(getFilmRepository(context))
    }

    fun getFilmDetailsViewModelFactory(context: Context, id: Int): FilmDetailsViewModelFactory {
        return FilmDetailsViewModelFactory(getFilmRepository(context), id)
    }
}