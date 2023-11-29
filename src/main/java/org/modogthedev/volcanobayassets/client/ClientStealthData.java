package org.modogthedev.volcanobayassets.client;

public class ClientStealthData {
    private static int playerStealth;

    public static void set(int stealth) {
        ClientStealthData.playerStealth = stealth;
    }

    public static int getPlayerStealth() {
        return playerStealth;
    }
}
