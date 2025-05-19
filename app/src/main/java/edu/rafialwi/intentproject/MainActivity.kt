package edu.rafialwi.intentproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var txtResultValue : TextView

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == TambahActivity.RESULT_CODE && result.data != null) {
            val selectedValue =
                result.data?.getStringExtra(TambahActivity.EXTRA_SELECTED_VALUE)
            txtResultValue.text = "Jenis Kelamin Yang Anda Pilih Adalah: $selectedValue"
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnSubmit: Button = findViewById(R.id.btn_pindah)
        btnSubmit.setOnClickListener(this)

        val btnData: Button = findViewById(R.id.btn_pindah_dengandata)
        btnData.setOnClickListener(this)

        val btnObjek: Button = findViewById(R.id.btn_pindah_denganobjek)
        btnObjek.setOnClickListener(this)

        val btnDial: Button = findViewById(R.id.btn_dial)
        btnDial.setOnClickListener(this)

        val btnOpenAnotherApps: Button = findViewById(R.id.btn_open_anotherapps)
        btnOpenAnotherApps.setOnClickListener(this)

        val btnForResult: Button = findViewById(R.id.btn_for_result)
        btnForResult.setOnClickListener(this)

        txtResultValue = findViewById(R.id.txt_result)
    }

    override fun onClick(v: View){
        when(v.id) {
            R.id.btn_pindah -> {
                val pindahIntent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(pindahIntent)
            }

            R.id.btn_pindah_dengandata -> {
                val pindahDataIntent = Intent(this@MainActivity, PindahDenganData::class.java)
                pindahDataIntent.putExtra(PindahDenganData.EXTRA_NAME, "Rafi Alwi Saputra")
                pindahDataIntent.putExtra(PindahDenganData.EXTRA_AGE, 22)
                startActivity(pindahDataIntent)
            }

            R.id.btn_pindah_denganobjek -> {
                val person = Person(
                    "Rafi Alwi Saputra",
                    22,
                    "rafi.alwisaputra@gmail.com",
                    "Bandung"
                )

                val pindahDenganObjek = Intent(this@MainActivity, PindahDenganObjek::class.java)
                pindahDenganObjek.putExtra(PindahDenganObjek.EXTRA_PERSON, person)
                startActivity(pindahDenganObjek)
            }

            R.id.btn_dial -> {
                val phoneNumber = "08123456789"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_open_anotherapps -> {

                val openAnotherApps = Intent(Intent.ACTION_MAIN)
                openAnotherApps.setPackage("com.google.android.youtube")
                startActivity(openAnotherApps)
            }

            R.id.btn_for_result -> {
                val forResultIntent = Intent(this@MainActivity, TambahActivity::class.java)
                //startActivity(forResultIntent)
                resultLauncher.launch(forResultIntent)
            }

        }
    }
}