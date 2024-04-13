package com.example.test2.generic.di

import nl.move.domain.comments.GetComments
import nl.move.domain.photos.GetPhoto
import nl.move.domain.photos.GetPhotos
import org.koin.dsl.module

val domainModule = module {

    factory { GetPhoto(get()) }
    factory { GetPhotos(get()) }
    factory { GetComments(get()) }
}