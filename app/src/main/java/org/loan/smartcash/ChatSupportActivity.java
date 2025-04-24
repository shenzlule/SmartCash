package org.loan.smartcash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.loan.smartcash.adapter.ChatAdapter;
import org.loan.smartcash.models.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatSupportActivity extends AppCompatActivity {
    private EditText editMessage;
    private ImageButton btnSend;
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> messageList;

    private final Handler handler = new Handler();

    private final String[] autoReplies = {
            "Hello! 👋 How can we help you today?",
            "We're reviewing your issue. Please hold on.",
            "Thanks for reaching out! A support agent will get back to you shortly.",
            "You can also email us at support@smartcash.com."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_support);

        editMessage = findViewById(R.id.editMessage);
        btnSend = findViewById(R.id.btnSend);
        recyclerView = findViewById(R.id.recyclerViewChat);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter();
        chatAdapter.setMessages(messageList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        btnSend.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String text = editMessage.getText().toString().trim();
        if (text.isEmpty()) return;

        ChatMessage userMessage = new ChatMessage(text, true);
        messageList.add(userMessage);
        chatAdapter.setMessages(messageList);
        editMessage.setText("");

        recyclerView.scrollToPosition(messageList.size() - 1);

        simulateAutoReply();
    }

    private void simulateAutoReply() {
        handler.postDelayed(() -> {
            int index = (int) (Math.random() * autoReplies.length);
            ChatMessage reply = new ChatMessage(autoReplies[index], false);
            messageList.add(reply);
            chatAdapter.setMessages(messageList);
            recyclerView.scrollToPosition(messageList.size() - 1);
        }, 1500); // Simulates support agent delay
    }
}
