package id.chirikualii.base_clean_arch_example.arch.presentation.view

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.chirikualii.base_clean_arch_example.abstraction.domain.UseCase
import id.chirikualii.base_clean_arch_example.abstraction.presentation.BaseViewModel
import id.chirikualii.base_clean_arch_example.arch.domain.usecase.movie.GetMovieUseCase
import id.chirikualii.base_clean_arch_example.arch.presentation.model.MovieView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
):BaseViewModel<MainViewModel.MainState>(){

    sealed class MainState(){

        data class SuccessLoadMovie(val listData :List<MovieView>) :MainState()
        object LoadingLoadMovie :MainState()
        data class FailedLoadMovie(val message:String) :MainState()

    }

    fun doLoadMovies(){
        _uiState.value = MainState.LoadingLoadMovie
        viewModelScope.launch(Dispatchers.IO) {

            getMovieUseCase.run(UseCase.None)
                .catch {
                    withContext(Dispatchers.Main){
                        _uiState.value = MainState.FailedLoadMovie(it.message.toString())
                    }
                }
                .collect { listData ->
                    withContext(Dispatchers.Main){
                        _uiState.value = MainState.SuccessLoadMovie(listData)
                    }
                }

        }
    }

}