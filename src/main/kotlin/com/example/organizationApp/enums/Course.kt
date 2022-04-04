package com.example.organizationApp.enums

enum class Course(kuerzel: String) {
    ACCELERATED_COMPUTING_WITH_GPU("ACG") {override fun toString(): String = "Accelerated Computing with GPU"},
    BIOINFORMATIK("EIB") {override fun toString(): String = "Einfuehrung in die Bioinformatik"},
    BIOINFORMATIK_SEMINAR("EIB-Seminar") {override fun toString(): String = "Bioinformatik Seminar"},
    PERSOENLICHKEITSPSYCHOLOGIE("PP") {override fun toString(): String = "Persoenlichkeitspsychologie"},
    DATENSTRUKTUREN_UND_EFFIZIENTE_ALGORITHMEN("DSeA") {override fun toString(): String =
        "Datenstrukturen und effiziente Algorithmen"},
    PLACEHOLDER("") {override fun toString(): String = "Placeholder"},
    ARBEITS_UND_ORGANISATIONSPSYCHOLOGIE("AOP") {override fun toString(): String =
        "Arbeits- und Organisationspsychologie"}
}
