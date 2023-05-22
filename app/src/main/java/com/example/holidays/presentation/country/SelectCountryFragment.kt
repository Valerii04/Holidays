package com.example.holidays.presentation.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.holidays.R
import com.example.holidays.databinding.FragmentSelectCountryBinding

class SelectCountryFragment : Fragment() {
    private var _binding: FragmentSelectCountryBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var wasNavigatedToHolidaysScreen = false
    }

    private val viewModel by viewModels<CountryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedCountry = viewModel.getCountryCode(requireContext())

        if (selectedCountry.isNotEmpty() && !wasNavigatedToHolidaysScreen) {
            wasNavigatedToHolidaysScreen = true
            findNavController().navigate(SelectCountryFragmentDirections.actionSelectCountryFragmentToHolidaysFragment())
        }

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            resources.getStringArray(R.array.countries)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.countrySpinner.adapter = adapter

        binding.continueButton.setOnClickListener {
            viewModel.saveCountryCode(
                binding.countrySpinner.selectedItem.toString().substring(0, 2), requireContext()
            )
            wasNavigatedToHolidaysScreen = true
            findNavController().navigate(SelectCountryFragmentDirections.actionSelectCountryFragmentToHolidaysFragment())
        }
    }
}