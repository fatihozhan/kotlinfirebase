package com.example.firebaseveritabani

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebaseveritabani.ui.theme.FireBaseVeriTabaniTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FireBaseVeriTabaniTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Sayfa()
                }
            }
        }
    }
}

@Composable
fun Sayfa() {
//        ekle()
//    tumKisiler()
//    sil()
//    guncelle()
//    esitlikArama()
//degerAraligi()
limit()
}

fun ekle() {
    val db =
        FirebaseDatabase.getInstance("https://start-e1f22-default-rtdb.europe-west1.firebasedatabase.app")
    val refKisiler = db.getReference("kisiler")

    val yeniKisi = Kisiler("Talat", 15)
    refKisiler.push().setValue(yeniKisi)

}

fun tumKisiler() {
    val db =
        FirebaseDatabase.getInstance("https://start-e1f22-default-rtdb.europe-west1.firebasedatabase.app")
    val refKisiler = db.getReference("kisiler")

    refKisiler.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for (d in snapshot.children) {
                val kisi = d.getValue(Kisiler::class.java)
                Log.e("Kisi", kisi!!.kisi_ad.toString())
                Log.e("Kisi", kisi!!.kisi_yas.toString())
            }
        }

        override fun onCancelled(error: DatabaseError) {}
    })

}

fun sil() {
    val db =
        FirebaseDatabase.getInstance("https://start-e1f22-default-rtdb.europe-west1.firebasedatabase.app")
    val refKisiler = db.getReference("kisiler")

    refKisiler.child("-NS5ZTFbyJYwc-ygAp4S").removeValue()

}

fun guncelle() {
    val db =
        FirebaseDatabase.getInstance("https://start-e1f22-default-rtdb.europe-west1.firebasedatabase.app")
    val refKisiler = db.getReference("kisiler")
    val bilgiler = HashMap<String, Any>()
    bilgiler["kisi_ad"] = "Yeni Talat"
    bilgiler["kisi_yas"] = 45
    refKisiler.child("-NS5ZWy_iCBdavD5T5RO").updateChildren(bilgiler)

}

fun esitlikArama() {
    val db =
        FirebaseDatabase.getInstance("https://start-e1f22-default-rtdb.europe-west1.firebasedatabase.app")
    val refKisiler = db.getReference("kisiler")
    val sorgu = refKisiler.orderByChild("kisi_ad").equalTo("Fatih")

    sorgu.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for (d in snapshot.children) {
                val kisi = d.getValue(Kisiler::class.java)
                Log.e("Kisi", kisi!!.kisi_ad.toString())
                Log.e("Kisi", kisi!!.kisi_yas.toString())
            }
        }

        override fun onCancelled(error: DatabaseError) {}
    })
}

fun degerAraligi() {
    val db =
        FirebaseDatabase.getInstance("https://start-e1f22-default-rtdb.europe-west1.firebasedatabase.app")
    val refKisiler = db.getReference("kisiler")
    val sorgu = refKisiler.orderByChild("kisi_yas").startAt(15.0).endAt(40.0)

    sorgu.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for (d in snapshot.children) {
                val kisi = d.getValue(Kisiler::class.java)
                Log.e("Kisi", kisi!!.kisi_ad.toString())
                Log.e("Kisi", d.key.toString())
                Log.e("Kisi", kisi!!.kisi_yas.toString())
            }
        }

        override fun onCancelled(error: DatabaseError) {}
    })
}

fun limit() {
    val db =
        FirebaseDatabase.getInstance("https://start-e1f22-default-rtdb.europe-west1.firebasedatabase.app")
    val refKisiler = db.getReference("kisiler")
    val sorgu = refKisiler.limitToFirst(2)

    sorgu.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for (d in snapshot.children) {
                val kisi = d.getValue(Kisiler::class.java)
                Log.e("Kisi", kisi!!.kisi_ad.toString())
                Log.e("Kisi", d.key.toString())
                Log.e("Kisi", kisi!!.kisi_yas.toString())
            }
        }

        override fun onCancelled(error: DatabaseError) {}
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FireBaseVeriTabaniTheme {
        Sayfa()
    }
}