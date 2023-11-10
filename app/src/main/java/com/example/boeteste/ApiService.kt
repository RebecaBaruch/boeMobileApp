package com.example.boeteste

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("signupUser")
    @Headers("Content-Type:application/json")
    fun cadastrarUsuario(@Body requests: RequestBody): Call<ResponseBody>

    @POST("loginUser")
    @Headers("Content-Type:application/json")
    fun logarUsuario(@Body requests: RequestBody): Call<ResponseBody>

    @PUT("updateUser/{idUser}")
    @Headers("Content-Type:application/json")
    fun atualizarDadosUsuario(@Path("idUser") id: String, @Body requests: RequestBody): Call<ResponseBody>
}