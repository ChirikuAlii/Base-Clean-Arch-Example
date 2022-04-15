package id.chirikualii.base_clean_arch_example.arch.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EncryptedPreferences
@Inject constructor(
@ApplicationContext context: Context
) {

    private val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sp: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            SECURE_PREF_NAME,
            masterKey,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private val spe: SharedPreferences.Editor by lazy {
        sp.edit()
    }

    fun clear() {
        spe.clear().apply()
    }

    var encryptedToken: String
    get() = sp.getString(SECURE_SP_TOKEN, "") ?: ""
    set(value) = spe.putString(SECURE_SP_TOKEN, value).apply()

    var encryptedPassword:String
    get() = sp.getString(SECURE_SP_PASSWORD, "") ?: ""
    set(value) = spe.putString(SECURE_SP_PASSWORD, value).apply()

    companion object {
        private const val SECURE_PREF_NAME = "base.unreachable_preferences"
        private const val SECURE_SP_TOKEN = "pref_token"
        private const val SECURE_SP_PASSWORD = "pref_password"
    }
}
