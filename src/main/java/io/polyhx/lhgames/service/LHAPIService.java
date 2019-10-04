package io.polyhx.lhgames.service;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import io.polyhx.lhgames.App;

public class LHAPIService {
    private static LHAPIService INSTANCE;

    private HubConnection fHubConnection;

    private LHAPIService() { }

    public static LHAPIService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new LHAPIService();
        }

        return INSTANCE;
    }

    public void start() {
        if(fHubConnection != null) {
            System.out.println("lhapi: service is already started");
            return;
        }

        fHubConnection = HubConnectionBuilder.create(App.LHAPI_URL + "/teamshub").build();
        fHubConnection.on("AssignTeamId", (teamID) -> {
           GameServerService.getInstance().setTeamID(teamID);
        }, String.class);
        fHubConnection.on("AssignGameServerUriToGameId", (gameserverUri) -> {
            App.GAME_SERVER_URL = gameserverUri;
            GameServerService.getInstance().start();
        }, String.class);
        fHubConnection.start().doOnComplete(() -> {
            System.out.println("lhapi: connection opened and handshake received");
        });
    }
}
