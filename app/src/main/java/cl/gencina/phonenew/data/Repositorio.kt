package cl.gencina.phonenew.data

import android.util.Log
import cl.gencina.phonenew.data.local.PhoneDao
import cl.gencina.phonenew.data.remote.PhoneApi

class Repositorio(private val phoneApi : PhoneApi, private val phoneDao: PhoneDao) {

    suspend fun cargarListaTelefonos(){
        val response = phoneApi.getAllData()
        if(response.isSuccessful){
            val data = response.body()
            data?.let {telefono ->
                telefono.map{
                    phoneDao.insertBase(it.toEntity())
                }
            }
        }else{
            Log.e("repositorioError", response.body().toString())
        }
    }


    suspend fun cargarDetalleTelefono(id:Int){
        val response = phoneApi.getPhoneDetail(id)
        if(response.isSuccessful){
            val data = response.body()
            data?.let { phoneDao.updatePhone(it.id, it.description, it.lastPrice, it.credit) }
        }else{
            Log.e("repositorioError", response.body().toString())
        }
    }

}