package rw.eatclubminiapp.feature.resturants.internal.presentation.list.mapper

import rw.eatclubminiapp.feature.resturants.internal.domain.model.Restaurant
import rw.eatclubminiapp.feature.resturants.internal.presentation.list.model.RestaurantListItem
import javax.inject.Inject

internal class RestaurantListItemMapper @Inject constructor() {

    fun create(
        restaurants: List<Restaurant>,
        onItemClick: suspend (Restaurant) -> Unit
    ): List<RestaurantListItem> {
        return restaurants.map {
            RestaurantListItem(
                name = it.name,
                suburb = it.suburb,
                cuisines = it.cuisines.joinToString(", "),
                imageLink = it.imageLink,
                deals = it.deals.sortedByDescending { deal -> deal.discount },
                itemOnClick = { onItemClick(it) }
            )
        }
    }
}