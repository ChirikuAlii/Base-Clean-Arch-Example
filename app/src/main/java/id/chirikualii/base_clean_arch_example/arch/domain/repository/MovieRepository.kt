package id.chirikualii.base_clean_arch_example.arch.domain.repository

import id.chirikualii.base_clean_arch_example.arch.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

   suspend fun getDiscoverMovie() : Flow<List<Movie>>
}