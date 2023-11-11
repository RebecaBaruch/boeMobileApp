package com.example.boeteste

import com.example.boeteste.classes.UsuarioUpdateShowDataGet
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
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

    @GET("menu/{idUser}")
    @Headers("Content-Type:application/json")
    fun exibirDadosUsuarioMenu(@Path("idUser") id: String): Call<ResponseBody>

    @GET("updateUser/{idUser}")
    @Headers("Content-Type:application/json")
    fun exibirDadosUsuarioAtualizar(@Path("idUser") id: String): Call<UsuarioUpdateShowDataGet>

    @PUT("updateUser/{idUser}")
    @Headers("Content-Type:application/json")
    fun atualizarDadosUsuario(@Path("idUser") id: String, @Body requests: RequestBody): Call<ResponseBody>

    @DELETE("deleteUser/{idUser}")
    @Headers("Content-Type:application/json")
    fun deletarUsuario(@Path("idUser") id: String): Call<ResponseBody>
}