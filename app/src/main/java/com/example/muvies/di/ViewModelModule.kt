package com.example.muvies.di

import com.example.muvies.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { HomeViewModel(get()) }

}