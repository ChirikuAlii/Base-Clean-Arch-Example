package id.chirikualii.base_clean_arch_example.arch.data.mapper

import id.chirikualii.base_clean_arch_example.arch.data.remote.response.DiscoverMovieResponse
import id.chirikualii.base_clean_arch_example.arch.domain.model.Movie


internal fun DiscoverMovieResponse.Result.toDomain() : Movie{

    return Movie(
        adult= this.adult,
        backdropPath = this.backdropPath,
        id = this.id ,
        title = this.title,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}