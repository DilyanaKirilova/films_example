package pdp.va.com.personalgoal.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pdp.va.com.personalgoal.models.Film
import pdp.va.com.personalgoal.models.Response
import pdp.va.com.personalgoal.models.Review
import pdp.va.com.personalgoal.repository.FilmRepository

class FilmDetailsViewModel internal constructor(
        private val filmRepository: FilmRepository,
        private val id: Int)
    : ViewModel() {

    fun getFilm(): LiveData<Film> {
        return filmRepository.getFilm(id)
    }

    fun getReviews(): LiveData<Response<List<Review>>> {
        return filmRepository.getReviews(id)
    }
}