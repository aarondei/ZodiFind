package cit.edu.zodifind.fragments

import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import cit.edu.zodifind.R
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalDate
import java.time.LocalDateTime

class DatePicker : Fragment() {

    companion object {
        fun newInstance() = DatePicker()
    }

    private val viewModel: DatePickerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_date_picker, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val composeView = view.findViewById<ComposeView>(R.id.composeDatePicker)
        composeView.setContent {
            WheelDatePicker(

                textColor = Color.White,

                maxDate = LocalDate.now(),

                selectorProperties = WheelPickerDefaults.selectorProperties(
                    enabled = true,
                    shape = RoundedCornerShape(75.dp),
                    color = Color.White.copy(alpha = 0.2f),
                    border = BorderStroke(2.dp, Color.White)
                ),

                onSnappedDate = { date ->
                    viewModel.selectedDate.value = date
                }
            )
        }

        viewModel.selectedDate.observe(viewLifecycleOwner) {
            // Update UI with the selected date
        }
    }
}