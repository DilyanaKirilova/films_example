package pdp.va.com.personalgoal.models

import com.google.gson.annotations.SerializedName

data class PageFilms(
        @SerializedName("page")
        var page: Int,
        @SerializedName("total_results")
        var totalResults: Int,
        @SerializedName("total_pages")
        var totalPages: Int,
        @SerializedName("results")
        var movies: List<Film>
)
