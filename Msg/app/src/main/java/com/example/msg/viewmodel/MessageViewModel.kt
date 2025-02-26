package com.example.msg.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.msg.model.Message
import com.example.msg.repository.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MessageViewModel(private val repository: MessageRepository) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allMessages.collect { _messages.value = it }
        }
    }

    fun addMessage(content: String) {
        viewModelScope.launch {
            val newMessage = Message(content = content, timestamp = System.currentTimeMillis())
            _messages.value = _messages.value + newMessage // Adiciona ao final da lista
            repository.addMessage(content)
        }
    }
}
