package id.chirikualii.base_clean_arch_example.arch.data.repository

import id.chirikualii.base_clean_arch_example.arch.data.mapper.toDomain
import id.chirikualii.base_clean_arch_example.arch.data.remote.source.RemoteDataSource
import id.chirikualii.base_clean_arch_example.arch.domain.model.Movie
import id.chirikualii.base_clean_arch_example.arch.domain.repository.MovieRepository
import id.chirikualii.base_clean_arch_example.utils.vo.Either
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun getDiscoverMovie(): Flow<List<Movie>> {

        return flow {

            when (val response = remoteDataSource.discoverMovie()) {

                is Either.Success -> {
                    val listData = response.body.results.map {
                        it.toDomain()
                    }

                    emit(listData)
                    delay(3000)
                }
                is Either.Error -> {
                    delay(3000)
                    throw response.failure.throwable


                }
            }

        }
    }
}