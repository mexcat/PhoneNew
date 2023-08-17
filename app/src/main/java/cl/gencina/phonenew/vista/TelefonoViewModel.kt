package cl.gencina.phonenew.vista

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cl.gencina.phonenew.data.Repositorio
import cl.gencina.phonenew.data.local.PhoneDatabase
import cl.gencina.phonenew.data.remote.PhoneRetrofit
import kotlinx.coroutines.launch

class TelefonoViewModel(application: Application): AndroidViewModel(application) {
    private var repositorio : Repositorio
    init {
        val api = PhoneRetrofit.getRetrofit()
        val database = PhoneDatabase.getDatabase(application).PhoneDao()
        repositorio = Repositorio(api, database)
    }

    fun getData() = viewModelScope.launch {
        repositorio.cargarListaTelefonos()
    }
    fun getDetalle(id:Int) = viewModelScope.launch {
        repositorio.cargarDetalleTelefono(id)
    }

    fun listaTelefonosLiveData() = repositorio.getListaTelefonos()

    fun telefonoLiveData(id:Int) = repositorio.getTelefono(id)

}