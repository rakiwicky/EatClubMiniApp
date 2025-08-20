package rw.eatclubminiapp.feature.resturants.internal.data.remote.mapper

import rw.eatclubminiapp.feature.resturants.internal.data.remote.dto.RestaurantsDto
import rw.eatclubminiapp.feature.resturants.internal.domain.model.Restaurant

internal fun RestaurantsDto.RestaurantDto.toRestaurant(): Restaurant {
    return Restaurant(
        id = objectId,
        name = name,
        address1 = address1,
        suburb = suburb,
        cuisines = cuisines,
        imageLink = imageLink,
        open = open,
        close = close,
        deals = dealDtos.map { it.toDeal() }
    )
}

private fun RestaurantsDto.RestaurantDto.DealDto.toDeal(): Restaurant.Deal {
    return Restaurant.Deal(
        id = objectId,
        discount = discount,
        dineIn = dineIn,
        lightning = lightning,
        open = open,
        close = close,
        qtyLeft = qtyLeft
    )
}