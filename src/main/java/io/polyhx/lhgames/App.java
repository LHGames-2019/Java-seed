package io.polyhx.lhgames;

import io.polyhx.lhgames.service.GameServerService;
import io.polyhx.lhgames.service.LHAPIService;

public class App {
    public static String LHAPI_URL;
    public static String GAME_SERVER_URL;

    public static void main(String[] args) {
        if(System.getenv("LHAPI_URL") != null) {
            LHAPI_URL = System.getenv("LHAPI_URL");
        }

        if(System.getenv("GAME_SERVER_URL") != null) {
            GAME_SERVER_URL = System.getenv("GAME_SERVER_URL");
        }

        boolean isOnline = (LHAPI_URL != null) && (LHAPI_URL != "");
        if(isOnline) {
            System.out.println("Running the bot in online mode...");

            LHAPIService.getInstance().start();
        } else {
            System.out.println("Running the bot in offline mode...");
            if(GAME_SERVER_URL == null || GAME_SERVER_URL == "") {
                System.out.println("Error: GAME_SERVER_URL variable isn't defined");
                System.exit(1);
            }

            GameServerService.getInstance().start();
        }

        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
