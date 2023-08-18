package cl.gencina.phonenew.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.gencina.phonenew.databinding.FragmentListaTelefonosBinding

class ListaTelefonosFragment : Fragment() {
    lateinit var binding : FragmentListaTelefonosBinding
    private val viewmodel: TelefonoViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListaTelefonosBinding.inflate(layoutInflater, container, false)
        viewmodel.getData()
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        val  adapter = AdapterListaTelefonos()
        binding.rvLista.adapter = adapter

        viewmodel.listaTelefonosLiveData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }
}