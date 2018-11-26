package pdp.va.com.personalgoal.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pdp.va.com.personalgoal.models.Film
import pdp.va.com.personalgoal.models.FilmDao
import pdp.va.com.personalgoal.models.Review
import pdp.va.com.personalgoal.retrofit.IFilmAPI

class FilmRepository private constructor(private val filmDao: FilmDao, private val filmAPI: IFilmAPI) {
    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(filmDao: FilmDao, filmAPI: IFilmAPI) =
                instance ?: synchronized(this) {
                    instance ?: FilmRepository(filmDao, filmAPI).also { instance = it }
                }
    }

    fun getFilms(): LiveData<List<Film>> {
        // todo check if internet connection - api
        // todo else = dao
        return filmDao.getFilms()
    }

    fun getFilm(id: Int) = filmDao.getFilm(id)

    fun getReviews(id: Int): LiveData<List<Review>> {
        val liveReview: MutableLiveData<List<Review>> = MutableLiveData()
        filmAPI.getFilmReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ pageReviews -> liveReview.value = pageReviews.reviews })

        return liveReview
    }

}