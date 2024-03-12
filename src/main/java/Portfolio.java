/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 */

/*
 * ================================================================================================
 * Copyright (c) 2010-2014 Pivotal Software, Inc. All Rights Reserved. This product is protected by
 * U.S. and international copyright and intellectual property laws. Pivotal products are covered by
 * one or more patents listed at http://www.pivotal.io/patents.
 * ================================================================================================
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Portfolio implements ConfigurableObject, Serializable {
    public String myVersion;
    public int ID;
    // public Position position1;
    // public Position position2;
    // public HashMap positions = new HashMap();
    public String type;
    public String status;
    public byte[] payload;

    /*
     * public String getStatus(){ return status;
     */
    public int getID() {
        return ID;
    }

    /**
     * For testing.
     * When used within a query, this method causes the query to sleep for the given length of time before
     * returning true.
     *   Example query that invokes this method:
     *     select * from /testRegion p where p.sleepForMs(10000)
     * WARNING: This method is called on each value in the region as the query executes so the user must pass in
     *          the correct length of time to sleep PER VALUE. For example, a replicate region of size 5 will call
     *          this method 5 times for a total of 50 seconds.
     * To overcome the warning, limiting the query to 1 return value causes this method to be executed just
     * once. For example this causes the whole query to take 10 seconds because sleepForMs is called only once:
     *    select * from /testRegion p where p.sleepForMs(10000) limit 1
     *
     * @param msToSleep The number of milliseconds to sleep per region value.
     * @return Always returns true
     */
    public boolean sleepForMs(int msToSleep) {
        try{
            Thread.sleep(msToSleep);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Create a map of fields and field values to use to write to the blackboard since PdxSerialiables
     * cannot be put on the blackboard since the MasterController does not have pdx objects on its
     * classpath. For PdxSerializables we put this Map on the blackboard instead.
     */
    public Map createPdxHelperMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("myVersion", myVersion);
        fieldMap.put("className", this.getClass().getName());
        fieldMap.put("ID", ID);
        fieldMap.put("type", type);
        fieldMap.put("status", status);
//        Log.getLogWriter().info("created map in tests/Portfolio: " + fieldMap);
        return fieldMap;
    }

    /**
     * Restore the fields of this instance using the values of the Map, created by
     * createPdxHelperMap()
     */
    public void restoreFromPdxHelperMap(Map aMap) {
       // Log.getLogWriter().info("restoring from map into " + this.getClass().getName() + ": " + aMap);
        this.myVersion = (String) aMap.get("myVersion");
        this.ID = (Integer) aMap.get("ID");
        this.type = (String) aMap.get("type");
        this.status = (String) aMap.get("status");
//        Log.getLogWriter().info("returning instance from map in tests/Portfolio: " + this);
    }

    // public HashMap getPositions() {
    // return positions;
    // }
    //
    // public HashMap getPositions(String str) {
    // return positions;
    // }
    //
    // public HashMap getPositions(Integer i) {
    // return positions;
    // }
    //
    // public HashMap getPositions(int i) {
    // return positions;
    // }
    //
    // public Position getP1() {
    // return position1;
    // }
    //
    // public Position getP2() {
    // return position2;
    // }

    public boolean testMethod(boolean booleanArg) {
        return true;
    }

    public boolean isActive() {
        return status.equals("active");
    }

    // public static String secIds[] = { "SUN", "IBM", "YHOO", "GOOG", "MSFT",
    // "AOL", "APPL", "ORCL", "SAP", "DELL", "RHAT", "NOVL", "HP"};

    public Portfolio() {
        myVersion = "tests/objects.Portfolio";
        ID = 0;
        status = "";
        type = "";
        // position1 = position2 = null;

    }

    public Portfolio(int i) {
        ID = i;
        status = i % 2 == 0 ? "active" : "inactive";
        type = "type" + (i % 3);
        // position1 = new Position(secIds[Position.cnt % secIds.length],
        // Position.cnt * 1000);
        // if (i % 2 != 0) {
        // position2 = new Position(secIds[Position.cnt % secIds.length],
        // Position.cnt * 1000);
        // }
        // else {
        // position2 = null;
        // }
        // positions.put(secIds[Position.cnt % secIds.length], new Position(
        // secIds[Position.cnt % secIds.length], Position.cnt * 1000));
        // positions.put(secIds[Position.cnt % secIds.length], new Position(
        // secIds[Position.cnt % secIds.length], Position.cnt * 1000));
    }

    public String toString() {
        String out = "Portfolio [ID=" + ID + " status=" + status + " type=" + type;
        // Iterator iter = positions.entrySet().iterator();
        // while (iter.hasNext()) {
        // Map.Entry entry = (Map.Entry) iter.next();
        // out += entry.getKey() + ":" + entry.getValue() + ", ";
        // }
        // out += "\n P1:" + position1 + ", P2:" + position2;
        return out + "]";
    }

    /**
     * Getter for property type.
     *
     * @return Value of property type.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns a new instance of the object encoded with the index.
     *
     * @throws ObjectCreationException An error occurred when creating the object. See the error
     *         message for more details.
     */
    public void init(int i) {
        this.ID = i;
        status = i % 2 == 0 ? "active" : "inactive";
        type = "type" + (i % 3);
        // position1 = new Position(secIds[Position.cnt % secIds.length], Position.cnt * 1000);
        // if (i % 2 != 0) {
        // position2 = new Position(secIds[Position.cnt % secIds.length], Position.cnt * 1000);
        // }
        // else {
        // position2 = null;
        // }
        // positions.put(secIds[Position.cnt % secIds.length], new Position(
        // secIds[Position.cnt % secIds.length], Position.cnt * 1000));
        // positions.put(secIds[Position.cnt % secIds.length], new Position(
        // secIds[Position.cnt % secIds.length], Position.cnt * 1000));

    }

    /**
     * Returns the index encoded in the object.
     *
     * @throws ObjectAccessException An error occurred when accessing the object. See the error
     *         message for more details.
     */
    public int getIndex() {
        return this.ID;
    }

    /**
     * Validates whether the index is encoded in the object, if this applies, and performs other
     * validation checks as needed.
     *
     * @throws ObjectAccessException An error occurred when accessing the object. See the error
     *         message for more details.
     * @throws ObjectValidationException The object failed validation. See the error message for more
     *         details.
     */
    public void validate(int index) {
        // do nothing
    }

    @Override
    public int hashCode() {
        return ID;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Portfolio)) {
            return false;
        }
        Portfolio o = (Portfolio) other;
        if (this.ID == o.ID) {
            return true;
        }
        return false;
    }

}