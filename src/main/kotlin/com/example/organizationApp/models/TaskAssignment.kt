package com.example.organizationApp.models

import com.example.organizationApp.enums.Course
import java.util.*

class TaskAssignment(name: String, course: Course, notes: String, submissionDate: Date)
    : AbstractTask(name, course, submissionDate, notes) {

}