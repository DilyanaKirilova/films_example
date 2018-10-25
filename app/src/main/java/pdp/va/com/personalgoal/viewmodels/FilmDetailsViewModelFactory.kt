package pdp.va.com.personalgoal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pdp.va.com.personalgoal.repository.FilmRepository

class FilmDetailsViewModelFactory (
private val repository: FilmRepository,
private val filmId : Int
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = FilmDetailsViewModel(repository, filmId) as T
}