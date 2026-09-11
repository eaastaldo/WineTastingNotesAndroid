package com.sami.winetasting;

import android.content.Context;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

public class TastingStore {
    private static final String PREF="wine_tastings", KEY="items";
    public static List<Tasting> load(Context c){
        List<Tasting> out=new ArrayList<>();
        try{ JSONArray a=new JSONArray(c.getSharedPreferences(PREF,0).getString(KEY,"[]")); for(int i=0;i<a.length();i++) out.add(Tasting.fromJson(a.getJSONObject(i))); }catch(Exception ignored){}
        return out;
    }
    public static void save(Context c, Tasting tasting){
        List<Tasting> list=load(c); boolean replaced=false;
        for(int i=0;i<list.size();i++) if(list.get(i).id==tasting.id){ list.set(i,tasting); replaced=true; break; }
        if(!replaced) list.add(0,tasting);
        JSONArray a=new JSONArray(); try{ for(Tasting t:list)a.put(t.toJson()); c.getSharedPreferences(PREF,0).edit().putString(KEY,a.toString()).apply(); }catch(Exception ignored){}
    }
    public static Tasting find(Context c,long id){ for(Tasting t:load(c)) if(t.id==id)return t; return null; }
    public static void delete(Context c,long id){ List<Tasting> list=load(c); JSONArray a=new JSONArray(); try{ for(Tasting t:list) if(t.id!=id)a.put(t.toJson()); c.getSharedPreferences(PREF,0).edit().putString(KEY,a.toString()).apply(); }catch(Exception ignored){} }
}
