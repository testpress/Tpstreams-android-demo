package com.syed.tpstreamsandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syed.tpstreamsandroid.ui.theme.TPStreamsAndroidTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.content.Intent
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.TabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TPStreamsAndroidTheme(darkTheme = true, dynamicColor = false) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Black
                ) { innerPadding ->
                    PlayerInputScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PlayerInputScreen(modifier: Modifier = Modifier) {
    var selectedTabIndex by remember { mutableStateOf(0) } // 0: Try your video, 1: Try demo
    var orgCode by remember { mutableStateOf("") }
    var assetId by remember { mutableStateOf("") }
    var accessToken by remember { mutableStateOf("") }
    val context = LocalContext.current

    val demoOrgCode = "9q94nm"
    val demoAssetId = "7xbZeQzR36h"
    val demoAccessToken = "3d9838f3-db51-4fc3-8472-075ab5e40b64"

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tps_logo),
            contentDescription = "TPS Logo",
            modifier = Modifier
                .height(110.dp)
                .padding(bottom = 16.dp)
        )
        Text(
            text = "TPStreams",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Custom Toggle Bar
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = Color(0xFF6200EE),
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Color(0xFF6200EE)
                )
            },
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
                text = { Text("Try your video", color = if (selectedTabIndex == 0) Color(0xFF6200EE) else Color.Gray) }
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
                text = { Text("Try demo", color = if (selectedTabIndex == 1) Color(0xFF6200EE) else Color.Gray) }
            )
        }
        
        val textFieldColors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = Color(0xFF6200EE),
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color(0xFF6200EE),
            unfocusedLabelColor = Color.Gray,
            cursorColor = Color(0xFF6200EE)
        )

        if (selectedTabIndex == 0) {
            OutlinedTextField(
                value = orgCode,
                onValueChange = { orgCode = it },
                label = { Text("Org Code") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = assetId,
                onValueChange = { assetId = it },
                label = { Text("Asset ID") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = accessToken,
                onValueChange = { accessToken = it },
                label = { Text("Access Token") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
        } else {
            // Demo Mode Info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("Demo Video Details:", color = Color.LightGray, style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Org: $demoOrgCode", color = Color.White)
                Text("Asset ID: $demoAssetId", color = Color.White)
                Text("Token: ${demoAccessToken.take(10)}...", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                val finalOrg = if (selectedTabIndex == 0) orgCode else demoOrgCode
                val finalAssetId = if (selectedTabIndex == 0) assetId else demoAssetId
                val finalAccessToken = if (selectedTabIndex == 0) accessToken else demoAccessToken

                if (finalOrg.isNotEmpty() && finalAssetId.isNotEmpty() && finalAccessToken.isNotEmpty()) {
                    val intent = Intent(context, PlayerActivity::class.java).apply {
                        putExtra("asset_id", finalAssetId)
                        putExtra("access_token", finalAccessToken)
                        putExtra("org_code", finalOrg)
                    }
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6200EE),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
        ) {
            Text(
                if (selectedTabIndex == 0) "Play" else "Play Demo",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}