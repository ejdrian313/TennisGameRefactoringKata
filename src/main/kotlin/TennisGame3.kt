class TennisGame3(private val p1N: String, private val p2N: String) : TennisGame {

    private var playerTwoPoints = PlayerPoints()
    private var playerOnePoints = PlayerPoints()

    override fun getScore(): String {
        val game = Tennis(playerOnePoints, playerTwoPoints)
        if (game.isNormalNamingScore()) {
            val normalScoreNaming = NormalScoreNaming()
            normalScoreNaming.setScoreOne(playerOnePoints.points)
            return if (game.isPointsEqual())
                normalScoreNaming.equalScore()
            else
                normalScoreNaming.notEqualScore(playerTwoPoints)
        } else {
            val advantageScoreNaming = AdvantageScoreNaming()
            if (game.isPointsEqual())
                return advantageScoreNaming.deuce
            if (game.isPlayerOneAdvantage())
                advantageScoreNaming.setAdvantageOf(p1N)
            else
                advantageScoreNaming.setAdvantageOf(p2N)
            return if (game.canStillPlaying())
                advantageScoreNaming.getScoreAdv()
            else
                advantageScoreNaming.getWinner()
        }
    }

    override fun wonPoint(playerName: String) {
        if (playerName === "player1")
            this.playerOnePoints.addPoint()
        else
            this.playerTwoPoints.addPoint()

    }
}

class AdvantageScoreNaming {
    private var score = ""
    fun setAdvantageOf(playerName: String) {
        score = playerName
    }

    fun getScoreAdv() = "Advantage $score"
    fun getWinner() = "Win for $score"

    val deuce = "Deuce"
}

class NormalScoreNaming {
    private val scores = arrayOf("Love", "Fifteen", "Thirty", "Forty")
    private var scoreOne = ""

    fun equalScore(): String = "$scoreOne-All"

    private fun getScore(points: Int): String {
        return scores[points]
    }

    fun notEqualScore(playerTwoPoints: PlayerPoints): String {
        val playerTwoScore = getScore(playerTwoPoints.points)
        return "$scoreOne-$playerTwoScore"
    }

    fun setScoreOne(points: Int) {
        scoreOne = getScore(points)
    }
}

class Tennis(private val playerOnePoints: PlayerPoints, private val playerTwoPoints: PlayerPoints) {
    fun isPointsEqual(): Boolean {
        return playerOnePoints.points == playerTwoPoints.points
    }

    fun isNormalNamingScore(): Boolean {
        return playerOnePoints.points < 4 && playerTwoPoints.points < 4 && playerOnePoints.points + playerTwoPoints.points != 6
    }

    fun isPlayerOneAdvantage() = playerOnePoints.points > playerTwoPoints.points
    fun canStillPlaying() = (playerOnePoints.points - playerTwoPoints.points) * (playerOnePoints.points - playerTwoPoints.points) == 1

}

class PlayerPoints {
    var points = 0
        private set

    fun addPoint() {
        points++
    }
}