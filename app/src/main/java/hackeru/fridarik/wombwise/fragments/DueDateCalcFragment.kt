package hackeru.fridarik.wombwise.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import hackeru.fridarik.wombwise.databinding.FragmentDueDateCalcBinding
import hackeru.fridarik.wombwise.viewModels.DueDateCalcViewModel


class DueDateCalcFragment : Fragment() {

    private var _binding: FragmentDueDateCalcBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var dueDateCalcViewModel: DueDateCalcViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDueDateCalcBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dueDateCalcViewModel = ViewModelProvider(this) [DueDateCalcViewModel::class.java]

        dueDateCalcViewModel.dueDate.observe(viewLifecycleOwner) {
            binding.titleDueDate.text = it
        }

        dueDateCalcViewModel.gestationalAge.observe(viewLifecycleOwner) {
            binding.titleGestationalAge.text = it
        }

        binding.btnCalculate.setOnClickListener {
            val lastMenstrualPeriodString = binding.edtLastMenstrualPeriod.text.toString()
            val (dueDate, gestationalAge) =
                dueDateCalcViewModel.calculateDueDateAndGestationalAge(lastMenstrualPeriodString)

            binding.titleDueDate.text = "Due date: ${dueDate}"
            binding.titleGestationalAge.text = "Gestational age: ${gestationalAge}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}