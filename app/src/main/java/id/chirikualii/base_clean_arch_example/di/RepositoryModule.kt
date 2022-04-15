package id.chirikualii.base_clean_arch_example.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.chirikualii.base_clean_arch_example.arch.data.repository.MovieRepositoryImpl
import id.chirikualii.base_clean_arch_example.arch.domain.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(repositoryImpl: MovieRepositoryImpl) : MovieRepository
}