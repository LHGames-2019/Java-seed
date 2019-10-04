package io.polyhx.lhgames.data;

public class GameInfo {
    private final Map fMap;
    private final HostPlayer fHostPlayer;
    private final Player[] fOtherPlayers;

    public GameInfo(Map map, HostPlayer host, Player[] others) {
        fMap = map;
        fHostPlayer = host;
        fOtherPlayers = others;
    }

    public Map getMap() {
        return fMap;
    }

    public HostPlayer getHostPlayer() {
        return fHostPlayer;
    }

    public Player[] getOtherPlayers() {
        return fOtherPlayers;
    }
}