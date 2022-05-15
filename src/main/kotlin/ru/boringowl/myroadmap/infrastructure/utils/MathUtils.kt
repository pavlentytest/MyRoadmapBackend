package ru.boringowl.myroadmap.infrastructure.utils

import kotlin.math.max
import kotlin.math.min

object MathUtils {

    private fun getLevenshteinDistance(X: String, Y: String): Int {
        val m = X.length
        val n = Y.length
        val T = Array(m + 1) { IntArray(n + 1) }
        for (i in 1..m) {
            T[i][0] = i
        }
        for (j in 1..n) {
            T[0][j] = j
        }
        var cost: Int
        for (i in 1..m) {
            for (j in 1..n) {
                cost = if (X[i - 1] == Y[j - 1]) 0 else 1
                T[i][j] = min(
                    min(T[i - 1][j] + 1, T[i][j - 1] + 1),
                    T[i - 1][j - 1] + cost)
            }
        }
        return T[m][n]
    }

    fun findSimilarity(x: String, y: String): Double {
        val maxLength = max(x.length, y.length)
        return if (maxLength > 0) {
            (maxLength * 1.0 - getLevenshteinDistance(x, y)) / maxLength * 1.0
        } else 1.0
    }


}


fun HashMap<String, Int>.getMostSimilar(key: String): String? {
    var maxSimilar = 0.0
    var maxSimilarKey: String? = null
    this.forEach { k, v ->
        val sim = MathUtils.findSimilarity(k, key)
        if (maxSimilar < sim) {
            maxSimilar = sim
            maxSimilarKey = k
        }
    }
    return if (maxSimilar > 0.7 && !exclusionWords.contains(key))
        maxSimilarKey
    else
        null
}

val exclusionWords = arrayListOf(
    "android", "ios", "swift",
)
