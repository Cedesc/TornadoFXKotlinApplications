package com.example.organizationApp.models

import com.example.organizationApp.enums.Course
import com.example.organizationApp.enums.Room
import java.util.*

class TaskTutorial(name: String, course: Course, notes: String, date: Date, room: Room, tutor: String)
    : AbstractTask(name, course, date, notes) {

}