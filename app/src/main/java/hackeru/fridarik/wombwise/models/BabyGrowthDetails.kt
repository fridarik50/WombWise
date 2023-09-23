package hackeru.fridarik.wombwise.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BabyGrowthDetails(
    @SerializedName("week")
    val weekNum: Int,
    @SerializedName("month")
    val monthNum: Int,
    @SerializedName("trimester")
    val trimesterNum: Int,
    @SerializedName("size_comparison")
    val sizeComparison: String,
    @SerializedName("baby_weight")
    val babyWeight: String,
    @SerializedName("baby_length")
    val babyLength: String,
) : Parcelable
