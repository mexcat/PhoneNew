package cl.gencina.phonenew.data

import cl.gencina.phonenew.data.local.PhoneEntity
import cl.gencina.phonenew.data.remote.Phone
import org.junit.Assert.*

import org.junit.Test

class MapperKtTest {
    fun Phone.toEntity(): PhoneEntity = PhoneEntity(this.id,this.name,this.price,this.image, null,null,null)

    @Test
    fun toEntity() {
        //DADO ESTE VALOR

        val phone  = Phone(123,"test", 99999, "image",null,null,null)

        //HAGO ESTO

        val result = phone.toEntity()
        //Y ESPERO ESTO

        assertEquals(phone.id, result.id)
        assertEquals(phone.name, result.name)
        assertEquals(phone.price, result.price)
        assertEquals(phone.image, result.image)

        assertEquals(phone.description, result.description)
        assertEquals(phone.lastPrice, result.lastPrice)
        assertEquals(phone.credit, result.credit)
        //o
        //Truth.assertThat(result.nombreRaza).isEqualTo(id)//
    }
}