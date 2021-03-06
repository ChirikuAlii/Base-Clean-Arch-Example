package id.chirikualii.base_clean_arch_example.utils.network

import id.chirikualii.base_clean_arch_example.arch.data.local.EncryptedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor  constructor(
    private val encryptedPreferences: EncryptedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest =
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${encryptedPreferences.encryptedToken}")
                .build()

        return chain.proceed(newRequest)
    }
}