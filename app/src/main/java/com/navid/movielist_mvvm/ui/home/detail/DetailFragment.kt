package com.navid.movielist_mvvm.ui.home.detail


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.navid.movielist_mvvm.R
import com.navid.movielist_mvvm.databinding.FragmentDetailBinding
import com.navid.movielist_mvvm.module.BaseFragment
import com.navid.moviescore.BaseUri
import com.navid.moviescore.BundleKeys
import setImageUrl

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    var movieId: String ?=null

    val vm: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }


    override fun getViewRes(): Int {
        return R.layout.fragment_detail
    }

    override fun oncreate() {
        binding.detailViewModel = vm
        movieId = requireArguments().getString(BundleKeys.Movie_ID).toString()


        vm.getDetailMovie(movieId?:"")

        vm.liveDetailMovie.observe(this, Observer {
            binding.txtMovieName.text = it.title.toString() ?: ""
            binding.txtHeaderYear.text = it.release_date.toString() ?: ""
            binding.txtDetail.text = it.overview.toString() ?: ""
            binding.imgDetailPoster.setImageUrl(BaseUri.TMDB_IMAGEURL + it.poster_path)
            binding.ratingBar.rating=it.vote_average.toFloat()/2
        })
    }


}