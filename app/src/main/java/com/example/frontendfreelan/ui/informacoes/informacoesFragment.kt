package com.example.frontendfreelan.ui.informacoes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.frontendfreelan.R
import com.example.frontendfreelan.databinding.FragmentInformacoesBinding

class InformacoesFragment : Fragment() {

    private var _binding: FragmentInformacoesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformacoesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configura o clique nos links externos
        binding.root.findViewById<TextView>(R.id.link_externo_1).setOnClickListener {
            openLink(it.tag.toString())
        }
        binding.root.findViewById<TextView>(R.id.link_externo_2).setOnClickListener {
            openLink(it.tag.toString())
        }

        // Configura o clique no botão do WhatsApp
        binding.buttonWhatsapp.setOnClickListener {
            openWhatsApp("+55 48 9 8806-6473")
        }

        return root
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun openWhatsApp(phoneNumber: String) {
        val url = "https://wa.me/$phoneNumber"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}