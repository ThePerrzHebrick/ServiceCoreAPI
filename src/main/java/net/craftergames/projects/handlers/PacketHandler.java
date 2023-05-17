package net.craftergames.projects.handlers;

import com.mongodb.client.MongoCollection;
import net.craftergames.projects.ServiceCoreAPI;
import org.bson.Document;

public class PacketHandler {

    private MongoCollection<Document> getPacketHandler() {
        return ServiceCoreAPI.getServiceCoreAPI().getPacketMongoDatabase().getPacketHandler().getCollection("packets");
    }

    public void create(String packet, Document document) {
        if (!this.isExist(packet, document)) {
            final Document document2 = new Document("packet", packet).append("packets", document);
            this.getPacketHandler().insertOne(document2);
        }
    }

    public void deleteOne(String packet, Document document) {
        if (this.isExist(packet, document)) {
            final Document document2 = new Document("packet", packet).append("packets", document);
            this.getPacketHandler().deleteOne(document2);
        }
    }

    public void deleteMany(String packet, Document document) {
        if (this.isExist(packet, document)) {
            final Document document2 = new Document("packet", packet).append("packets", document);
            this.getPacketHandler().deleteMany(document2);
        }
    }

    public Document get(String packet) {
        final Document document2 = new Document("packet", packet);
        return (Document) this.getPacketHandler().find(document2).first().get("packets");
    }

    public Boolean isExist(String packet, Document document) {
        final Document document2 = new Document("packet", packet).append("packets", document);
        if (this.getPacketHandler().find(document2).first() != null) {
            return true;
        } else {
            return false;
        }
    }

}
