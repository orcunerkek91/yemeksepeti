package com.yemeksepeti.orcunerkek.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import com.yemeksepeti.orcunerkek.model.MovieDetailModel
import com.yemeksepeti.orcunerkek.model.SearchMovieModel

import com.yemeksepeti.orcunerkek.network.APIService
import com.yemeksepeti.orcunerkek.network.RetroInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMovieViewModel : ViewModel() {

    private val _searchModel = MutableLiveData<SearchMovieModel>()
    val searchModel: LiveData<SearchMovieModel>
        get() = _searchModel

    private val _movieDetailModel = MutableLiveData<MovieDetailModel>()
    val movieDetailModel: LiveData<MovieDetailModel>
        get() = _movieDetailModel

    /*fun searchMovieViewModel() {
        _searchModel
    }*/

    fun getSearchModelObserver(): MutableLiveData<SearchMovieModel>? {
        return _searchModel
    }

    fun getMovies(query: String, page: Int) {
        val apiService: APIService = RetroInstance.getRetroClient()!!.create(APIService::class.java)


            val call: Call<SearchMovieModel> = apiService.getSearchMovies("35ef0461fc4557cf1d256d3335ed7545", query, page)
            call.enqueue(object : Callback<SearchMovieModel> {
                override fun onResponse(call: Call<SearchMovieModel>, response: Response<SearchMovieModel>) {
                    _searchModel.postValue(response.body())
                }

                override fun onFailure(call: Call<SearchMovieModel>, t: Throwable) {
                    _searchModel.postValue(null)
                }
            })

    }

    fun getMovieDetailObserver(): MutableLiveData<MovieDetailModel>? {
        return _movieDetailModel
    }

    fun getMovieDetail(movie_id : Int) {
        val apiService: APIService = RetroInstance.getRetroClient()!!.create(APIService::class.java)


        val call: Call<MovieDetailModel> = apiService.getMovieDetail(movie_id,"35ef0461fc4557cf1d256d3335ed7545")
        call.enqueue(object : Callback<MovieDetailModel> {
            override fun onResponse(call: Call<MovieDetailModel>, response: Response<MovieDetailModel>) {
                _movieDetailModel.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieDetailModel>, t: Throwable) {
                _movieDetailModel.postValue(null)
            }
        })

    }



}