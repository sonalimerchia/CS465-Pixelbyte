package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button classButton = (Button) (findViewById(R.id.MainButton));
        try {
            FileOutputStream outputStream = openFileOutput("Class.cf", Context.MODE_PRIVATE);
            String str = "CS 465\n" +
                    "90.0%\n" +
                    "Help can be found on canvas.illinois.edu\n" +
                    "3\n" +
                    "\n" +
                    "Quizzes\n" +
                    "30%\n" +
                    "Scheme...\n" +
                    "3\n" +
                    "Quiz_1\t90\n" +
                    "Quiz_2\t98\n" +
                    "Quiz_3\t92\n" +
                    "\n" +
                    "Tests\n" +
                    "40%\n" +
                    "Scheme...\n" +
                    "2\n" +
                    "Test_1\t90\n" +
                    "Test_2\t98\n" +
                    "\n" +
                    "Projects\n" +
                    "30%\n" +
                    "Scheme...\n" +
                    "1\n" +
                    "Main_Project\t85";
            outputStream.write(str.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {}

        Intent intent = new Intent(this, ClassActivity.class);
        intent.putExtra("ClassName", "CS 465");
        classButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}