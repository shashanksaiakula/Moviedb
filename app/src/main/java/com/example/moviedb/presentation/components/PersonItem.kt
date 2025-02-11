package com.example.moviedb.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.moviedb.data.remote.model.Person

@Composable
fun PersonItem(person: Person) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(0.5.dp, color = MaterialTheme.colorScheme.primary , shape = RoundedCornerShape(5.dp))
            .shadow(0.5.dp, shape = RoundedCornerShape(3.dp))
            .clickable {

            }
    ) {
        Image(
            painter = rememberImagePainter("https://image.tmdb.org/t/p/w500${person.profile_path}"),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(100.dp)
                .padding(6.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(text = "Name: ${person.name}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            person.known_for_department?.let { Text(text = "Profession: $it", fontWeight = FontWeight.Bold, fontSize = 16.sp) }
        }
    }
}