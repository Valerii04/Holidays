package com.example.holidays.presentation.holidays

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.holidays.data.model.Holidays
import com.example.holidays.databinding.FragmentHolidaysBinding
import com.example.holidays.presentation.model.ApiResult
import java.util.Calendar

class HolidaysFragment : Fragment() {

    private var _binding: FragmentHolidaysBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HolidaysViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHolidaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.holidaysRecyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            layoutManager.orientation
        )
        binding.holidaysRecyclerView.addItemDecoration(dividerItemDecoration)

        val holidays = Holidays()
        val adapter = HolidaysAdapter(holidays)
        binding.holidaysRecyclerView.adapter = adapter

        val selectedDate = binding.calendarView.date

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selectedDate

        val year = calendar.get(Calendar.YEAR).toString()
        val month = (calendar.get(Calendar.MONTH) + 1).toString()
        val day = calendar.get(Calendar.DAY_OF_MONTH).toString()

        viewModel.getHolidays(requireContext(), year, month, day)

        binding.calendarView.setOnDateChangeListener { _, newYear, newMonth, newDay ->
            viewModel.getHolidays(
                requireContext(),
                newYear.toString(),
                newMonth.toString(),
                newDay.toString()
            )
        }

        binding.titleTextView.text = viewModel.getCountryCode(requireContext())

        viewModel.holidaysLiveData.observe(viewLifecycleOwner) {
            if (it is ApiResult.Error) {
                binding.errorTextView.text = it.message
                binding.holidaysRecyclerView.visibility = View.GONE
                binding.errorTextView.visibility = View.VISIBLE
            } else if (it is ApiResult.Success) {
                binding.holidaysRecyclerView.visibility = View.VISIBLE
                holidays.clear()
                holidays.addAll(it.holidays)
                if (it.holidays.isNotEmpty()) {
                    binding.errorTextView.visibility = View.GONE
                } else {
                    binding.errorTextView.text = "No holidays found!"
                    binding.errorTextView.visibility = View.VISIBLE
                }
                adapter.notifyDataSetChanged()
            }

        }

        binding.changeCountryButton.setOnClickListener {
            findNavController().navigate(HolidaysFragmentDirections.actionHolidaysFragmentToSelectCountryFragment())
        }
    }
}