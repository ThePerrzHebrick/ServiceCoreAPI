package net.craftergames.projects.clients;

public class ProjectUserClient {

    private String project;
    private String playerName;
    private String playerLowedName;
    private String playerUniqueID;

    public ProjectUserClient(String project, String playerName, String playerUniqueID) {
        this.project = project;
        this.playerName = playerName;
        this.playerLowedName = playerName.toLowerCase();
        this.playerUniqueID = playerUniqueID;
    }

    public static class ProjectUserClientBuilder {
        private String playerName;
        private String playerLowedName;
        private String playerUniqueID;
        private String project;

        public ProjectUserClientBuilder(String project, String playerUniqueId, String playerName) {
            this.project = project;
            this.playerName = playerName;
            this.playerLowedName = playerName.toLowerCase();
            this.playerUniqueID = playerUniqueId;
        }

        public ProjectUserClientBuilder(ProjectUserClient projectUserClient) {
            this.project = projectUserClient.getProject();
            this.playerUniqueID = projectUserClient.getPlayerUniqueID();
            this.playerName = projectUserClient.getPlayerName();
            this.playerLowedName = projectUserClient.getPlayerLowedName().toLowerCase();
        }

        public String getPlayerUniqueID() {
            return playerUniqueID;
        }

        public String getPlayerLowedName() {
            return playerLowedName.toLowerCase();
        }

        public String getPlayerName() {
            return playerName;
        }

        public String getProject() {
            return project;
        }

        public ProjectUserClient generate() {
            return new ProjectUserClient(getProject(), getPlayerName(), getPlayerUniqueID());
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPlayerLowedName() {
        return playerLowedName.toLowerCase();
    }

    public void setPlayerLowedName(String playerLowedName) {
        this.playerLowedName = playerLowedName.toLowerCase();;
    }
}
