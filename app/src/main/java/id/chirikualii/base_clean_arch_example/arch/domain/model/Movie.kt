package id.chirikualii.base_clean_arch_example.arch.domain.model

data class Movie(
    val adult: Boolean? = false,
    val backdropPath: String? = "",
    val id: Int? = 0,
    val title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val posterPath: String? = "",
    val releaseDate: String? = "",
    val video: Boolean? = false,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0
)