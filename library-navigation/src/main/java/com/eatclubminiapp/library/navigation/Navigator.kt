package com.eatclubminiapp.library.navigation

import androidx.navigation.NavController

sealed interface NavigationAction {
    fun performAction(navigationController: NavController)
}

data object PopBackStack: NavigationAction {
    override fun performAction(navigationController: NavController) {
        navigationController.popBackStack()
    }
}

data class Navigate(val route: Any): NavigationAction {
    override fun performAction(navigationController: NavController) {
        navigationController.navigate(route)
    }
}