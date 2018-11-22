package pdp.va.com.personalgoal.database


import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import pdp.va.com.personalgoal.models.Film
import pdp.va.com.personalgoal.retrofit.IFilmAPI
import pdp.va.com.personalgoal.retrofit.RetrofitClient

class SeedDatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    private var movieAPI: IFilmAPI? = null
    private val compositeDisposable = CompositeDisposable()

    init {
        val retrofit = RetrofitClient.getInstance()
        movieAPI = retrofit?.create(IFilmAPI::class.java)
    }

    override fun doWork(): Result {
        var filmList: List<Film>? = null
        movieAPI!!.getUpcoming()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<pdp.va.com.personalgoal.models.Result>() {
                    override fun onNext(result: pdp.va.com.personalgoal.models.Result) {
                        filmList = result.movies
                        val database = AppDatabase.getInstance(applicationContext)
                        database.filmDao().insertAll(filmList!!)
                        Log.i("films", filmList.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "Error seeding database", e)
                    }

                    override fun onComplete() {
                    }
                })?.let { compositeDisposable.add(it) }
        if (filmList != null) {
            return Result.SUCCESS
        }
        return Result.FAILURE
    }
}