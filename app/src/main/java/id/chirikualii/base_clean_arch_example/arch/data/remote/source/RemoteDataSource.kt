package id.chirikualii.base_clean_arch_example.arch.data.remote.source

import id.chirikualii.base_clean_arch_example.arch.data.remote.RemoteSafeRequest
import id.chirikualii.base_clean_arch_example.arch.data.remote.response.DiscoverMovieResponse
import id.chirikualii.base_clean_arch_example.arch.data.remote.service.ApiService
import id.chirikualii.base_clean_arch_example.utils.vo.Either
import id.chirikualii.base_clean_arch_example.utils.vo.Failure
import javax.inject.Inject


class RemoteDataSource  @Inject constructor(private val api :ApiService):RemoteSafeRequest() {

    suspend fun discoverMovie() :Either<Failure,DiscoverMovieResponse> =
        request {
            api.getDiscoverMovie()
        }
}