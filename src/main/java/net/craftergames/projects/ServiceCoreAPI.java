package net.craftergames.projects;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import net.craftergames.projects.colors.manager.HexCodeManager;
import net.craftergames.projects.databases.PacketMongoDatabase;
import net.craftergames.projects.databases.ProjectMongoDatabase;
import net.craftergames.projects.databases.ProxyMongoDatabase;
import net.craftergames.projects.handlers.PacketHandler;
import net.craftergames.projects.modules.ProjectUserClientModule;
import net.craftergames.projects.modules.ProxyUserClientModule;
import net.craftergames.projects.wrapper.ProxyUserWrapper;

@Singleton
public class ServiceCoreAPI {

    private static Injector injector;
    private static PacketHandler packetHandler;
    private static ServiceCoreAPI serviceCoreAPI;
    private static ProxyMongoDatabase proxyMongoDatabase;
    private static PacketMongoDatabase packetMongoDatabase;
    private static ProjectMongoDatabase projectMongoDatabase;
    private final String prefix = "§7 §8» " + HexCodeManager.createGradientFromString("CrafterGames", new String[]{"#D70606", "#EE0D0D"}) + " §8┃ §7";
    private final String prefixAlert = "§8« " + HexCodeManager.createGradientFromString("CrafterGames", new String[]{"#D70606", "#EE0D0D"}) + " §8»";
    private final String databaseError = prefix + "§cAufgrund eines Fehlers konnten die Daten nicht geladen oder geupdatet werden. Bitte melde dich sofort bei einem Teammitglied!";
    private final String noPerm = prefix + "§cDieser Befehl existiert nicht, oder du hast keine Rechte diesen auszuführen!";

    public ServiceCoreAPI() {
        serviceCoreAPI = this;
        injector = Guice.createInjector(new ProxyUserClientModule(), new ProjectUserClientModule());
        connectDatabase();
        registerHandlers();
    }

    private static void connectDatabase() {
        projectMongoDatabase = new ProjectMongoDatabase("localhost", 27017);
        proxyMongoDatabase = new ProxyMongoDatabase("localhost", 27017);
        packetMongoDatabase = new PacketMongoDatabase("localhost", 27017);
        projectMongoDatabase.connect();
        proxyMongoDatabase.connect();
        packetMongoDatabase.connect();
    }

    private static void registerHandlers() {packetHandler = new PacketHandler();}
    public static ServiceCoreAPI getServiceCoreAPI() {injector = Guice.createInjector(new ProxyUserClientModule(), new ProjectUserClientModule());connectDatabase();registerHandlers();return serviceCoreAPI;}
    public PacketHandler getPacketHandler() {
        return packetHandler;
    }
    public String getNoPerm() {
        return noPerm;
    }
    public static Injector getInjector() {injector = Guice.createInjector(new ProxyUserClientModule(), new ProjectUserClientModule());return injector;}
    public String getPermPrefix() {return "craftergames.";}
    public String getDatabaseError() {
        return databaseError;
    }
    public String getPrefixAlert() {
        return prefixAlert;
    }
    public ProjectMongoDatabase getProjectMongoDataBase() {return projectMongoDatabase;}
    public ProxyMongoDatabase getProxyMongoDataBase() {return proxyMongoDatabase;}
    public PacketMongoDatabase getPacketMongoDatabase() {return packetMongoDatabase;}

}
