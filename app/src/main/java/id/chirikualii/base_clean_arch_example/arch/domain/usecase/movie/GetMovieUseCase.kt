package id.chirikualii.base_clean_arch_example.arch.domain.usecase.movie

import id.chirikualii.base_clean_arch_example.abstraction.domain.UseCase
import id.chirikualii.base_clean_arch_example.arch.domain.model.Movie
import id.chirikualii.base_clean_arch_example.arch.domain.repository.MovieRepository
import id.chirikualii.base_clean_arch_example.arch.presentation.mapper.toDomainView
import id.chirikualii.base_clean_arch_example.arch.presentation.model.MovieView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) :UseCase<UseCase.None,Flow<List<MovieView>>>(){

    override suspend fun run(params: None) : Flow<List<MovieView>> {

        return repository.getDiscoverMovie()
            .map {
                it.map { movie -> movie.toDomainView() }
            }
            .flowOn(Dispatchers.IO)
    }
}