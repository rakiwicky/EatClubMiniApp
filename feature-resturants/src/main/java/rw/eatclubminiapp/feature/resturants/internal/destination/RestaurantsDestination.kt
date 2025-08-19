package rw.eatclubminiapp.feature.resturants.internal.destination

import kotlinx.serialization.Serializable

internal sealed interface RestaurantsDestination {

    @Serializable
    data object ListScreen : RestaurantsDestination

    @Serializable
    data class DetailScreen(val restaurantId: String) : RestaurantsDestination
}