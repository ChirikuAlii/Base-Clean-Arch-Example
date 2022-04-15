package id.chirikualii.base_clean_arch_example.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.chirikualii.base_clean_arch_example.BuildConfig
import id.chirikualii.base_clean_arch_example.BuildConfig.BASE_URL
import id.chirikualii.base_clean_arch_example.arch.data.local.EncryptedPreferences
import id.chirikualii.base_clean_arch_example.arch.data.remote.service.ApiService
import id.chirikualii.base_clean_arch_example.utils.network.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Provides
    @Singleton
    fun provideNetworkInterceptor(encryptedPreferences: EncryptedPreferences) = NetworkInterceptor(encryptedPreferences)

    @Provides
    @Singleton
    fun providesOkHttpClient(networkInterceptor: NetworkInterceptor) : OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG){
                HttpLoggingInterceptor.Level.BODY
            }else{
                HttpLoggingInterceptor.Level.NONE
            }
        }

        val timeout = 30L
        return OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout,TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(networkInterceptor)
            .build()


    }
    @Provides
    @Singleton
    fun provideRetrofit(gson:Gson, okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)
}