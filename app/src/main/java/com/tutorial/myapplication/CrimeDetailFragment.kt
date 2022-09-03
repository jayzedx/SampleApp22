package com.tutorial.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.tutorial.myapplication.databinding.ActivityMainBinding
import com.tutorial.myapplication.databinding.FragmentCrimeDetailBinding


private const val ARG_PARAM1 = "param1"


class CrimeDetailFragment : Fragment() {
    private var param1: String? = null

    private var _binding: FragmentCrimeDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    //need to declare args in nav_graph before
    private val args: CrimeDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCrimeDetailBinding.inflate(layoutInflater, container, false)
        Toast.makeText(binding.root.context, args.crimeId.toString(), Toast.LENGTH_SHORT).show()
        return binding.root
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            CrimeDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}