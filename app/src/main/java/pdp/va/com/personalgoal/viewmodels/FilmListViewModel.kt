package pdp.va.com.personalgoal.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import pdp.va.com.personalgoal.models.Film
import pdp.va.com.personalgoal.repository.FilmRepository


class FilmListViewModel constructor(private val filmRepository: FilmRepository) : ViewModel() {

    private val filmList = MediatorLiveData<List<Film>>()

    init {
        filmList.addSource(filmRepository.getFilms(), filmList::setValue)
    }

    fun getFilms() = filmList
}
