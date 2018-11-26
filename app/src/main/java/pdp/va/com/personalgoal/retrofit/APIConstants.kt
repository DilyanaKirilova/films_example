package pdp.va.com.personalgoal.retrofit

class APIConstants {

    companion object {
        const val API_BASE_URL = "https://api.themoviedb.org/3/movie/"

        const val FIRST_PARAM_API_KEY = "api_key=65b0f0c1dca6b0957d34d1fceaf3107a"
        const val SECOND_PARAM_LANGUAGE = "&language=en-US"
        const val THIRD_PARAM_PAGE = "&page=1"

        const val TOP_RATED = "top_rated?"
        const val POPULAR = "popular?"
        const val UPCOMING = "upcoming?"
        const val FILM_REVIEWS = "{movie_id}/reviews?"
    }
}