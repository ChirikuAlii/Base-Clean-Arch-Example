package id.chirikualii.base_clean_arch_example.arch.presentation.mapper

import id.chirikualii.base_clean_arch_example.arch.domain.model.Movie
import id.chirikualii.base_clean_arch_example.arch.presentation.model.MovieView

internal fun Movie.toDomainView() : MovieView {
    return MovieView(
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

internal fun MovieView.toDomain() : Movie{
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