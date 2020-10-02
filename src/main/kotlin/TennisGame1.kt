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
        if (scoreOne == scoreTwo) {
            return EqualsStateEngine(scoreOne).printScore()
        } else if (scoreOne >= 4 || scoreTwo >= 4) {
            return AdvantageWinStateEngine(scoreOne, scoreTwo).printScore()

        } else {
            var score = ""
            var tempScore = 0

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
            return score
        }
    }


}
class AdvantageWinStateEngine(private val scoreOne: Int,private val  scoreTwo: Int): GameStateEngine {
    override fun printScore(): String {
        val minusResult = scoreOne - scoreTwo
        return when {
            minusResult == 1 -> "Advantage player1"
            minusResult == -1 -> "Advantage player2"
            minusResult >= 2 -> "Win for player1"
            else -> "Win for player2"
        }
    }
}

class EqualsStateEngine(private val scoreOne: Int): GameStateEngine {
    override fun printScore(): String {
        return when (scoreOne) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }
    }
}

interface GameStateEngine {
    fun printScore(): String
}



object GameState {
    fun createEngine(scoreOne: Int, scoreTwo: Int) : String {
        if (scoreOne == scoreTwo) {
        }
    }
}
