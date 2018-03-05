package com.example.aravind_pt1748.recyclerviewapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    List<Game> gamesList = new ArrayList<>();
    RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new RecyclerAdapterActivity(gamesList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        prepareData();
    }

    public void prepareData(){

        Game game = new Game("Uncharted 4: A Thief's End","Action-adventure","(2016)",R.drawable.uncharted4);
        gamesList.add(game);

        game = new Game("Fallout 4","Action role-playing","(2015)",R.drawable.fallout4);
        gamesList.add(game);

        game = new Game("Destiny","Action role-playing","(2014)",R.drawable.destiny);
        gamesList.add(game);

        game = new Game("The Last Of Us","Action-adventure","(2013)",R.drawable.thelastofus);
        gamesList.add(game);

        game = new Game("Dishonored","Action-adventure","(2012)",R.drawable.dishonored);
        gamesList.add(game);

        game = new Game("Portal 2","Puzzle-platformer","(2011)",R.drawable.portal2);
        gamesList.add(game);

        game = new Game("Mass Effect 2","Action role-playing","(2010)",R.drawable.masseffect2);
        gamesList.add(game);

        game = new Game("Batman: Arkham Asylum","Action-adventure","(2009)",R.drawable.batman);
        gamesList.add(game);

        game = new Game("BioShock","First-person shooter","(2007)",R.drawable.bioshock);
        gamesList.add(game);

        game = new Game("Tom Clancy's Ghost Recon Advanced Warfighter","Tactical Shooter","(2006)",R.drawable.ghostrecon);
        gamesList.add(game);

        game = new Game("Half-Life 2","First-person shooter","(2004)",R.drawable.halflife2);
        gamesList.add(game);

        game = new Game("Call of Duty","First-person shooter","(2003)",R.drawable.callofduty);
        gamesList.add(game);

        game = new Game("NeverWinter Nights","Role-playing","(2002)",R.drawable.neverwinternights);
        gamesList.add(game);

        game = new Game("Halo: Combat Evolved","First-person shooter","(2002)",R.drawable.halo);
        gamesList.add(game);

        game = new Game("Max Payne","First-person shooter","(2001)",R.drawable.maxpayne);
        gamesList.add(game);

        game = new Game("Deus Ex","Action role-playing","(2000)",R.drawable.deusex);
        gamesList.add(game);

        mAdapter.notifyDataSetChanged();
    }
}
