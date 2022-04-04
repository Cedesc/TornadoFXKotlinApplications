package com.example.organizationApp.models

import com.example.organizationApp.enums.Course
import java.util.*

class TaskWorkWithOtherStudents(name: String, course: Course, notes: String, date: Date)
    : AbstractTask(name, course, date, notes) {

}