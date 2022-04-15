package id.chirikualii.base_clean_arch_example.arch.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import id.chirikualii.base_clean_arch_example.R
import id.chirikualii.base_clean_arch_example.abstraction.presentation.BaseActivityBinding
import id.chirikualii.base_clean_arch_example.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivityBinding<ActivityMainBinding>() ,Observer<MainViewModel.MainState> {

    private val mViewModel :MainViewModel by viewModels()

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = { ActivityMainBinding.inflate(layoutInflater)}


    override fun setupView() {
        mViewModel.uiState.observe(this,this)

        mViewModel.doLoadMovies()

    }
    override fun onChanged(state: MainViewModel.MainState?) {

        when (state){

            is MainViewModel.MainState.SuccessLoadMovie -> {
                Log.d(MainActivity::class.java.simpleName, "success load movie ${Gson().toJsonTree(state.listData)}")
            }
            is MainViewModel.MainState.LoadingLoadMovie -> {
                Log.d(MainActivity::class.java.simpleName,  "loading load movie")
            }
            is MainViewModel.MainState.FailedLoadMovie -> {
                Log.e(MainActivity::class.java.simpleName, "failed load movie :${state.message} " )
            }
        }
    }
}