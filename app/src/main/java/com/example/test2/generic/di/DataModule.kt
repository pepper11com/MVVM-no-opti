package com.example.test2.generic.di

import nl.move.data.comments.RemoteCommentRepository
import nl.move.data.comments.network.CommentService
import nl.move.data.network.HttpClientProvider
import nl.move.data.photos.RemotePhotosRepository
import nl.move.data.photos.network.PhotosService
import nl.move.data.photos.storage.PhotoDataStorage
import nl.move.domain.comments.data.CommentRepository
import nl.move.domain.photos.data.PhotoRepository
import org.koin.dsl.module

val dataModule = module {

    single { HttpClientProvider() }
    single { PhotoDataStorage() }
    factory { PhotosService(get()) }
    factory { CommentService(get()) }
    factory<CommentRepository> { RemoteCommentRepository(get()) }
    factory<PhotoRepository> { RemotePhotosRepository(get(), get()) }
}