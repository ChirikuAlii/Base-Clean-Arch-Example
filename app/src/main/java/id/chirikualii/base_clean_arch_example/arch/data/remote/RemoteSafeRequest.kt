package id.chirikualii.base_clean_arch_example.arch.data.remote

import id.chirikualii.base_clean_arch_example.utils.extensions.hasEmptyBody
import id.chirikualii.base_clean_arch_example.utils.extensions.isTotallySuccess
import id.chirikualii.base_clean_arch_example.utils.vo.Either
import id.chirikualii.base_clean_arch_example.utils.vo.Failure
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

open class RemoteSafeRequest {

    open suspend fun <T> request(apiCall: suspend () -> Response<T>) :Either<Failure,T>{
        return try {
            val response = apiCall.invoke()

            when {
                response.isTotallySuccess() -> Either.Success(response.body()!!)
                response.hasEmptyBody() -> Either.Error(Failure(Throwable("Empty Body")))
                response.code() in 300..599 -> Either.Error(Failure(Throwable("[${response.code()}] - [${response.message()}]"), message = response.message()))
                else -> Either.Error(Failure(Throwable("Unknown error from server")))
            }
        }catch (e :Throwable){
            when(e){
                is UnknownHostException -> Either.Error(Failure(Throwable("Tidak ada koneksi internet")))
                else -> Either.Error(Failure(e))

            }
        }
    }
}