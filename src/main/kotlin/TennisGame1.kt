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
        return GameState
                .createEngine(scoreOne, scoreTwo)
                .printScore()
    }
}

class PointsStateEngine(private val scoreOne: Int, private val scoreTwo: Int) : GameStateEngine {
    override fun printScore(): String {
        return calculateTempScore(scoreOne)
                .plus("-")
                .plus(calculateTempScore(scoreTwo))
    }

    private fun calculateTempScore(tempScore: Int): String {
        var string = ""
        when (tempScore) {
            0 -> string += "Love"
            1 -> string += "Fifteen"
            2 -> string += "Thirty"
            3 -> string += "Forty"
        }
        return string
    }
}

class AdvantageWinStateEngine(private val scoreOne: Int, private val scoreTwo: Int) : GameStateEngine {
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

class EqualsStateEngine(private val scoreOne: Int) : GameStateEngine {
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
    fun createEngine(scoreOne: Int, scoreTwo: Int): GameStateEngine {
        return if (scoreOne == scoreTwo) {
            EqualsStateEngine(scoreOne)
        } else if (scoreOne >= 4 || scoreTwo >= 4) {
            AdvantageWinStateEngine(scoreOne, scoreTwo)
        } else {
            PointsStateEngine(scoreOne, scoreTwo)
        }
    }
}
