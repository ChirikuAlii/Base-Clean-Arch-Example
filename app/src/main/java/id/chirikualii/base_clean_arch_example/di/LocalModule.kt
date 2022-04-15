package id.chirikualii.base_clean_arch_example.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.chirikualii.base_clean_arch_example.arch.data.local.EncryptedPreferences
import id.chirikualii.base_clean_arch_example.arch.data.local.Session
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideSession (@ApplicationContext context :Context)= Session(context)

    @Provides
    @Singleton
    fun encryptedPreferences (@ApplicationContext context: Context) = EncryptedPreferences(context)
}