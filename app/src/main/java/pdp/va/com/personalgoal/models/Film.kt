package pdp.va.com.personalgoal.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "films")
data class Film(
        @SerializedName("vote_count")
        var voteCount: Int,
        @PrimaryKey
        @SerializedName("id")
        var id: Int,
        @SerializedName("video")
        var video: Boolean,
        @SerializedName("vote_average")
        var voteAverage: Double,
        @SerializedName("title")
        var title: String,
        @SerializedName("popularity")
        var popularity: Double,
        @SerializedName("poster_path")
        var posterPath: String,
        @SerializedName("original_language")
        var originalLanguage: String,
        @SerializedName("original_title")
        var originalTitle: String,
        @SerializedName("backdrop_path")
        var backdropPath: String,
        @SerializedName("adult")
        var adult: Boolean,
        @SerializedName("overview")
        var overview: String,
        @SerializedName("release_date")
        var releaseDate: String
)