package com.example.organizationApp.models

import com.example.organizationApp.enums.Course
import java.util.*


abstract class AbstractTask(val name: String, val course: Course, val date: Date, var notes: String) {

}
