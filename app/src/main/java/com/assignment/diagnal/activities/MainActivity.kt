package com.assignment.diagnal.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.assignment.diagnal.R
import com.assignment.diagnal.adapters.MoviesListAdapter
import com.assignment.diagnal.core.CommonUtils
import com.assignment.diagnal.core.PaginationScrollListener
import com.assignment.diagnal.core.ViewModelFactory
import com.assignment.diagnal.core.addGridItemDecorations
import com.assignment.diagnal.core.getViewModelFromFactory
import com.assignment.diagnal.core.removeItemDecorations
import com.assignment.diagnal.core.showToast
import com.assignment.diagnal.databinding.ActivityMainBinding
import com.assignment.diagnal.di.MoviesApplication
import com.assignment.diagnal.models.ContentItem
import com.assignment.diagnal.viewmodels.MoviesState
import com.assignment.diagnal.viewmodels.MoviesViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val moviesList: java.util.ArrayList<ContentItem?> = arrayListOf()

    private val mMoviesListAdapter: MoviesListAdapter by lazy {
        MoviesListAdapter(mActivity!!)
    }
    private val mGridLayoutManager: GridLayoutManager by lazy {
        GridLayoutManager(mActivity!!, spanCount)
    }

    private var spanCount = 3
    private var isLoading = false
    private var isLastPage = false
    private var currentPage = 1
    private var lastPage = 3
    private var isSearchMode = false

    @Inject
    internal lateinit var vmFactory: ViewModelFactory<MoviesViewModel>
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        MoviesApplication.component.inject(this)
        setContentView(binding.root)
        viewModel = getViewModelFromFactory(vmFactory)

        setUpClicks(
            binding.imgBack, binding.imgSearch, binding.imgCloseSearch
        )
        initMoviesList()
        setObserver()
    }

    private fun initMoviesList() {
        setGridAsPerOrientation(resources.configuration)

        binding.recyclerMovies.apply {
            layoutManager = mGridLayoutManager
            adapter = mMoviesListAdapter
        }
    }

    private fun setObserver() {
        viewModel.liveDataObserver.observe(this) {
            when (it) {
                is MoviesState.Success -> {
                    it.moviesResponse.page?.let { moviePage ->
                        runOnUiThread {
                            binding.txtTitle.text = moviePage.title

                            moviesList.addAll(moviePage.contentItems?.content!!)
                            mMoviesListAdapter.addAll(moviePage.contentItems.content)

                            isLoading = false
                            if (currentPage == lastPage) {
                                isLastPage = true
                            }
                        }
                    }
                }

                is MoviesState.Error -> {
                    showToast(it.error)
                }

                else -> {}
            }
        }

        viewModel.getMoviesList(currentPage)

        binding.recyclerMovies.addOnScrollListener(object :
            PaginationScrollListener(mGridLayoutManager) {
            override fun loadMoreItems() {
                if (isSearchMode) {
                    return
                }

                this@MainActivity.isLoading = true
                currentPage += 1
                viewModel.getMoviesList(currentPage = currentPage)
            }

            override fun isLastPage(): Boolean {
                return this@MainActivity.isLastPage
            }

            override fun isLoading(): Boolean {
                return this@MainActivity.isLoading
            }
        })

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length >= 3 || s.toString() == "") {
                    filter(s.toString())
                }
            }
        })
    }

    private fun animationSearchView() {
        if (binding.mcvSearch.visibility == View.GONE) {
            binding.mcvSearch.visibility = View.VISIBLE
            binding.mcvSearch.alpha = 0f
            binding.mcvSearch.animate().alpha(1f).setDuration(300)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        binding.mcvSearch.visibility = View.VISIBLE
                    }
                })

            binding.edtSearch.requestFocus()
            isSearchMode = true
        } else {
            binding.mcvSearch.animate().alpha(0f).setDuration(300)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        binding.mcvSearch.visibility = View.GONE
                    }
                })

            CommonUtils.hideKeyBoard(binding.root)
            isSearchMode = false
        }
    }

    fun filter(text: String) {
        val temp: ArrayList<ContentItem?> = ArrayList()
        for (d in moviesList) {
            if (d?.name!!.lowercase().contains(text.lowercase())) {
                temp.add(d)
            }
        }

        binding.lblNotFound.visibility = if (temp.isEmpty()) View.VISIBLE else View.GONE

        mMoviesListAdapter.updateList(temp)
        mMoviesListAdapter.searchedText = text.lowercase()
    }

    private fun setGridAsPerOrientation(config: Configuration) {
        spanCount = if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            3
        } else {
            7
        }
        binding.recyclerMovies.removeItemDecorations()
        binding.recyclerMovies.addGridItemDecorations(30, spanCount)
        mGridLayoutManager.spanCount = spanCount
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setGridAsPerOrientation(newConfig)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.imgBack -> {
                onBackPressedDispatcher.onBackPressed()
            }

            R.id.imgSearch -> {
                animationSearchView()
            }

            R.id.imgCloseSearch -> {
                binding.edtSearch.setText("")
                binding.imgSearch.performClick()
            }
        }
    }
}