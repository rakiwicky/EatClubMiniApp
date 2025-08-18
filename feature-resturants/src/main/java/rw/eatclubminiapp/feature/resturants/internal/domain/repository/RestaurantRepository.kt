package rw.eatclubminiapp.feature.resturants.internal.domain.repository

import rw.eatclubminiapp.feature.resturants.internal.domain.model.Restaurant

internal interface RestaurantRepository {

    suspend fun getRestaurants(): List<Restaurant>
}