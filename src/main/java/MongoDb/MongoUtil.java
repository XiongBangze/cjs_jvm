package MongoDb;

import com.mongodb.*;

import java.util.Arrays;

public class MongoUtil {


    public static DB noneMongdb() throws Exception {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("test");
        return db;
    }


    public static DB mongoCredentialType() throws Exception{

        MongoCredential credential = MongoCredential.createCredential("", "test", "".toCharArray());
        ServerAddress serverAddress = new ServerAddress("localhost", 27017);
        MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
        DB db = mongoClient.getDB("test");
        return db;
    }


    public DB mongoClientURIType() throws Exception{
        String sURI = String.format("mongodb://%s:%s@%s:%d/%s", "", "", "localhost", 27017, "test");
        MongoClientURI uri = new MongoClientURI(sURI);
        MongoClient mongoClient = new MongoClient(uri);
        DB db = mongoClient.getDB("mydb");
        return db;
    }

    public static void insertMongdb(DB db) throws Exception {
        DBCollection coll = db.getCollection("test");
        BasicDBObject doc = new BasicDBObject("name", "mongo").append("age","11").append("ahe", new BasicDBObject("ver", "3.0"));
        coll.insert(doc);
    }

    public static void main(String[] args) {
        try {
            DB db = noneMongdb();
            DBCollection coll = db.getCollection("test");
            DBCursor cursor = coll.find();
            try {
                while(cursor.hasNext()) {
                    System.out.println(cursor.next());
                }
            } finally {
                cursor.close();
            }

            BasicDBObject query = new BasicDBObject("name", "mongo");
            DBCursor cursor1 = coll.find(query);
            try {
                while(cursor1.hasNext()) {
                    System.out.println(cursor1.next());
                }
            } finally {
                cursor1.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
