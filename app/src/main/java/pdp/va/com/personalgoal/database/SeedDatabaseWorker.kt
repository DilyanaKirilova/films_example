package pdp.va.com.personalgoal.database


import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import pdp.va.com.personalgoal.models.Film

class SeedDatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    override fun doWork(): Result {
        val filmType = object : TypeToken<List<Film>>() {}.type
        var jsonReader: JsonReader? = null

        return try {
            val inputStream = applicationContext.assets.open("films.json")
            jsonReader = JsonReader(inputStream.reader())
            val filmList: List<Film> = Gson().fromJson(jsonReader, filmType)
            val database = AppDatabase.getInstance(applicationContext)
            database.filmDao().insertAll(filmList)
            Result.SUCCESS
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.FAILURE
        } finally {
            jsonReader?.close()
        }
    }
}