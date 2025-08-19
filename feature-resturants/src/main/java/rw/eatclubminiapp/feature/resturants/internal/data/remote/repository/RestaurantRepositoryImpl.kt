package rw.eatclubminiapp.feature.resturants.internal.data.remote.repository

import rw.eatclubminiapp.feature.resturants.internal.data.remote.api.RestaurantRemoteDataSource
import rw.eatclubminiapp.feature.resturants.internal.data.remote.mapper.toRestaurant
import rw.eatclubminiapp.feature.resturants.internal.domain.model.Restaurant
import rw.eatclubminiapp.feature.resturants.internal.domain.repository.RestaurantRepository
import javax.inject.Inject

internal class RestaurantRepositoryImpl @Inject constructor(
    private val remoteDataSource: RestaurantRemoteDataSource
) : RestaurantRepository {

    private val cache = mutableMapOf<String, Restaurant>()

    override suspend fun getRestaurants(): List<Restaurant> {
        if (cache.isEmpty()) {
            val fetchedRestaurants = remoteDataSource.getRestaurantDtos().map { it.toRestaurant() }
            cache.putAll(fetchedRestaurants.associateBy { it.objectId })
        }
        return cache.values.toList()
    }

    override suspend fun getRestaurantById(id: String): Restaurant {
        return cache[id] ?: throw NoSuchElementException("Restaurant not found")
    }
}