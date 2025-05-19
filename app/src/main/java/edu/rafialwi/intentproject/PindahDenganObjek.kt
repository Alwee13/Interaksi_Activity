package edu.rafialwi.intentproject

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PindahDenganObjek : AppCompatActivity() {

    companion object{
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pindah_dengan_objek)
        val txtObjek: TextView = findViewById(R.id.txt_objek)

        val person = intent.getParcelableExtra<Person>(EXTRA_PERSON) as Person
        val text = "Nama : ${person.name.toString()}," +
                "\nEmail : ${person.email.toString()}," +
                "\nUmur : ${person.age.toString()}," +
                "\nKota : ${person.city.toString()}"
        txtObjek.text = text

    }
}