package cl.gencina.phonenew.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhoneDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBase(item : PhoneEntity)

    @Query("UPDATE tabla_telefono SET description =:description , lastPrice = :lastPrice, credit = :credit WHERE id = :id" )
    suspend fun updatePhone(id: Int, description: String, lastPrice: Int, credit: Boolean, )

}