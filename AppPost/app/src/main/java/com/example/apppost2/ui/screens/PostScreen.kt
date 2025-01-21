package com.example.apppost2.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apppost2.data.models.Post
//import com.example.apppost2.ui.theme.Blue40
//import com.example.apppost2.ui.theme.Blue80
import com.example.apppost2.viewModel.PostViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun PostScreen(viewModel: PostViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var editingPost by remember { mutableStateOf<Post?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.fetchPosts()
        isLoading = false
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Gerenciamento de Posts",
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1565C0)
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(text = "Título") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text(text = "Conteúdo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                isLoading = true
                viewModel.createPost(title, content, onSuccess = {
                    Toast.makeText(context, "Post criado com sucesso!", Toast.LENGTH_SHORT).show()
                    isLoading = false
                }, onError = {
                    Toast.makeText(context, "Erro ao criar o post!", Toast.LENGTH_SHORT).show()
                    isLoading = false
                })
                title = ""
                content = ""
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF42A5F5))
        ) {
            Text(text = "Criar Post", color = Color.White)
        }
        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn {
                items(viewModel.posts) { post ->
                    PostItem(
                        post = post,
                        onDelete = { viewModel.deletePost(it) },
                        onEdit = { editingPost = it }
                    )
                }
            }
        }
    }

    if (editingPost != null) {
        AlertDialog(
            onDismissRequest = { editingPost = null },
            title = { Text(text = "Editar Post", color = Color(0xFF0D47A1)) },
            text = {
                Column {
                    TextField(
                        value = editingPost!!.title,
                        onValueChange = { newTitle -> editingPost = editingPost!!.copy(title = newTitle) },
                        label = { Text(text = "Título") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = editingPost!!.content,
                        onValueChange = { newContent -> editingPost = editingPost!!.copy(content = newContent) },
                        label = { Text(text = "Conteúdo") }
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.updatePost(editingPost!!.id, editingPost!!.title, editingPost!!.content)
                    editingPost = null
                }) {
                    Text(text = "Salvar", color = Color(0xFF0D47A1))
                }
            },
            dismissButton = {
                TextButton(onClick = { editingPost = null }) {
                    Text(text = "Cancelar", color = Color.Red)
                }
            }
        )
    }
}