package pdp.va.com.personalgoal.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private var ourInstance: Retrofit? = null
        private val API_BASE_URL = "https://api.themoviedb.org"

        fun getInstance(): Retrofit? {
            if (ourInstance == null) {
                ourInstance = Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }
            return ourInstance
        }
    }
}