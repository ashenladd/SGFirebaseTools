package com.example.sgfirebasetools.feature

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sgfirebasetools.data.source.model.MakananResponse
import com.example.sgfirebasetools.data.source.network.MakananService
import com.example.sgfirebasetools.databinding.FragmentMainBinding
import com.example.sgfirebasetools.feature.adapter.MakananAdapter
import com.example.sgfirebasetools.feature.adapter.MakananListener
import com.example.sgfirebasetools.feature.adapter.MakananModel

class MainFragment : Fragment(), MakananListener {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mMakananAdapter: MakananAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(
            view,
            savedInstanceState
        )

        setupRecyclerView()
        loadData()
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.apply {
            btnAdd.setOnClickListener {
                addPokemon()
            }
        }
    }


    private fun setupRecyclerView() {
        binding.apply {
            rvMakanan.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun addPokemon() {
        MakananService().addPokemon(
            MakananResponse(
                "Spaghetti",
                "https://cdn1-production-images-kly.akamaized.net/uBuE5OD3B9pUTVNJd81cB819z7Y=/0x194:5616x3359/800x450/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/3048436/original/030475400_1581499756-shutterstock_413580649.jpg"
            )
        ).addOnSuccessListener {
            Toast.makeText(
                requireContext(),
                "Success to add data",
                Toast.LENGTH_SHORT
            ).show()
            loadData()
        }.addOnFailureListener {
            Toast.makeText(
                requireContext(),
                "Failed to add data",
                Toast.LENGTH_SHORT
            ).show()
            Log.e(
                "Makanan",
                "addPokemon: " + it.message
            )
        }
    }

    private fun loadData() {
        MakananService().getAllMakanan().addOnSuccessListener {
            Toast.makeText(
                requireContext(),
                "Success to get data ${it.size()} data",
                Toast.LENGTH_SHORT
            ).show()
            val list: MutableList<MakananModel> = mutableListOf()

            it.forEach { document ->
                val data = document.toObject(MakananResponse::class.java)
                list.add(
                    MakananModel(
                        document.id,
                        data.nama.orEmpty(),
                        data.url.orEmpty()
                    )
                )
            }
            mMakananAdapter = MakananAdapter(
                list,
                this
            )
            binding.rvMakanan.adapter = mMakananAdapter

        }.addOnFailureListener {
            Toast.makeText(
                requireContext(),
                "Failed to get data",
                Toast.LENGTH_SHORT
            ).show()
            Log.e(
                "Makanan",
                "loadData: " + it.message
            )
        }
    }

    override fun onMakananClicked(makananId: String) {
        Toast.makeText(
            requireContext(),
            "Makanan id: $makananId",
            Toast.LENGTH_SHORT
        ).show()
    }
}