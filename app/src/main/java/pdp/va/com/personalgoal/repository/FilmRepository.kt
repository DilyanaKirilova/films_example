package pdp.va.com.personalgoal.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import pdp.va.com.personalgoal.models.Film
import pdp.va.com.personalgoal.models.FilmDao

class FilmRepository private constructor(private val filmDao: FilmDao) {
    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(filmDao: FilmDao) =
                instance ?: synchronized(this) {
                    instance ?: FilmRepository(filmDao).also { instance = it }
                }
    }

    fun getFilms() : LiveData<List<Film>> = filmDao.getFilms()

    fun getFilm(id: Int) = filmDao.getFilm(id)

}