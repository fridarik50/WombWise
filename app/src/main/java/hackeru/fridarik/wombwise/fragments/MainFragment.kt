package hackeru.fridarik.wombwise.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hackeru.fridarik.wombwise.R
import hackeru.fridarik.wombwise.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnWeeklyTrack.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_weeklyTrackFragment)
        }

        binding.btnSizeComparison.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fruitComparisonFragment)
        }

        binding.btnCalcDueDate.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_dueDateCalcFragment)
        }
        binding.btnPersonalData.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_userDetailsFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}