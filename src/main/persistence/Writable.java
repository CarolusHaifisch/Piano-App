package persistence;

import org.json.JSONObject;

/** Code derived from writable interface code given in the JSON serialization demo code by the CPSC 210 teaching team
 *
 */

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
