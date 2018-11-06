package org.encryptic.encryptic

import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.kibotu.pgp.Pgp

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        btn_main_decrypt.setOnClickListener {
            showNote()
        }

        btn_main_save_key.setOnClickListener{
            val sharedPref = this.getSharedPreferences( "main_settings", Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putString("key", edt_main_input.text.toString())
                apply()
            }
        }

        btn_main_save_pass.setOnClickListener{
            val sharedPref = this.getSharedPreferences( "main_settings", Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putString("pass", edt_main_input.text.toString())
                apply()
            }
        }

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun showNote(){
        val note = "{\"type\":\"notes\",\"id\":\"6f0d2520-e1db-11e8-b6b5-39efe33b14f7\",\"encryptedData\":\"-----BEGIN PGP MESSAGE-----\\r\\nVersion: OpenPGP.js v2.6.2\\r\\nComment: https://openpgpjs.org\\r\\n\\r\\nwcBMA/BJgz+CqNCMAQf/RKPMgKXlqx5tWphpV1ZBBdnUfcE5J4zMiCymWH5A\\nOkAa2V94HE8VBNfIRnmE5FqmyuEeJFHDDFxHC2GbAr9PCMShPDIm1DqKxBcp\\nwn++Z3soztu13nxIwDlAri5hrhbCfBNFM7DoBXO+yLSIaRkV7DqEri674AcF\\nQJeAkLZu0BJT0fpeig1TpaQbFxYXODWO/HrCrzEKRqYU2aPd6zv+dxICovo3\\nZ/6ry06ejp4CKMQRfEwRMiUBRb7FQ3GMJyiRS2ysj3BlKt8dW9NpbgCUwuup\\ncVnjaR/+8FuCiTcvZ0ZOAr4MyCHckgUwgO5cLW7T5gs43fEKULg8UdAzKHIr\\nh9LA0QHVR49DGi4ASWo5Zw7jJSEnoHC+DLPsN76ha/5Arc1bZD+tEMorSuvo\\nPO9Jq0m3oOqPbODgXGWBBdTznXXRdPp1Cg5i7r/YzcX7NCHXrtTNqvgXURGs\\nFxaerGJYEme1FGa6VLisT7ffxXJtPXmia22SnCcdgBw2gfnUL0pcYtCej5DG\\nyoU0lBnhj3NXhmuU56DMiJ8OYhf1i+ni5jh7VOxPZRkWApcsOqA2ZGeBf/2r\\nK1UWje6CW2Yxrz8nRs3jg5+KJuZdNy4GxNOPeWGs7zhnMc5GfsACflV+CLpI\\nGi+kCX4Z8Bjyo/uX3vvPPA4dkH+gANVJwjigm7c3TNaaClxtiLw4GXFooHjE\\nBeNSMtIdWvLOFVQaJXyV3HSsr2naoGcHxjAFv3g35IhSBG1UdKrAg+iFKGTK\\nRkPiZN735BRaGglNOBV8iTxhkW7xwUMQ4UGfWyXXku9vYja96osc1Di2khWw\\nd8zu7/L3pDicuGObINl1YNgrFDrVHKson9rjDVsC1Fw8kyIG3Q38RhR0VmqD\\n\\r\\n=i8cQ\\r\\n-----END PGP MESSAGE-----\\r\\n\",\"taskAll\":0,\"taskCompleted\":0,\"created\":1541519321077,\"updated\":1541519334476,\"notebookId\":\"0\",\"isFavorite\":0,\"trash\":0,\"files\":[],\"sharedWith\":[],\"sharedBy\":\"vabene1111\"}"

        val parser = Parser()
        val stringBuilder = StringBuilder(note)
        val json: JsonObject = parser.parse(stringBuilder) as JsonObject

        val sharedPref = this.getSharedPreferences( "main_settings", Context.MODE_PRIVATE)
        val key = sharedPref.getString("key", "ERROR")
        val pass = sharedPref.getString("pass", "ERROR")

        Pgp.setPrivateKey(key!!)
        Pgp.setPublicKey("-----BEGIN PGP PUBLIC KEY BLOCK-----\\r\\nVersion: OpenPGP.js v2.6.2\\r\\nComment: https://openpgpjs.org\\r\\n\\r\\nxsBNBFvhrYEBCADVpqHelZ1HF0St2z2OBUDyURmqwZ810VAPXoAU3XcTXOjc\\nPCZmhGVYgkPp3nVpDBBYIYiHrMxJRP3Du5H0sxonDHOoMXhO4YFLt1qSRIBu\\n8FQUX2Fblnub0Ql2EQ3q34c+uXvKB0luvcfmOnRNMWzhoc8cXGgsvBC278qx\\njELyrr7eT05zkmMwbWzSikkoJbPIR2apfhaJyICTn1TzRSrWcW32OsrPW3Zr\\nwPsL3kOVK17CKcj2qsvqLaBePnayJ0QFkxm9a2KE0C3xbNkX/PiKcYaHIn0D\\nuy5n0pnhkA/KvozPTiRvwgpqKfLFsJZV3mre/CSNX5w1ci62ZW7433cRABEB\\nAAHNDXZhYmVuZTExMTEgPD7CwHUEEAEIACkFAlvhrYcGCwkHCAMCCRDxaET4\\nKlHtxAQVCAoCAxYCAQIZAQIbAwIeAQAAN14H/3/V+fCUJ02Yt99zg68vS8E3\\njoR8SCgD8mtv3UIgwM0iJefKleYfYG9U+xG4ltGvXBsT597c8bsXTN5Fr4ZP\\nl44w/EPU+pxPruYz6l2H98e2CxvyUNVNrJQlz0UBAnOmBGMbELoo+XBrN1MZ\\nwIb0ZpTbEkarmVyYhQQiMNk1CiRZfL77laskSurYWL+/+AFv443gzE8XF7Pr\\njtUzmJZvCthXUNVK4R5x4XAPXR3+CtPDWt3AQ+1Xl4P8sR5GIICLAtcC9dmy\\nU3wyL50kNY5QF2FRjOzY5MI1owQjj3ZWYDxbl9BIxpzN4emwhPHBzjc3lbC8\\ngalupiGdxVNsPZULvnfOwE0EW+GtgwEIAM0RsmSHtyrankZYVBx7lkA3Q0ZY\\n3/Ly1P0gqrGR4B0tFUK0dy/7j93T0h6LiWLwV9ZufPVd6PV8ScUXyHIxz6G1\\nAaAk7BVWO9WMjmEc9ZlD27vMHCH+vK2XwgJ/zKI3D7wwM0MS2uXtnyHDD51Z\\ngvAHKvYjamp1xflNdXBTnSPAjRDCD6hNnS6EwEYA/bBTHma9h9qUUaqmfaRv\\nX6ukSbA0XGM4H1stlkHRZVD6AqXTihCaOjwenb/YGJlAkQ6f0VmXxAsoX9PF\\nr3IrRgLpnbyo0ePxIcMTC4Sh57+eq4SxAEmNRGHamRLs4DgSxI8tVNvjb30/\\niijfsPOMx1eFXtMAEQEAAcLAXwQYAQgAEwUCW+GthwkQ8WhE+CpR7cQCGwwA\\nAJUzB/98Q5NJaVndrHCpQi8QyD/X3uI/Vh6z29PEawQUgJK67YqM6y3U4++v\\nJxJR+wPTuXywJ3jMoVnQUYQpOJTaVUxzmKCi18F19YHjNxJKQuSxOD61ABMM\\naJSeAl71H/qx/G7nsTp8tHXX7ycot1jXO/rirtjkUUdTMERT3AF58WtfJBd0\\nwEA0P58SZv6CyOuUa0TjW+vidXphRoWPgclBjSEuxMMDcvBh8XSERrD67lWW\\nFDP63OsJzm7bqR6HaxaIPDmTc+jLG6MIYDg38U+zQ3cXy3zmAFjtMMMf2pJs\\nnbY1VEhONRi60Z700rFKU3mfCeOCnDz4rDmeHBCmbUe59Ic+\\r\\n=QvIl\\r\\n-----END PGP PUBLIC KEY BLOCK-----\\r\\n\\r\\n")

        val decrypted = Pgp.decrypt(json.string("encryptedData")!!,pass)

        tv_main_note.text = decrypted

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
