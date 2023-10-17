package com.example.devnatif.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.devnatif.MainActivity
import com.example.devnatif.Note
import com.example.devnatif.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun creatNewNote(view: View?){
        //écrire dans la console
        println("click")
        //toast
        Toast.makeText(activity, "click", Toast.LENGTH_SHORT).show()
        //init une nouvelle note avec pour titre la valeur du champ noteTitle
        val note = Note(binding.noteTitle.text.toString(), "")
        //ajoute la note à la liste
        (activity as MainActivity).addNote (note)
    }
}