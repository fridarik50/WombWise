package hackeru.fridarik.wombwise.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import hackeru.fridarik.wombwise.R
import hackeru.fridarik.wombwise.adapters.BabyGrowthAdapter
import hackeru.fridarik.wombwise.databinding.FragmentFruitComparisonBinding
import hackeru.fridarik.wombwise.databinding.FragmentWeeklyTrackBinding
import hackeru.fridarik.wombwise.viewModels.BabyGrowthViewModel
import hackeru.fridarik.wombwise.viewModels.WeeklyTrackViewModel


class FruitComparisonFragment : Fragment() {

    private var _binding: FragmentFruitComparisonBinding? = null
    private lateinit var adapter: BabyGrowthAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var babyGrowthViewModel: BabyGrowthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFruitComparisonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        babyGrowthViewModel = ViewModelProvider(this) [BabyGrowthViewModel::class.java]

        babyGrowthViewModel.babyGrowthLiveData.observe(viewLifecycleOwner) {details ->
            adapter = BabyGrowthAdapter(details)
            binding.babySizeDetailsRv.adapter = adapter
        }

        binding.babySizeDetailsRv.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}