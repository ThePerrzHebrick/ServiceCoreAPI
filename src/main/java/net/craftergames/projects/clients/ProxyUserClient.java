package net.craftergames.projects.clients;

import net.craftergames.projects.wrapper.ProjectUserWrapper;

import java.util.ArrayList;

public class ProxyUserClient {

    private String playerName;
    private String playerLowedName;
    private String playerUniqueID;

    public ProxyUserClient(String playerName, String playerUniqueID) {
        this.playerName = playerName;
        this.playerLowedName = playerName.toLowerCase();
        this.playerUniqueID = playerUniqueID;
    }

    public static class ProxyUserClientBuilder {
        private String playerName;
        private String playerLowedName;
        private String playerUniqueID;

        public ProxyUserClientBuilder(String playerUniqueId, String playerName) {
            this.playerName = playerName;
            this.playerLowedName = playerName.toLowerCase();
            this.playerUniqueID = playerUniqueId;
        }

        public ProxyUserClientBuilder(ProxyUserClient proxyUserClient) {
            this.playerUniqueID = proxyUserClient.getPlayerUniqueID();
            this.playerName = proxyUserClient.getPlayerName();
            this.playerLowedName =proxyUserClient.getPlayerLowedName().toLowerCase();;
        }

        public String getPlayerUniqueID() {
            return playerUniqueID;
        }

        public String getPlayerLowedName() {
            return playerLowedName;
        }

        public String getPlayerName() {
            return playerName;
        }

        public ProxyUserClient generate() {
            return new ProxyUserClient(getPlayerName(), getPlayerUniqueID());
        }
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerUniqueID() {
        return playerUniqueID;
    }

    public void setPlayerUniqueID(String playerUniqueID) {
        this.playerUniqueID = playerUniqueID;
    }

    public String getPlayerLowedName() {
        return playerLowedName.toLowerCase();
    }

    public void setPlayerLowedName(String playerLowedName) {
        this.playerLowedName = playerLowedName.toLowerCase();
    }
}