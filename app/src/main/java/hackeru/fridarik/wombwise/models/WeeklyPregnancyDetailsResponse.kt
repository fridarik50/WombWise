package hackeru.fridarik.wombwise.models

import com.google.gson.annotations.SerializedName

data class WeeklyPregnancyDetailsResponse(
    @SerializedName("pregnancy_weeks")
    val weeklyDetails: List<WeeklyPregnancyDetails>
    )
