package pdp.va.com.personalgoal.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmDao {
    @Query("SELECT * FROM films")
    fun getFilms(): LiveData<List<Film>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(film: Film)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFilms(films: List<Film>)

    @Query("SELECT * FROM films WHERE id =:id")
    fun getFilm(id: Int): LiveData<Film>
}