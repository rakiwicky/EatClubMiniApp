package rw.eatclubminiapp.feature.resturants.internal.domain.model

internal data class Restaurant(
    val id: String,
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
        val id: String,
        val discount: Int,
        val dineIn: Boolean?,
        val lightning: Boolean?,
        val open: String?,
        val close: String?,
        val qtyLeft: Int
    )
}