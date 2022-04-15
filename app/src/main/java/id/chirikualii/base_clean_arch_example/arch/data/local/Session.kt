package id.chirikualii.base_clean_arch_example.arch.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import id.chirikualii.base_clean_arch_example.arch.domain.model.User
import kotlinx.coroutines.flow.map

class Session constructor(
    @ApplicationContext
    private val context:Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SESSION_NAME)
    private val Context.userStore :DataStore<Preferences> by preferencesDataStore(USER_PROFILE)

    private val isFirstTime = booleanPreferencesKey(IS_FIRST_TIME)
    private val isLogin = booleanPreferencesKey(IS_LOGIN)
    private val name = stringPreferencesKey("NAME")
    private val email = stringPreferencesKey("EMAIL")
    private val address = stringPreferencesKey("ADDRESS")


    suspend fun setIsFirstTimeLaunch(isFirstTime:Boolean){
        context.dataStore.edit { data ->
            data[this.isFirstTime] = isFirstTime
        }
    }

    suspend fun getIsFirstTimeLaunch() =  context.dataStore.data.map {
        it[isFirstTime] ?: true
    }


    suspend fun setIsLogin(isLogin:Boolean){
        context.dataStore.edit { data ->
            data[this.isLogin] = isLogin
        }
    }

    suspend fun getIsLogin() = context.dataStore.data.map {
        it[isLogin] ?: false
    }

    suspend fun setUser(user:User) = context.userStore.edit { data ->
        data[name] = user.name
        data[email] = user.email
        data[address] = user.address
    }

    suspend fun getUser () = context.userStore.data.map { data->
        User(
            name = data[name] ?: "",
            email = data[email] ?: "",
            address = data[address] ?: "",
        )
    }




    companion object {
        private const val SESSION_NAME = "BaseAppSession"
        private const val IS_FIRST_TIME ="is_first_time"
        private const val USER_PROFILE = "user_profile"
        private const val IS_LOGIN = "is login"
    }
}