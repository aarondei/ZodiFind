package cit.edu.zodifind.fragments.other

import cit.edu.zodifind.wheel_of_fortune.WheelItem
import kotlinx.coroutines.flow.MutableStateFlow

object ItemsList {
    var flow = MutableStateFlow<List<WheelItem>>(listOf())
}