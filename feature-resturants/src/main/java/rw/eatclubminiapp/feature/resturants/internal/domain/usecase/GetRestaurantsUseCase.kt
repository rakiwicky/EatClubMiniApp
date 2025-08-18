package rw.eatclubminiapp.feature.resturants.internal.domain.usecase

import rw.eatclubminiapp.feature.resturants.internal.domain.repository.RestaurantRepository
import javax.inject.Inject

internal class GetRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {

    suspend operator fun invoke() = repository.getRestaurants()
}