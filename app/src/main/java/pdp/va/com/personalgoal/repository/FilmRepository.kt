package pdp.va.com.personalgoal.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pdp.va.com.personalgoal.models.*
import pdp.va.com.personalgoal.retrofit.IFilmAPI

/** Repository for films and related data
 * This class hold the cache and API instances and decides what to use to provide data
 * */
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

    /**
     * This method provides а LiveData for list of films
     * We check if we have internet and decide if we are going to get the cached version
     * or refresh with API call.
     *
     */
    fun getFilms(): LiveData<List<Film>> {
        // todo check if internet connection - api
        // todo else = dao
        return filmDao.getFilms()
    }

    /**
     * This method provides а LiveData for a single Film
     * We always get the cached version of the film
     */
    fun getFilm(id: Int) = filmDao.getFilm(id)

    /**
     * This method provides а LiveData for reviews
     * We always get the latest version of review with each call
     */
    @SuppressLint("CheckResult")
    fun getReviews(id: Int): LiveData<Response<List<Review>>> {
        val liveReview: MutableLiveData<Response<List<Review>>> = MutableLiveData()
        filmAPI.getFilmReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { liveReview.value = Response.loading() }
                .doOnError { error -> liveReview.value = Response.error(error) }
                .subscribe({ pageReviews -> liveReview.value = Response.success(pageReviews.reviews) },
                        { error -> liveReview.value = Response.error(error) })

        return liveReview
    }

}