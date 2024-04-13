package com.example.test2.detail

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import nl.move.presentation.photodetail.listener.DetailNavigationListener

class DetailNavigator(
    private val destinationsNavigator: DestinationsNavigator,
) : DetailNavigationListener {

    override fun closeDetails() { destinationsNavigator.navigateUp() }
}