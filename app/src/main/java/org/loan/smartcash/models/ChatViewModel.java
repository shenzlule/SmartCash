package org.loan.smartcash.models;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {
    private final MutableLiveData<List<ChatMessage>> messages = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<ChatMessage>> getMessages() {
        return messages;
    }

    public void sendMessage(String message) {
        List<ChatMessage> current = new ArrayList<>(messages.getValue());
        current.add(new ChatMessage(message, true)); // user message
        current.add(new ChatMessage("Support: Thank you, we received your message.", false)); // mock reply
        messages.setValue(current);
    }
}

