package uz.os3ketchup.sputnikgo.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.os3ketchup.sputnikgo.R
import uz.os3ketchup.sputnikgo.databinding.FragmentRemainderItemBinding


class RemainderItemFragment : Fragment() {
private lateinit var binding: FragmentRemainderItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRemainderItemBinding.inflate(inflater,container,false)
        return binding.root
    }

}