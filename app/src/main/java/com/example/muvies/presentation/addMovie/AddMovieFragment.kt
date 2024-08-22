package com.example.muvies.presentation.addMovie

import android.content.Context
import android.graphics.Movie
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.muvies.R
import com.example.muvies.database.Movies
import com.example.muvies.databinding.FragmentAddMovieBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddMovieFragment(val onAddMovie: (Movies) -> Unit , val movies: Movies ?= null) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddMovieBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMovieBinding.inflate(layoutInflater)

        movies?.let {
            binding.titleText.setText(it.title)
            binding.overviewText.setText(it.overview)
            binding.posterText.setText(it.poster_path)
            binding.trailerText.setText(it.trailer_path)
            binding.releaseText.setText(it.release_date)
            binding.rateText.setText(it.rate.toString())
            binding.starText.setText(it.star)
            binding.popularText.isChecked = it.isPopular
            binding.add.text = "Update"
        }

        binding.add.setOnClickListener {
            onAddButtonClicked()
        }
        return binding.root
    }


    private fun onAddButtonClicked() {

        val title = binding.titleText.text.toString()
        val overview = binding.overviewText.text.toString()
        val poster_path = binding.posterText.text.toString()
      //  data:image/jpeg;base64,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
        val trailer_path = binding.trailerText.text.toString()
        //"https://www.youtube.com/embed/DLET_u31M4M"
        val release_date = binding.releaseText.text.toString()
        val rate = binding.rateText.text.toString().toDouble()
        val star = binding.starText.text.toString()
        val isPopular =
            if (binding.popularText.isChecked) {
                true
            } else {
                false
            }
        onAddMovie(
            Movies(
                title,
                overview,
                poster_path,
                trailer_path,
                release_date,
                rate,
                star,
                isPopular
            )
                .apply {
                    if(movies!=null){
                        id = movies.id
                    }

                }
        )
        dismiss()
    }

}