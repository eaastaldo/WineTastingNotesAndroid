package com.sami.winetasting;

import android.app.*;
import android.content.*;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.util.List;

public class MainActivity extends Activity {
    LinearLayout list;
    int wine = Color.rgb(122,23,50), ink=Color.rgb(31,37,48), muted=Color.rgb(110,110,110);
    int dp(float x){ return (int)(x*getResources().getDisplayMetrics().density+0.5f); }
    TextView tv(String s,int sp,boolean bold){ TextView v=new TextView(this); v.setText(s); v.setTextSize(sp); v.setTextColor(ink); if(bold)v.setTypeface(null,1); return v; }
    @Override public void onCreate(Bundle b){ super.onCreate(b); build(); }
    @Override protected void onResume(){ super.onResume(); if(list!=null) refresh(); }
    void build(){
        ScrollView sc=new ScrollView(this); sc.setBackgroundColor(Color.rgb(255,253,249));
        LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(18),dp(22),dp(18),dp(32)); sc.addView(root);
        TextView title=tv("WINE TASTING NOTES",30,true); title.setTextColor(wine); root.addView(title);
        TextView sub=tv("Systematic tasting, made practical.",15,false); sub.setTextColor(muted); root.addView(sub,new LinearLayout.LayoutParams(-1,-2));
        Button add=new Button(this); add.setText("+  NEW TASTING"); add.setTextColor(Color.WHITE); add.setTextSize(16); add.setTypeface(null,1); add.setBackgroundResource(com.sami.winetasting.R.drawable.btn_wine); add.setAllCaps(false);
        LinearLayout.LayoutParams bp=new LinearLayout.LayoutParams(-1,dp(58)); bp.setMargins(0,dp(20),0,dp(22)); root.addView(add,bp); add.setOnClickListener(v->startActivity(new Intent(this,TastingActivity.class)));
        TextView saved=tv("MY TASTINGS",18,true); root.addView(saved);
        list=new LinearLayout(this); list.setOrientation(LinearLayout.VERTICAL); root.addView(list);
        setContentView(sc); refresh();
    }
    void refresh(){
        list.removeAllViews(); List<Tasting> items=TastingStore.load(this);
        if(items.isEmpty()){ TextView e=tv("No tastings yet. Start with your next glass.",15,false); e.setTextColor(muted); e.setPadding(0,dp(18),0,0); list.addView(e); return; }
        for(Tasting t:items){
            LinearLayout card=new LinearLayout(this); card.setOrientation(LinearLayout.VERTICAL); card.setPadding(dp(16),dp(14),dp(16),dp(14)); card.setBackgroundResource(R.drawable.rounded_card);
            TextView n=tv(t.wine.isEmpty()?"Untitled wine":t.wine,19,true); card.addView(n);
            String meta=(t.vintage.isEmpty()?"":t.vintage+"  •  ")+(t.grape.isEmpty()?"":t.grape+"  •  ")+(t.region.isEmpty()?"":t.region);
            TextView m=tv(meta,13,false); m.setTextColor(muted); card.addView(m);
            String score=t.score.isEmpty()?"Not scored":t.score+" / 100"; TextView s=tv(score+(t.quality.isEmpty()?"":"   "+t.quality),14,true); s.setTextColor(wine); s.setPadding(0,dp(8),0,0); card.addView(s);
            LinearLayout.LayoutParams cp=new LinearLayout.LayoutParams(-1,-2); cp.setMargins(0,dp(10),0,0); list.addView(card,cp);
            card.setOnClickListener(v->{ Intent i=new Intent(this,TastingActivity.class); i.putExtra("id",t.id); startActivity(i); });
            card.setOnLongClickListener(v->{ new AlertDialog.Builder(this).setTitle("Delete tasting?").setMessage(t.wine).setPositiveButton("Delete",(d,w)->{TastingStore.delete(this,t.id);refresh();}).setNegativeButton("Cancel",null).show(); return true; });
        }
    }
}
