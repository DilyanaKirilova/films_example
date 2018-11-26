package pdp.va.com.personalgoal.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import pdp.va.com.personalgoal.models.Film
import pdp.va.com.personalgoal.models.Response
import pdp.va.com.personalgoal.models.Review
import pdp.va.com.personalgoal.repository.FilmRepository

class FilmDetailsViewModel internal constructor(
        private val filmRepository: FilmRepository,
        private val id: Int)
    : ViewModel() {

    private val film = MediatorLiveData<Film>()
    private val reviews = MediatorLiveData<Response<List<Review>>>()

    init {
        film.addSource(filmRepository.getFilm(id), film::setValue)
        reviews.addSource(filmRepository.getReviews(id), reviews::setValue)
    }

    fun getFilm(): LiveData<Film> {
        return film
    }

    fun getReviews(): LiveData<Response<List<Review>>> {
        return reviews
    }
}