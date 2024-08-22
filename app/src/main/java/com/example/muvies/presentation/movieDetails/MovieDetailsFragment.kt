package com.example.muvies.presentation.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.muvies.R
import com.example.muvies.databinding.FragmentAddMovieBinding
import com.example.muvies.databinding.FragmentMovieDetailsBinding
import com.example.muvies.utils.setImageUrl


class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentMovieDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.title.text = args.movieItem.title
        binding.overview.text = args.movieItem.overview
        binding.releaseDate.text = args.movieItem.release_date
        binding.rate.text = args.movieItem.rate.toString()
        binding.cast.text = args.movieItem.star
        binding.toolbarMovieName.text = args.movieItem.title
        setImageUrl(binding.poster, args.movieItem.poster_path)
        initVideo(binding.videoView, args.movieItem.trailer_path)



        return binding.root
    }

    private fun initVideo(webView: WebView, videoUrl: String) {

        webView.settings.saveFormData = true
        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.pluginState = WebSettings.PluginState.ON_DEMAND
        webView.settings.mediaPlaybackRequiresUserGesture = false
        webView.webViewClient = WebViewClient()

        webView.loadUrl(videoUrl)
    }

}