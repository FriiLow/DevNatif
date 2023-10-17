package com.example.devnatif.ui.dashboard

import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.devnatif.MainActivity
import com.example.devnatif.Note
import com.example.devnatif.R
import com.example.devnatif.databinding.FragmentDashboardBinding
import com.example.devnatif.ui.Note.NoteFragment

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val dashNotes: ArrayList<Note> = (activity as MainActivity).notes

        val parentLayout: LinearLayout = root.findViewById(R.id.noteContainerLayout);

        dashNotes.forEach { note ->
            val cardView = this.context?.let { CardView(it) }
            val cardLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            cardLayoutParams.setMargins(5, 5, 5, 5)
            if (cardView != null) {
                cardView.layoutParams = cardLayoutParams
                cardView.radius = resources.getDimension(R.dimen.cardCornerRadius)
                cardView.cardElevation = resources.getDimension(R.dimen.cardElevation)
            }

            val imageView = ImageView(this.context)
            val imageLayoutParams = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.imageSize),
                resources.getDimensionPixelSize(R.dimen.imageSize)
            )
            imageLayoutParams.setMargins(5, 5, 5, 5)
            imageView.layoutParams = imageLayoutParams
            imageView.elevation = resources.getDimension(R.dimen.imageElevation)
            imageView.setImageResource(R.drawable.profil)

            val textView = TextView(this.context)
            val textLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            textView.layoutParams = textLayoutParams
            textView.gravity = Gravity.CENTER
            textView.setPadding(resources.getDimensionPixelSize(R.dimen.textPadding), resources.getDimensionPixelSize(R.dimen.textPaddingTop), 0, 0)
            textView.text = note.getTitle()
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23f)
            textView.setTypeface(null, Typeface.BOLD)

            if (cardView != null) {
                cardView.addView(imageView)
                cardView.addView(textView)
                cardView.setOnClickListener {
                    //bundle
                    val bundle = Bundle()
                    bundle.putString("title", note.getTitle())
                    bundle.putString("content", note.getContent())
                    bundle.putInt("id", note.getId())
                    val fragmentToReplace = NoteFragment()
                    fragmentToReplace.arguments = bundle
                    replaceFragment(fragmentToReplace)
                }
            }

            parentLayout.addView(cardView)
        }

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    private fun replaceFragment(fragment: Fragment) {
        // Obtenez le gestionnaire de fragments
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, fragment)
        fragmentTransaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}