package com.sxit.campuslink.network

import retrofit2.http.GET

import com.sxit.campuslink.model.Board

interface ApiService {
    @GET("boards/")
    suspend fun getBoards(): List<Board>
}