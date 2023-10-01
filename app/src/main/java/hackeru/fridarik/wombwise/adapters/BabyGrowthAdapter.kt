package hackeru.fridarik.wombwise.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
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

        holder.binding.weekNumValueSizeItem.text = "${babySize.weekNum}"
        holder.binding.monthNumValueSizeItem.text = "${babySize.weekNum}"
        holder.binding.trimesterNumValueSizeItem.text = "${babySize.weekNum}"
        holder.binding.textBabyWeightValue.text = "${babySize.babyWeight}"
        holder.binding.textBabyLengthValue.text = "${babySize.babyLength}"
        holder.binding.textBabySizeDesc.text = "${babySize.sizeComparison}"

        Glide.with(holder.itemView.context).load(babySize.fruitImg).into(holder.binding.weeklyFruitImg)
    }

    class BabyGrowthVH(val binding: SizeCompareItemBinding)
        :ViewHolder(binding.root)
}