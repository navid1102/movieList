package com.navid.movielist_mvvm.ui.home

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.navid.movielist_mvvm.R
import com.navid.movielist_mvvm.databinding.FragmentHomeBinding
import com.navid.movielist_mvvm.module.BaseFragment
import com.navid.movielist_mvvm.ui.home.adapter.AutoSuggestAdapter
import com.navid.movielist_mvvm.ui.home.adapter.MovieListAdapter
import com.navid.movielist_mvvm.ui.home.adapter.SpinnerAdapter
import com.navid.moviescore.BundleKeys
import com.navid.moviescore.core.home.pojo.Genre
import kotlinx.android.synthetic.main.serchbar.*
import kotlinx.android.synthetic.main.serchbar.view.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(), AdapterView.OnItemSelectedListener,
    TextWatcher,
    AdapterView.OnItemClickListener {

    private val args = Bundle()
    private var currentGenreId: String? = null
    private lateinit var scrollListener: ScrollListener

    private val adapter =
        MovieListAdapter(mutableListOf())
    private val autoSuggestAdapter by lazy {
        AutoSuggestAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line
        )
    }


    val vm: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }


    override fun getViewRes(): Int {
        return R.layout.fragment_home
    }

    override fun oncreate() {

        binding.homeViewModel = vm
        vm.getMovieList(1, "")
        setUpSearchView()
        setUpRecyclerView()
        vm.getGenerList()
        binding.header.spinner.onItemSelectedListener = this

        vm.liveGenerList.observe(this, Observer {
            SpinnerAdapter(requireContext(), it.genres.toMutableList()).also {
                spinner.adapter = it
            }
        })

        vm.liveMovieList.observe(this, Observer {
            binding.loading.visibility = View.GONE
            if (it.results != null)
                adapter.addMovies(it.results)
            adapter.onItemClicked {
                args.putString(BundleKeys.Movie_ID, it)
                findNavController().navigate(R.id.detailFragment, args)
            }
        })

        vm.liveSearch.observe(this, Observer {
            if (it.results != null)
                autoSuggestAdapter.setData(it.results)
        })
    }

    private fun setUpSearchView() {
        binding.header.searchView.setAdapter(autoSuggestAdapter)
        binding.header.searchView.addTextChangedListener(this)
        binding.header.searchView.onItemClickListener = this
    }

    private fun setUpRecyclerView() {
        binding.rcvMovieList.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rcvMovieList.layoutManager = linearLayoutManager
        scrollListener = ScrollListener(linearLayoutManager)
        binding.rcvMovieList.addOnScrollListener(scrollListener)
    }

    fun getMovieList(page: Int, genre: String) {
        vm.getMovieList(page, genre)
    }

    fun hideKeyboard() {
        val imm: InputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity?.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        hideKeyboard()
        args.putString(BundleKeys.Movie_ID, id.toString())
        findNavController().navigate(R.id.detailFragment, args)
    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val genre = parent?.getItemAtPosition(position) as Genre
        adapter.clearMovies()
        currentGenreId = if (genre.id != null) genre.id.toString() else null
        scrollListener.resetState()
        getMovieList(1, currentGenreId ?: "")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (p0 != null)
        vm.getSearch(p0.toString())

    }

    inner class ScrollListener(linearLayoutManager: LinearLayoutManager?) :
        EndlessRecyclerOnScrollListener(linearLayoutManager!!) {
        override fun onLoadMore(page: Int) {
            binding.loading.visibility = View.VISIBLE
            vm.getMovieList(page, "")
        }
    }
}