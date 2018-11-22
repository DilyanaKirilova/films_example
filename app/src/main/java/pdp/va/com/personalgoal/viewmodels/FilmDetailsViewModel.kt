package pdp.va.com.personalgoal.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import pdp.va.com.personalgoal.models.Film
import pdp.va.com.personalgoal.repository.FilmRepository

class FilmDetailsViewModel internal constructor(
        private val filmRepository: FilmRepository,
        private val id: Int)
    : ViewModel() {

    private val film = MediatorLiveData<Film>()

    init {
        film.addSource(filmRepository.getFilm(id), film::setValue)
    }

    fun getFilm(): LiveData<Film> {
        return film
    }
}