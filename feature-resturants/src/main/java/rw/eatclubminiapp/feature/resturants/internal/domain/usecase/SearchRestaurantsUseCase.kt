package rw.eatclubminiapp.feature.resturants.internal.domain.usecase

import rw.eatclubminiapp.feature.resturants.internal.domain.repository.RestaurantRepository
import javax.inject.Inject

internal class SearchRestaurantsUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {

    suspend operator fun invoke(searchText: String) = restaurantRepository.searchRestaurants(searchText)
}