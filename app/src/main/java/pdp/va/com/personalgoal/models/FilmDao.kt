package pdp.va.com.personalgoal.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmDao {
    @Query("SELECT * FROM films ORDER BY title")
    fun getFilms(): LiveData<List<Film>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(film: Film)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(films: List<Film>)

    @Query("SELECT * FROM films WHERE id =:id")
    fun getFilm(id: Int): LiveData<Film>
}