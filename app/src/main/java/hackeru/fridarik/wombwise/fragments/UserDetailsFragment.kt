package hackeru.fridarik.wombwise.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import hackeru.fridarik.wombwise.databinding.FragmentUserDetailsBinding
import hackeru.fridarik.wombwise.entity.PregnantWoman
import hackeru.fridarik.wombwise.viewModels.UserDetailsViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun Long.toDateString(): String {
    val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
    return "${date.year}-${date.monthValue}-${date.dayOfMonth}"
}

class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null

    private val binding get() = _binding!!

    private var _viewModel : UserDetailsViewModel? = null
    private val viewModel get(): UserDetailsViewModel  = _viewModel!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewModel = ViewModelProvider(this) [UserDetailsViewModel::class.java]
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initDB(requireActivity().application)

        viewModel.pregnantWomanLiveData.observe(viewLifecycleOwner) { woman ->
            if(woman == null)return@observe
            binding.nameEt.setText(woman.name)
            binding.ageEt.setText(woman.age.toString())
            binding.genderEt.setText(woman.gender)
            val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(woman.lastPeriod), ZoneId.systemDefault())

            val monthName = date.month.name
            val dateString = "${date.dayOfMonth} ${monthName}, ${date.year}"
            binding.lastPeriodDatePicker.init(date.year, date.monthValue, date.dayOfMonth, null)
            binding.chosenLastPeriodDate.text = dateString
        }


        binding.updateDetailsBtn.setOnClickListener {

            val dateTime = LocalDateTime.of(
                binding.lastPeriodDatePicker.year,
                binding.lastPeriodDatePicker.month + 1,
                binding.lastPeriodDatePicker.dayOfMonth,
                1,
                1
            )
            val dateTimeInMillis =
                dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            val name = binding.nameEt.text.toString()
            val age = binding.ageEt.text.toString()
            val gender = binding.genderEt.text.toString()
            if(age.isEmpty()) {
                Snackbar.make(view, "Please enter your age", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (viewModel.pregnantWomanLiveData.value != null) { // update existing details
                val current = viewModel.pregnantWomanLiveData.value!!
                current.age = age.toInt()
                current.name = name
                current.gender = gender
                current.lastPeriod = dateTimeInMillis
                viewModel.updatePregnantWoman(current)
            } else { // new user details

                val woman = PregnantWoman(
                        name = name,
                        age = age.toInt(),
                        lastPeriod = dateTimeInMillis,
                        gender = gender)

                viewModel.insertPregnantWoman(woman)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}