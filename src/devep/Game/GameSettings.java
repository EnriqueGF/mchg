package devep.Game;

public class GameSettings {
    private Integer requiredPlayersToStart;
    private Integer worldBorderRadius = 3000;
    public GameStatusEnum gameStatus = GameStatusEnum.NOT_STARTED;

    public GameSettings(Integer requiredPlayersToStart) {
        this.requiredPlayersToStart = requiredPlayersToStart;
    }

    public Integer getRequiredPlayersToStart() {
        return requiredPlayersToStart;
    }

    public Integer getWorldBorderRadius() {
        return worldBorderRadius;
    }

    public void setWorldBorderRadius(Integer worldBorderRadius) {
        this.worldBorderRadius = worldBorderRadius;
    }
}
