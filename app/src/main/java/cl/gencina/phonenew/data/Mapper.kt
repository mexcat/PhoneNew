package cl.gencina.phonenew.data

import cl.gencina.phonenew.data.local.PhoneEntity
import cl.gencina.phonenew.data.remote.Phone

fun Phone.toEntity(): PhoneEntity = PhoneEntity(this.id,this.name,this.price,this.image, null,null,null)


