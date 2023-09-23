package hackeru.fridarik.wombwise.fragments

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import hackeru.fridarik.wombwise.R
import hackeru.fridarik.wombwise.adapters.WeeklyProgressionAdapter
import hackeru.fridarik.wombwise.databinding.FragmentWeeklyTrackBinding
import hackeru.fridarik.wombwise.viewModels.WeeklyTrackViewModel


class WeeklyTrackFragment : Fragment() {

    private var _binding: FragmentWeeklyTrackBinding? = null
    private lateinit var adapter: WeeklyProgressionAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var weeklyTrackViewModel: WeeklyTrackViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWeeklyTrackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weeklyTrackViewModel = ViewModelProvider(this) [WeeklyTrackViewModel::class.java]

        weeklyTrackViewModel.weeklyTrackLiveData.observe(viewLifecycleOwner) {details ->
            adapter = WeeklyProgressionAdapter(details) { clicked->


                findNavController().navigate(R.id.action_weeklyTrackFragment_to_weeklyDetailsFragment,
                    bundleOf(Pair<String,String>(EXTRA_WEEKLY_DETAILS, Gson().toJson(clicked))))

            }
            binding.weeklyDetailsRv.adapter = adapter
        }

        binding.weeklyDetailsRv.layoutManager = LinearLayoutManager(context)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}