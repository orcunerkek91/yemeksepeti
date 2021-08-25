package com.yemeksepeti.orcunerkek.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.yemeksepeti.orcunerkek.R
import com.yemeksepeti.orcunerkek.model.MovieDetailModel
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setSupportActionBar(toolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        val i = intent
        val movieDetailModel: MovieDetailModel? = i.getParcelableExtra<MovieDetailModel>("MovieDetailModel")
        var strGenres=""

        getSupportActionBar()?.setTitle(movieDetailModel?.title.toString())
        tv_average_point_movie_detail.setText(movieDetailModel?.vote_average.toString())
        tv_overview.setText(movieDetailModel?.overview.toString())

        Glide.with(this!!)
                .load("https://image.tmdb.org/t/p/w500/" + movieDetailModel?.poster_path)
                .fitCenter()
                .into(img_poster_movie_detail)


        Glide.with(this!!)
                .load("https://image.tmdb.org/t/p/w500/" + movieDetailModel?.backdrop_path)
                .fitCenter()
                .into(img_backdrop)

        if(movieDetailModel?.genres?.size!!>0){
            for(x in 0 until movieDetailModel?.genres!!.size){
                if(x == 0){
                    strGenres = movieDetailModel?.genres!![x].name.toString()
                }
                else
                strGenres = strGenres +"/"+ movieDetailModel?.genres!![x].name.toString()
            }
            tv_genres.setText(strGenres)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return super.onSupportNavigateUp()
    }

}