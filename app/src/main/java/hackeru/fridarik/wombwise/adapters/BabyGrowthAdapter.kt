package hackeru.fridarik.wombwise.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import hackeru.fridarik.wombwise.databinding.SizeCompareItemBinding
import hackeru.fridarik.wombwise.models.BabyGrowthDetails

class BabyGrowthAdapter(val babyGrowthDetails: List <BabyGrowthDetails>): Adapter<BabyGrowthAdapter.BabyGrowthVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BabyGrowthVH {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = SizeCompareItemBinding.inflate(inflater, parent, false)
        return BabyGrowthVH(binding)
    }

    override fun getItemCount(): Int = babyGrowthDetails.size

    override fun onBindViewHolder(holder: BabyGrowthVH, position: Int) {
        val babySize = babyGrowthDetails[position]

        holder.binding.textWeeklyNum.text = "In week number ${babySize.weekNum}:"
    }

    class BabyGrowthVH(val binding: SizeCompareItemBinding)
        :ViewHolder(binding.root)
}