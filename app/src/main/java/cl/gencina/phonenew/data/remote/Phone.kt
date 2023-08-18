package cl.gencina.phonenew.data.remote

data class Phone(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description:String?,
    val lastPrice:Int?,
    val credit: Boolean?
)