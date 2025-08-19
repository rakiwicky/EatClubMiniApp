package rw.eatclubminiapp.feature.resturants.internal.data.remote.api

import rw.eatclubminiapp.feature.resturants.internal.data.remote.dto.RestaurantsDto

internal class RestaurantRemoteDataSource(
    private val apiService: RestaurantApiService
) {
    suspend fun getRestaurantDtos(): List<RestaurantsDto.RestaurantDto> {
        return apiService.getRestaurants().restaurants
    }
}