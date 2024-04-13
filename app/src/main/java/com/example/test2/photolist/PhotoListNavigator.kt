package com.example.test2.photolist

import com.ramcosta.composedestinations.generated.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import nl.move.presentation.photolist.listener.PhotoListNavigationListener

class PhotoListNavigator(
    private val destinationsNavigator: DestinationsNavigator,
) : PhotoListNavigationListener {

    override fun openDetails(id: Int) {
        destinationsNavigator.navigate(direction = DetailScreenDestination(id))
    }
}