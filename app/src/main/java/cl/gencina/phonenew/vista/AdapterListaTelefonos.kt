package cl.gencina.phonenew.vista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cl.gencina.phonenew.R
import cl.gencina.phonenew.data.local.PhoneEntity
import cl.gencina.phonenew.databinding.ItemListaBinding
import coil.load

class AdapterListaTelefonos: RecyclerView.Adapter<AdapterListaTelefonos.ListaTalefonosViewHolder>() {
    lateinit var binding : ItemListaBinding
    private val listaTelefonos = mutableListOf<PhoneEntity>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListaTalefonosViewHolder {
        binding = ItemListaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListaTalefonosViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ListaTalefonosViewHolder,
        position: Int
    ) {
        val telefono = listaTelefonos[position]
        holder.bind(telefono)
    }

    override fun getItemCount(): Int {
        return listaTelefonos.size
    }

    fun setData(listaTelefonos: List<PhoneEntity>){
        this.listaTelefonos.clear()
        this.listaTelefonos.addAll(listaTelefonos)
        notifyDataSetChanged()
    }

    class ListaTalefonosViewHolder(private val v: ItemListaBinding):RecyclerView.ViewHolder(v.root) {
        fun bind(phone:PhoneEntity){
            v.ivItem.load(phone.image)
            v.tvModelo.text = phone.name
            v.tvPrice.text = "$ "+ phone.price.toString()

            v.cvItem.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("id", phone.id)
                Navigation.findNavController(v.root).navigate(R.id.action_listaTelefonosFragment_to_detalleFragment, bundle)
            }
        }
    }
}