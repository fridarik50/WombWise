package hackeru.fridarik.wombwise.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import hackeru.fridarik.wombwise.R
import hackeru.fridarik.wombwise.databinding.FragmentWeeklyDetailsBinding
import hackeru.fridarik.wombwise.models.WeeklyPregnancyDetails
import java.lang.Exception

const val EXTRA_WEEKLY_DETAILS = "weeklyDetails"

class WeeklyDetailsFragment : Fragment() {

    private var _binding: FragmentWeeklyDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeeklyDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val weeklyDetails = Gson().fromJson( requireArguments().getString(EXTRA_WEEKLY_DETAILS),WeeklyPregnancyDetails::class.java)
        binding.textBabyProg.text = "Baby Progression: ${weeklyDetails.babyProgression}"
        binding.textMotherProg.text = "Mother Progression: ${weeklyDetails.motherProgression}"


         Glide.with(this).load(weeklyDetails.weekNumImg).into(binding.imgWeekOfNum)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}