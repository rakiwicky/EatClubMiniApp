package rw.eatclubminiapp.feature.resturants.internal.domain.usecase

import rw.eatclubminiapp.feature.resturants.internal.domain.model.Restaurant
import rw.eatclubminiapp.feature.resturants.internal.domain.repository.RestaurantRepository

internal class GetRestaurantDetailUseCase(
    private val repository: RestaurantRepository
) {

    suspend operator fun invoke(restaurantId: String): Restaurant {
        return repository.getRestaurantById(restaurantId)
    }
}