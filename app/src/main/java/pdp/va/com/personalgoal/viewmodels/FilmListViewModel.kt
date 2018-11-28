package pdp.va.com.personalgoal.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import pdp.va.com.personalgoal.repository.FilmRepository


class FilmListViewModel constructor(private val filmRepository: FilmRepository) : ViewModel() {

    fun getFilms(owner: LifecycleOwner) = filmRepository.getFilms(owner)
}
