package rw.eatclubminiapp.feature.resturants.internal.presentation.list.model

import rw.eatclubminiapp.feature.resturants.internal.domain.model.Restaurant

internal data class RestaurantListItem(
    val name: String,
    val suburb: String,
    val cuisines: String,
    val imageLink: String,
    val deals: List<Restaurant.Deal>,
    val itemOnClick: suspend () -> Unit
)