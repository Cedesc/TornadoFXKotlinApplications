package com.example.organizationApp.models

import com.example.organizationApp.enums.Course
import com.example.organizationApp.enums.Room
import java.util.*

class TaskLecture(name: String, course: Course, notes: String, date: Date, room: Room, prof: String)
    : AbstractTask(name, course, date, notes) {

}