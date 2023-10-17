package com.example.devnatif.ui.Note

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.devnatif.MainActivity
import com.example.devnatif.Note
import com.example.devnatif.R
import com.example.devnatif.databinding.FragmentNoteBinding
import com.example.devnatif.databinding.FragmentNotificationsBinding
import com.example.devnatif.ui.notifications.NotificationsViewModel
import com.google.android.material.textfield.TextInputEditText

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val noteViewModel =
            ViewModelProvider(this).get(NoteViewModel::class.java)

        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val myButton = root.findViewById<Button>(R.id.valideContent)

        myButton.setOnClickListener {

            //set le contenu de la note avec la valeur de noteContentText (TextInputEditText) en string
            val noteContentText = root.findViewById<TextInputEditText>(R.id.noteContentText)
            val noteContent = noteContentText.text.toString()
            MainActivity().notes.forEach {
                it.setContent(noteContent)
            }
            // Obtenez le gestionnaire de fragments
            val fragmentManager = requireActivity().supportFragmentManager
            // Identifiez le fragment que vous souhaitez supprimer (remplacer 'YourFragment' par la classe de votre fragment)
            val fragmentToRemove = fragmentManager.findFragmentByTag("YourFragmentTag")
            // Commencez une transaction pour retirer le fragment
            val transaction = fragmentManager.beginTransaction()
            // Indiquez au gestionnaire de fragments de retirer le fragment
            transaction.remove(this)
            // Validez la transaction pour appliquer la suppression du fragment
            transaction.commit()
        }

        val textView: TextInputEditText = binding.noteContentText
        noteViewModel.text.observe(viewLifecycleOwner) {
            textView.text = Editable.Factory.getInstance().newEditable(this.arguments?.getString("content"))
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}