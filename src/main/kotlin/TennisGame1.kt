class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private var scoreOne: Int = 0
    private var scoreTwo: Int = 0

    override fun wonPoint(playerName: String) {
        if (playerName === "player1")
            scoreOne += 1
        else
            scoreTwo += 1
    }

    override fun getScore(): String {
        var score = ""
        var tempScore = 0
        if (scoreOne == scoreTwo) {
            when (scoreOne) {
                0 -> score = "Love-All"
                1 -> score = "Fifteen-All"
                2 -> score = "Thirty-All"
                else -> score = "Deuce"
            }
        } else if (scoreOne >= 4 || scoreTwo >= 4) {
            val minusResult = scoreOne - scoreTwo
            if (minusResult == 1)
                score = "Advantage player1"
            else if (minusResult == -1)
                score = "Advantage player2"
            else if (minusResult >= 2)
                score = "Win for player1"
            else
                score = "Win for player2"
        } else {
            for (i in 1..2) {
                if (i == 1)
                    tempScore = scoreOne
                else {
                    score += "-"
                    tempScore = scoreTwo
                }
                when (tempScore) {
                    0 -> score += "Love"
                    1 -> score += "Fifteen"
                    2 -> score += "Thirty"
                    3 -> score += "Forty"
                }
            }
        }
        return score
    }
}
