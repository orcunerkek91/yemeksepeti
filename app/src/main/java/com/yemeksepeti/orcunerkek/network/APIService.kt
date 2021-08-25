package com.yemeksepeti.orcunerkek.network

import com.yemeksepeti.orcunerkek.model.MovieDetailModel
import com.yemeksepeti.orcunerkek.model.SearchMovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("search/movie")
    fun getSearchMovies(@Query("api_key") api_key: String?, @Query("query") query: String?, @Query("page") page: Int?): Call<SearchMovieModel>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path(value = "movie_id", encoded = false) movie_id: Int?, @Query("api_key") api_key: String?): Call<MovieDetailModel>
}