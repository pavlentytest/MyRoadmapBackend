package ru.boringowl.myroadmap.infrastructure.jpa

import ru.boringowl.myroadmap.domain.Hackathon
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name="hackathons_table")
class JpaHackathon() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hack_id")
    var hackId: UUID? = null

    @Column(name = "hack_title")
    var hackTitle: String = ""

    @Column(columnDefinition="TEXT", name = "hack_description")
    var hackDescription: String = ""


    @Column(name = "publish_date")
    var publishDate: LocalDateTime = LocalDateTime.now()

    @Column(columnDefinition="TEXT")
    var source: String = ""
    @Column(columnDefinition="TEXT")
    var date: String? = ""
    @Column(columnDefinition="TEXT")
    var registration: String? = ""
    @Column(columnDefinition="TEXT")
    var focus: String? = ""
    @Column(columnDefinition="TEXT")
    var prize: String? = ""
    @Column(columnDefinition="TEXT")
    var routes: String? = ""
    @Column(columnDefinition="TEXT")
    var terms: String? = ""
    @Column(columnDefinition="TEXT")
    var organization: String? = ""
    @Column(length=500)
    var imageUrl: String? = ""
    
    constructor(hackathon: Hackathon) : this() {
        hackId = hackathon.hackId
        hackTitle = hackathon.hackTitle
        hackDescription = hackathon.hackDescription
        publishDate = hackathon.publishDate
        source = hackathon.source
        date = hackathon.date
        registration = hackathon.registration
        focus = hackathon.focus
        prize = hackathon.prize
        routes = hackathon.routes
        terms = hackathon.terms
        organization = hackathon.organization
        imageUrl = hackathon.imageUrl
    }

    fun toHackathon() = Hackathon().also {
        it.hackId = hackId
        it.hackTitle = hackTitle
        it.hackDescription = hackDescription
        it.publishDate = publishDate
        it.source = source
        it.date = date
        it.registration = registration
        it.focus = focus
        it.prize = prize
        it.routes = routes
        it.terms = terms
        it.organization = organization
        it.imageUrl = imageUrl
    }
}
