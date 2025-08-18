package rw.eatclubminiapp.feature.resturants.internal.domain.model

internal data class Restaurant(
    val objectId: String,
    val name: String,
    val address1: String,
    val suburb: String,
    val cuisines: List<String>,
    val imageLink: String,
    val open: String,
    val close: String,
    val deals: List<Deal>
) {

    data class Deal(
        val objectId: String,
        val discount: String?,
        val dineIn: Boolean?,
        val lightning: Boolean?,
        val qtyLeft: String
    )
}