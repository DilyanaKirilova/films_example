package pdp.va.com.personalgoal.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "films")
data class Film(
    @PrimaryKey(autoGenerate = true) val filmId: Int = 1,
    val name : String,
    val genre : String,
    val country : String,
    val director : String,
    val year : Int,
    val imageUrl : String
)