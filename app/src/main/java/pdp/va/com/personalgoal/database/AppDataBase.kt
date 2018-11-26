package pdp.va.com.personalgoal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pdp.va.com.personalgoal.models.Film
import pdp.va.com.personalgoal.models.FilmDao
import pdp.va.com.personalgoal.models.Review


/**
 * The Room database for this app
 */
@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context?): AppDatabase? {
            if (context == null) {
                return null
            }
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context?): AppDatabase {
            return Room.databaseBuilder(context!!, AppDatabase::class.java, "filmsDB")
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}
