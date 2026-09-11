package com.sami.winetasting;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Tasting {
    public long id = System.currentTimeMillis();
    public String wine="", producer="", vintage="", grape="", region="", date="", temperature="", notes="";
    public String clarity="", appearanceIntensity="", color="", appearanceNotes="";
    public String noseCondition="", noseIntensity="", mainAromas="", noseNotes="";
    public List<String> aromas = new ArrayList<>();
    public String sweetness="", acidity="", tannin="", alcohol="", body="", flavourIntensity="", finish="";
    public String primaryFlavours="", secondaryFlavours="", tertiaryFlavours="";
    public String quality="", why="", overall="", score="", buyAgain="";

    public JSONObject toJson() throws Exception {
        JSONObject o = new JSONObject();
        o.put("id", id); o.put("wine", wine); o.put("producer", producer); o.put("vintage", vintage); o.put("grape", grape);
        o.put("region", region); o.put("date", date); o.put("temperature", temperature); o.put("notes", notes);
        o.put("clarity", clarity); o.put("appearanceIntensity", appearanceIntensity); o.put("color", color); o.put("appearanceNotes", appearanceNotes);
        o.put("noseCondition", noseCondition); o.put("noseIntensity", noseIntensity); o.put("mainAromas", mainAromas); o.put("noseNotes", noseNotes);
        JSONArray a = new JSONArray(); for(String s: aromas) a.put(s); o.put("aromas", a);
        o.put("sweetness", sweetness); o.put("acidity", acidity); o.put("tannin", tannin); o.put("alcohol", alcohol); o.put("body", body); o.put("flavourIntensity", flavourIntensity); o.put("finish", finish);
        o.put("primaryFlavours", primaryFlavours); o.put("secondaryFlavours", secondaryFlavours); o.put("tertiaryFlavours", tertiaryFlavours);
        o.put("quality", quality); o.put("why", why); o.put("overall", overall); o.put("score", score); o.put("buyAgain", buyAgain);
        return o;
    }

    public static Tasting fromJson(JSONObject o) throws Exception {
        Tasting t = new Tasting();
        t.id=o.optLong("id",System.currentTimeMillis()); t.wine=o.optString("wine"); t.producer=o.optString("producer"); t.vintage=o.optString("vintage"); t.grape=o.optString("grape");
        t.region=o.optString("region"); t.date=o.optString("date"); t.temperature=o.optString("temperature"); t.notes=o.optString("notes");
        t.clarity=o.optString("clarity"); t.appearanceIntensity=o.optString("appearanceIntensity"); t.color=o.optString("color"); t.appearanceNotes=o.optString("appearanceNotes");
        t.noseCondition=o.optString("noseCondition"); t.noseIntensity=o.optString("noseIntensity"); t.mainAromas=o.optString("mainAromas"); t.noseNotes=o.optString("noseNotes");
        JSONArray a=o.optJSONArray("aromas"); if(a!=null) for(int i=0;i<a.length();i++) t.aromas.add(a.optString(i));
        t.sweetness=o.optString("sweetness"); t.acidity=o.optString("acidity"); t.tannin=o.optString("tannin"); t.alcohol=o.optString("alcohol"); t.body=o.optString("body"); t.flavourIntensity=o.optString("flavourIntensity"); t.finish=o.optString("finish");
        t.primaryFlavours=o.optString("primaryFlavours"); t.secondaryFlavours=o.optString("secondaryFlavours"); t.tertiaryFlavours=o.optString("tertiaryFlavours");
        t.quality=o.optString("quality"); t.why=o.optString("why"); t.overall=o.optString("overall"); t.score=o.optString("score"); t.buyAgain=o.optString("buyAgain");
        return t;
    }
}
