package pdp.va.com.personalgoal.retrofit

import io.reactivex.Observable
import pdp.va.com.personalgoal.models.Result
import pdp.va.com.personalgoal.retrofit.APIConstants.Companion.FIRST_PARAM_API_KEY
import pdp.va.com.personalgoal.retrofit.APIConstants.Companion.POPULAR
import pdp.va.com.personalgoal.retrofit.APIConstants.Companion.SECOND_PARAM_LANGUAGE
import pdp.va.com.personalgoal.retrofit.APIConstants.Companion.THIRD_PARAM_PAGE
import pdp.va.com.personalgoal.retrofit.APIConstants.Companion.TOP_RATED
import pdp.va.com.personalgoal.retrofit.APIConstants.Companion.UPCOMING
import retrofit2.http.GET


interface IFilmAPI {

    @GET(TOP_RATED + FIRST_PARAM_API_KEY + SECOND_PARAM_LANGUAGE + THIRD_PARAM_PAGE)
    abstract fun getTopRated(): Observable<Result>

    @GET(POPULAR + FIRST_PARAM_API_KEY + SECOND_PARAM_LANGUAGE + THIRD_PARAM_PAGE)
    abstract fun getPopular(): Observable<Result>

    @GET(UPCOMING + FIRST_PARAM_API_KEY + SECOND_PARAM_LANGUAGE + THIRD_PARAM_PAGE)
    abstract fun getUpcoming(): Observable<Result>
}