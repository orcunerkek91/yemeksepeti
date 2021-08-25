package com.yemeksepeti.orcunerkek.view

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.stetho.Stetho
import com.yemeksepeti.orcunerkek.R
import com.yemeksepeti.orcunerkek.adapter.SearchMovieAdapter
import com.yemeksepeti.orcunerkek.model.MovieResultModel
import com.yemeksepeti.orcunerkek.model.SearchMovieModel
import com.yemeksepeti.orcunerkek.viewmodel.SearchMovieViewModel
import kotlinx.android.synthetic.main.activity_movies_page.*

class MovieSearchActivity : AppCompatActivity() {

    lateinit var searchMovieViewModel: SearchMovieViewModel
    var searchMovieAdapter: SearchMovieAdapter? = null

    var foundedMovieDetails = SearchMovieModel()
    private lateinit var linearLayoutManager: LinearLayoutManager

    companion object {
        var pageCounter = 0
        var myLastListCounter = 0
        var myCounterForList = 0
        var stopFlag=false
        var toastMessageFlag=false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_page)
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())

        searchMovieViewModel = ViewModelProvider(this).get(SearchMovieViewModel::class.java)
        linearLayoutManager = LinearLayoutManager(this.applicationContext)



        searchMovieViewModel.getSearchModelObserver()?.observe(this, Observer {
            stopFlag =false
            if (it != null) {
                if (it.total_pages != null) {
                    pageCounter = it.total_pages!!
                }

                if (foundedMovieDetails.movie_result_model != null) {
                    if (foundedMovieDetails!!.movie_result_model!!.size > 0) {
                        for (x in 0 until it.movie_result_model!!.size)
                            foundedMovieDetails?.movie_result_model!!.add(it.movie_result_model!!.get(x))
                    }
                    else {
                        foundedMovieDetails = it
                    }
                } else
                    foundedMovieDetails = it


                linearLayoutManager = GridLayoutManager(this.applicationContext, 3)

                searchMovieAdapter = SearchMovieAdapter(foundedMovieDetails.movie_result_model as ArrayList<MovieResultModel>)
                rcv_movies!!.setHasFixedSize(true)
                rcv_movies!!.setLayoutManager(linearLayoutManager)
                rcv_movies!!.setAdapter(searchMovieAdapter)

                rcv_movies?.scrollToPosition(myLastListCounter)


                searchMovieAdapter!!.setOnItemClickListener(object : SearchMovieAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        foundedMovieDetails!!.movie_result_model?.get(position)?.id?.let { it1 -> searchMovieViewModel.getMovieDetail(it1) }
                    }

                })

                rcv_movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if(!stopFlag){
                        myLastListCounter = linearLayoutManager.itemCount

                        var lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition()

                        if (myLastListCounter == lastVisible + 1 && myLastListCounter % 20 == 0) {
                            // myAddListFlag=true
                            myCounterForList = myCounterForList + 1;
                            if (pageCounter > myCounterForList){
                                searchMovieViewModel.getMovies(et_movies_page.text.toString(), myCounterForList + 1)
                                stopFlag =true

                            }
                            else {
                                Toast.makeText(this@MovieSearchActivity, "Üzgünüz, bu arama için daha fazla içerik bulunamadı.", Toast.LENGTH_SHORT).show()
                            }
                        }
                            if(myLastListCounter ==foundedMovieDetails.total_result && !toastMessageFlag){
                                toastMessageFlag =true
                                Toast.makeText(this@MovieSearchActivity, "Üzgünüz, bu arama için daha fazla içerik bulunamadı.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        })

        cv_search.setOnClickListener {
            if (et_movies_page.text.toString().length >= 3) {
                myLastListCounter = 0
                myCounterForList = 0
                toastMessageFlag = false
                foundedMovieDetails.movie_result_model?.clear()
                searchMovieViewModel.getMovies(et_movies_page.text.toString(), 1)
            } else {
                Toast.makeText(this.applicationContext, "Lütfen en az 3 harf giriniz...", Toast.LENGTH_SHORT).show()
            }

        }

        searchMovieViewModel.getMovieDetailObserver()?.observe(this, Observer {
            if (it != null) {
                var intent = Intent(this, MovieDetailActivity::class.java)
                intent.putExtra("MovieDetailModel", it as Parcelable?)

                startActivity(intent)
            }

        })

        /*et_movies_page.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }
        })*/
    }


}