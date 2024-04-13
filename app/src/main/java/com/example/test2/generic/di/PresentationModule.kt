package com.example.test2.generic.di

import nl.move.presentation.photodetail.DetailViewModel
import nl.move.presentation.photolist.PhotoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { PhotoListViewModel(get()) }
    viewModel { args -> DetailViewModel(get(), get(), args[0]) }
}