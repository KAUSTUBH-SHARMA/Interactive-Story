package com.example.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.interactivestory.R;
import com.example.interactivestory.model.Page;
import com.example.interactivestory.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {
    private Story story;
    private String name;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private Stack<Integer> pageStack =new Stack<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        storyImageView=findViewById(R.id.storyImageView);
        storyTextView=findViewById(R.id.storyTextView);
        choice1Button=findViewById(R.id.choice1Button);
        choice2Button=findViewById(R.id.choice2Button);

        
        Intent intent=getIntent();
         name=intent.getStringExtra("name");
        if(name.isEmpty() || name == null){
            name="Friend";
        }

        story=new Story();
        loadPage(0);



    }

    private void loadPage(int pageNumber) {
        pageStack.push(pageNumber);

        final Page page =story.getPage(pageNumber);
        Drawable image= ContextCompat.getDrawable(this, page.getImageId());

        storyImageView.setImageDrawable(image);
        String pageText=getString(page.getTextId());
        pageText=String.format(pageText,name);
        storyTextView.setText(pageText);
        if(page.isFinalPage()){
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText("Play Again");
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   finish();  //will take me to new page

                }
            });

        }
        else{
            choice1Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage=page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });
            choice2Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextpage=page.getChoice2().getNextPage();
                loadPage(nextpage);
            }
        });
    }}

    @Override
    public void onBackPressed() {
        pageStack.pop();
        if(pageStack.isEmpty()){
        super.onBackPressed();}
        else{
            loadPage(pageStack.pop());
        }
    }
}
