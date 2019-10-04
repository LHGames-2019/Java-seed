package io.polyhx.lhgames.service;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import io.polyhx.lhgames.App;
import io.polyhx.lhgames.Bot;
import io.polyhx.lhgames.data.*;

import java.util.ArrayList;
import java.util.List;

public class GameServerService {
    private static GameServerService INSTANCE;

    private HubConnection fHubConnection;
    private String fTeamID;
    private Bot fBot;

    private GameServerService() { }

    public static GameServerService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new GameServerService();
        }

        return INSTANCE;
    }

    public void start() {
        if(fHubConnection != null) {
            System.out.println("game server: service is already started");
            return;
        }

        fBot = new Bot();
        fHubConnection = HubConnectionBuilder.create(App.LHAPI_URL + "/teamshub").build();
        fHubConnection.on("RequestExecuteTurn", this::onRequestExecuteTurn,
                String[].class,
                Integer.class,
                Integer.class,
                Integer.class,
                Integer.class,
                Integer.class
        );
        fHubConnection.start().doOnComplete(this::onConnect);
        fHubConnection.send("Register", fTeamID);
    }

    public void setTeamID(String teamID) {
        fTeamID = teamID;
    }

    private void onConnect() {
        System.out.println("game server: connection opened and handshake received");
    }

    private void onRequestExecuteTurn(String[] map, int dimension, int max, int left, int last, int team) {
        Map currentMap;
        try {
            currentMap = Map.fromStrings(map);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        Team hostTeam = Team.fromInteger(team);
        Point hostPosition = currentMap.getHeadPosition(hostTeam);
        int hostTail = currentMap.getTailLength(hostTeam);
        int hostBody = currentMap.getBodySize(hostTeam);
        int hostMaxMovement = max;
        int hostMovementLeft = left;
        Direction hostLastMove = Direction.fromInteger(last);
        HostPlayer host = new HostPlayer(
            hostTeam,
            hostPosition,
            hostTail,
            hostBody,
            hostMaxMovement,
            hostMovementLeft,
            hostLastMove
        );

        List<Player> others = new ArrayList<>();
        for(Team otherTeam: hostTeam.getOtherTeams()) {
            Point otherPosition = currentMap.getHeadPosition(otherTeam);
            int otherTail = currentMap.getTailLength(otherTeam);
            int otherBody = currentMap.getBodySize(otherTeam);
            others.add(new Player(
                otherTeam,
                otherPosition,
                otherTail,
                otherBody
            ));
        }

        GameInfo info = new GameInfo(currentMap, host, (Player[]) others.toArray());

        Direction next = fBot.getNextDirection(info);
        System.out.println(String.format("game server: next move is '%s'", next.toString()));
        fHubConnection.send("ReturnExecuteTurn", next.getValue());
    }
}