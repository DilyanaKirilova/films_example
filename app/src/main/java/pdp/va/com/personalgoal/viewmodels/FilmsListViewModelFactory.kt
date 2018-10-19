package pdp.va.com.personalgoal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pdp.va.com.personalgoal.repository.FilmRepository

class FilmsListViewModelFactory(
private val repository: FilmRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = FilmListViewModel(repository) as T
}