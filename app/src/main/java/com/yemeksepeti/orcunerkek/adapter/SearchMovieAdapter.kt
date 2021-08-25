package com.yemeksepeti.orcunerkek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yemeksepeti.orcunerkek.R
import com.yemeksepeti.orcunerkek.model.MovieResultModel


class SearchMovieAdapter(private val movies: ArrayList<MovieResultModel>) : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {
    private var mListener: OnItemClickListener? = null
    var context : Context ? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    class SearchMovieViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        var tv_average_point : TextView
        var tv_poster_title : TextView
        var img_poster : ImageView


        init {
            tv_average_point = itemView.findViewById(R.id.tv_average_point)
            tv_poster_title = itemView.findViewById(R.id.tv_poster_title)
            img_poster = itemView.findViewById(R.id.img_poster)

            itemView.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        context = parent.context
        return SearchMovieViewHolder(v, mListener)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        val exam = movies[position]
   /*     holder.tv_exam_name.setText(exam.exam_name)
        holder.tv_exam_date.setText(exam.exam_date)*/
        holder.tv_average_point.setText(movies[position].vote_average.toString())
        holder.tv_poster_title.setText(movies[position].title.toString())
        Glide.with(context!!)
                .load("https://image.tmdb.org/t/p/w500/"+movies[position].poster_path)
                .fitCenter()
                .into(holder.img_poster)




    }

    override fun getItemCount(): Int {
        return movies.size
    }
}