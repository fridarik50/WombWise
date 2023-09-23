package hackeru.fridarik.wombwise.models

import com.google.gson.annotations.SerializedName

data class BabyGrowthDetailsResponse(
    @SerializedName("pregnancy_weeks")
    val babyGrowthDetails :List<BabyGrowthDetails>
)
