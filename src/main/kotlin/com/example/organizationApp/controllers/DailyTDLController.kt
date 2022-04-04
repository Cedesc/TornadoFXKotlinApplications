package com.example.organizationApp.controllers

import com.example.organizationApp.enums.Course
import com.example.organizationApp.enums.Room
import com.example.organizationApp.models.*
import java.util.*

class DailyTDLController {
    var todayTDL: Array<AbstractTask> = arrayOf(
        TaskAssignment(
            "Assignment Task 1",
            Course.BIOINFORMATIK,
            "yeet the assignment",
            Date(2021, 11, 27, 12, 30)
        ),
        TaskLecture(
            "Accelerated Computing Vorlesung",
            Course.ACCELERATED_COMPUTING_WITH_GPU,
            "this is a lecture",
            Date(2021, 10, 19, 10, 0),
            Room.MUSCHEL,
            "An IT-Professor"
        ),
        TaskTutorial(
            "DSEA Tutorial",
            Course.DATENSTRUKTUREN_UND_EFFIZIENTE_ALGORITHMEN,
            "Meine Uebung sheeesh",
            Date(2021, 10, 21, 8, 0),
            Room.K04_422,
            "Christian zu dem Dohmen"
        ),
        TaskWorkWithOtherStudents(
            "Persoenlichkeitspsychologie lernen",
            Course.PERSOENLICHKEITSPSYCHOLOGIE,
            "als ob man da was machen muss",
            Date(2021, 11, 5, 16, 0)
        )
    )

}