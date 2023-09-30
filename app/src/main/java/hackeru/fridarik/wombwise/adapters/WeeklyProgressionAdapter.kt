package hackeru.fridarik.wombwise.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import hackeru.fridarik.wombwise.databinding.PregnancyWeekItemBinding
import hackeru.fridarik.wombwise.models.WeeklyPregnancyDetails

const val TAG = "WeeklyProgressionAdapter"

class WeeklyProgressionAdapter(val weeklyProgDetails: List<WeeklyPregnancyDetails>,
                               private val onClick:(WeeklyPregnancyDetails)->Unit
): Adapter<WeeklyProgressionAdapter.WeeklyProgressionVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyProgressionVH {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = PregnancyWeekItemBinding.inflate(inflater, parent, false)
        return WeeklyProgressionVH(binding)
    }

    override fun getItemCount(): Int = weeklyProgDetails.size

    override fun onBindViewHolder(holder: WeeklyProgressionVH, position: Int) {
        val pregnancyWeek = weeklyProgDetails[position]

        holder.binding.textWeekValue.text = "${pregnancyWeek.weekNum}"
        holder.binding.textMonthValue.text = "${pregnancyWeek.monthNum}"
        holder.binding.textTrimesterValue.text = "${pregnancyWeek.trimesterNum}"

        holder.binding.root.setOnClickListener {
            onClick.invoke(pregnancyWeek)
            Log.d(TAG, "Clicked")
        }
    }

    class WeeklyProgressionVH(val binding: PregnancyWeekItemBinding)
        :ViewHolder(binding.root)
}