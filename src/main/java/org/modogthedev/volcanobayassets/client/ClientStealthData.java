package org.modogthedev.volcanobayassets.client;

public class ClientStealthData {
    private static int playerStealth;

    public static void set(int thirst) {
        ClientStealthData.playerStealth = thirst;
    }

    public static int getPlayerStealth() {
        return playerStealth;
    }
}
