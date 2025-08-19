package rw.eatclubminiapp.feature.resturants

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable
import rw.eatclubminiapp.feature.resturants.internal.destination.RestaurantsDestination
import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.RestaurantDetailScreen
import rw.eatclubminiapp.feature.resturants.internal.presentation.list.RestaurantListScreen

fun restaurantsNavGraph(
    navigationController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.navigation<RestaurantsNavGraph>(
        startDestination = RestaurantsDestination.ListScreen
    ) {
        composable<RestaurantsDestination.ListScreen> {
            RestaurantListScreen(navigationController)
        }

        composable<RestaurantsDestination.DetailScreen> {
            RestaurantDetailScreen()
        }
    }
}

@Serializable
data object RestaurantsNavGraph