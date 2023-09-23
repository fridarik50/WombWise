package hackeru.fridarik.wombwise.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hackeru.fridarik.wombwise.databinding.FragmentDueDateCalcBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DueDateCalcViewModel : ViewModel() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    private val _dueDate = MutableLiveData<String>()
    val dueDate: LiveData<String> = _dueDate

    private val _gestationalAge = MutableLiveData<String>()
    val gestationalAge: LiveData<String> = _gestationalAge

    fun calculateDueDateAndGestationalAge(lastMenstrualPeriodString: String): Pair<String, String> {
        try {
            val lastMenstrualPeriod = dateFormat.parse(lastMenstrualPeriodString)
            val calendar = Calendar.getInstance()
            calendar.time = lastMenstrualPeriod

            // Step 1: Count back 3 calendar months
            calendar.add(Calendar.MONTH, -3)
            val gestationalAgeDate = calendar.time

            // Step 2: Add 1 year and 7 days to calculate the due date
            calendar.add(Calendar.YEAR, 1)
            calendar.add(Calendar.DAY_OF_YEAR, 7)
            val dueDate = calendar.time
            val dueDateFormatted = dateFormat.format(dueDate)

            // Calculate gestational age using the provided formula
            val gestationalAge = calculateGestationalAge(lastMenstrualPeriodString)

            return Pair(dueDateFormatted, gestationalAge)
        } catch (e: Exception) {
            return Pair("Invalid Date", "Invalid Date")
        }
    }

    private fun calculateGestationalAge(lastMenstrualPeriodString: String): String {
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd") // Adjust the date format as needed
            val lastMenstrualPeriod = dateFormat.parse(lastMenstrualPeriodString)
            val currentDate = Calendar.getInstance().time

            val daysDifference = ((currentDate.time - lastMenstrualPeriod.time) / (1000 * 60 * 60 * 24)).toInt()
            val weeks = daysDifference / 7
            val days = daysDifference % 7

            return "$weeks weeks $days days"
        } catch (e: Exception) {
            return "Invalid Date"
        }
    }
}