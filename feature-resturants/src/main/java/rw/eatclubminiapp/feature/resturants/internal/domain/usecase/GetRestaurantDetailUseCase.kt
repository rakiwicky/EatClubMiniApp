package rw.eatclubminiapp.feature.resturants.internal.domain.usecase

import rw.eatclubminiapp.feature.resturants.internal.domain.repository.RestaurantRepository

internal class GetRestaurantDetailUseCase(
    private val repository: RestaurantRepository
) {

    suspend operator fun invoke(restaurantId: String) = repository.getRestaurantById(restaurantId)
}