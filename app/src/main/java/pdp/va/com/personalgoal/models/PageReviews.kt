package pdp.va.com.personalgoal.models

import com.google.gson.annotations.SerializedName

data class PageReviews(
        @SerializedName("id")
        var id: Int,
        @SerializedName("page")
        var page: Int,
        @SerializedName("results")
        var reviews: List<Review>,
        @SerializedName("total_pages")
        var totalPages: Int,
        @SerializedName("total_results")
        var totalResults: Int
)