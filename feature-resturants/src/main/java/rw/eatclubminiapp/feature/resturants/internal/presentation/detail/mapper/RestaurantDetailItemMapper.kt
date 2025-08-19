package rw.eatclubminiapp.feature.resturants.internal.presentation.detail.mapper

import rw.eatclubminiapp.feature.resturants.internal.domain.model.Restaurant
import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.model.RestaurantDetailItem
import javax.inject.Inject

internal class RestaurantDetailItemMapper @Inject constructor() {

    fun create(
        restaurant: Restaurant,
    ): RestaurantDetailItem {
        return restaurant.run {
            RestaurantDetailItem(
                name = name,
                suburb = suburb,
                cuisines = cuisines.joinToString(),
                imageLink = imageLink,
                deals = deals.map {
                    RestaurantDetailItem.DealItem(
                        discount = it.discount,
                        availabilityInfo = "Between ${it.open} - ${it.close}",
                        qtyLeft = it.qtyLeft
                    )
                }
            )
        }
    }
}