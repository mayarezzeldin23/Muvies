package com.example.muvies.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.muvies.database.Movies
import com.example.muvies.databinding.FragmentHomeBinding
import com.example.muvies.presentation.addMovie.AddMovieFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.coroutineContext


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    val homeViewModel: HomeViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this



        binding.add.setOnClickListener {
            val addMovieFragment = AddMovieFragment(onAddMovie = {
                homeViewModel.onAddMovie(it)
            })
            addMovieFragment.show(childFragmentManager, "AddMovieFragment")

        }


        observeMovies()


        return binding.root
    }

    private fun observeMovies() {
        homeViewModel.moviesLiveData.observe(viewLifecycleOwner) { movies ->
            if (movies != null) {
                binding.recyclerView.adapter = HomeAdapter(movies = movies, onDeleteMovie = {
                    homeViewModel.onDeleteMovie(it)
                }, onEditMovie = {
                    homeViewModel.getMovieItem(it)
                },
                    onMovieClick = {
                        findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                                it
                            )
                        )
                    })

                Log.i("Observe", movies.toString())
            }
        }
        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.movieItemLiveData.collect { movieItem ->
                    if (movieItem != null) {
                        val addMovieFragment = AddMovieFragment(onAddMovie = {
                            homeViewModel.onEditMovie(it)
                        }, movieItem)
                        addMovieFragment.show(childFragmentManager, "AddMovieFragment")
                    }
                }

            }
        }
    }


}