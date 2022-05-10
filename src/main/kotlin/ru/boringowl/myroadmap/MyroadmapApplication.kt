package ru.boringowl.myroadmap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyroadmapApplication

fun main(args: Array<String>) {
	runApplication<MyroadmapApplication>(*args)
}
