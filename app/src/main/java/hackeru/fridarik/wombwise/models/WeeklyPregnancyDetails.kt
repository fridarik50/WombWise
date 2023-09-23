package hackeru.fridarik.wombwise.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeeklyPregnancyDetails(
    @SerializedName("week")
    val weekNum: Int,
    @SerializedName("month")
    val monthNum: Int,
    @SerializedName("trimester")
    val trimesterNum: Int,
    @SerializedName("baby_progression")
    val babyProgression: String,
    @SerializedName("mother_progression")
    val motherProgression: String,
    @SerializedName("week_num_img")
    val weekNumImg: String
)   : Parcelable

