package edu.temple.funwithintents

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // This view contains the text to share
        val editText = findViewById<EditText>(R.id.editTextText)

        // When the user clicks this button, share the text if not empty
        findViewById<ImageButton>(R.id.shareImageButton).setOnClickListener {
            //checks if the text is not empty
            if (editText.text.isNotEmpty()) {
                //create an implicit intent to share the text
                //action send means to share something
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    //the type / mime type of the data being shared is text
                    type = "text/plain" //mime type for text
                    //the extra text is the text to share
                    putExtra(Intent.EXTRA_TEXT, editText.text.toString())
                }
                //this if statement checks if there is an activity that can handle the share intent
                if (shareIntent.resolveActivity(packageManager) != null) {
                    //start the activity that can handle the share intent
                    //share via is the title of the activity
                    //chooser is a built in activity that allows the user to choose which app to use to share the text
                    startActivity(Intent.createChooser(shareIntent, "Share via"))
                }
            }
        }
    }
}