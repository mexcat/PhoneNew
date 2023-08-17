package cl.gencina.phonenew.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneApi {
    @GET("products/")
    suspend fun getAllData(): Response<List<Phone>>

    @GET("details/{id}/")
    suspend fun getPhoneDetail(@Path("id") id: Int): Response<Phone>
}