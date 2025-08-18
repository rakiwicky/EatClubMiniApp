package rw.eatclubminiapp.feature.resturants.internal.data.remote.repository

import rw.eatclubminiapp.feature.resturants.internal.data.remote.api.RestaurantApiService
import rw.eatclubminiapp.feature.resturants.internal.data.remote.mapper.toRestaurant
import rw.eatclubminiapp.feature.resturants.internal.domain.model.Restaurant
import rw.eatclubminiapp.feature.resturants.internal.domain.repository.RestaurantRepository
import javax.inject.Inject

internal class RestaurantRepositoryImpl @Inject constructor(
    private val apiService: RestaurantApiService
) : RestaurantRepository {

    override suspend fun getRestaurants(): List<Restaurant> {
        return apiService.getRestaurants().restaurants.map { it.toRestaurant() }
    }
}