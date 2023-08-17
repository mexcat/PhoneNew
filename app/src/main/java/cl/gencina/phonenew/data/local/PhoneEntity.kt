package cl.gencina.phonenew.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabla_telefono")
data class PhoneEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description:String?,
    val lastPrice:Int?,
    val credit: Boolean?
)