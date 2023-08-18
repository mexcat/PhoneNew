package cl.gencina.phonenew.vista

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import cl.gencina.phonenew.R
import cl.gencina.phonenew.data.local.PhoneEntity
import cl.gencina.phonenew.databinding.FragmentDetalleBinding
import coil.load


class DetalleFragment : Fragment() {

    lateinit var binding : FragmentDetalleBinding
    private val viewModel: TelefonoViewModel by activityViewModels()
    private var id : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("id")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetalleBinding.inflate(layoutInflater, container, false)
        viewModel.getDetalle(id)
        loadInfo(id)
        return binding.root
    }

    private fun loadInfo(id: Int) {

        viewModel.telefonoLiveData(id).observe(viewLifecycleOwner) { it ->
            binding.ivDetalle.load(it.image)
            binding.tvNombre.text = it.name
            binding.tvPrecioAntiguo.text = getString(R.string.antes, it.lastPrice.toString())
            binding.tvPrecioNuevo.text = getString(R.string.ahora, it.price.toString())
            binding.tvDescripcion.text = it.description

            binding.tvTipoCredito.text = if(it.credit == true){
                getString(R.string.acepta_credito)}else{
                getString(R.string.s_lo_efectivo)}

            binding.fbEmail.setOnClickListener {_ ->
                binding.fbEmail.visibility = View.GONE
                binding.cvEmail.visibility = View.VISIBLE

                binding.etBodyEmail.setText(getString(
                    R.string.body_email,
                    it.name,
                    it.id.toString()
                ))
            }

            binding.fbEmail.setOnClickListener {_ ->
/*                if(!binding.etDireccionEmail.text.isNullOrBlank() &&
                    binding.etDireccionEmail.text.contains("@") &&
                    !binding.etBodyEmail.text.isNullOrBlank()){*/
                    val asunto = getString(R.string.consulta_id, it.name, it.id.toString())
                    enviarCorreo(asunto, it)
                    findNavController().navigate(R.id.action_detalleFragment_to_listaTelefonosFragment)
               /* }else{
                    Toast.makeText(
                        context,
                        getString(R.string.ingrese_su_direccion_de_email_y_su_n_mero_de_tel_fono),
                        Toast.LENGTH_SHORT
                    ).show()
                }*/
            }
        }
    }

    private fun enviarCorreo(asunto: String, phoneEntity: PhoneEntity) {
        val email = "info@novaera.cl"
        val emailIntent = Intent(Intent.ACTION_SEND, Uri.parse(email))
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto)
        emailIntent.putExtra(Intent.EXTRA_TEXT, getString(
            R.string.body_email,
            phoneEntity.name,
            phoneEntity.id.toString()
        ))
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.enviar_email)))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                context,
                getString(R.string.no_existe_ning_n_cliente_de_email_instalado),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}