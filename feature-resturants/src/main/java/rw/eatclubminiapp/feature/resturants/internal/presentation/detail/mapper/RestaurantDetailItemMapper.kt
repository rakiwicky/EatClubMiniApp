package rw.eatclubminiapp.feature.resturants.internal.presentation.detail.mapper

import android.content.res.Resources
import rw.eatclubminiapp.feature.resturants.R
import rw.eatclubminiapp.feature.resturants.internal.domain.model.Restaurant
import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.model.RestaurantDetailItem
import javax.inject.Inject

internal class RestaurantDetailItemMapper @Inject constructor(
    private val resources: Resources
) {

    fun create(
        restaurant: Restaurant
    ): RestaurantDetailItem {
        return restaurant.run {
            RestaurantDetailItem(
                name = name,
                suburb = suburb,
                cuisines = cuisines,
                openHours = resources.getString(R.string.feature_restaurants_hours, open, close),
                imageLink = imageLink,
                deals = deals.sortedByDescending { deal -> deal.discount }.map {
                    RestaurantDetailItem.DealItem(
                        discount = resources.getString(R.string.feature_restaurants_deals_discount, it.discount),
                        availabilityInfo =  if (it.open == null || it.close == null) {
                            null
                        } else {
                            resources.getString(R.string.feature_restaurants_deals_between, it.open, it.close)
                        },
                        qtyLeft = resources.getString(R.string.feature_restaurants_deals_left, it.qtyLeft)
                    )
                }
            )
        }
    }
}