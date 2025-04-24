package org.loan.smartcash;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.loan.smartcash.R;
import org.loan.smartcash.adapter.ChatAdapter;
import org.loan.smartcash.models.ChatViewModel;

public class LiveChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText messageInput;
    private ImageButton sendBtn;
    private ChatViewModel chatViewModel;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_chat);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.setStatusBarColor(ContextCompat.getColor(this, R.color.greenTheme));
//            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.greenTheme));
        }


        recyclerView = findViewById(R.id.recyclerChat);
        messageInput = findViewById(R.id.editMessage);
        sendBtn = findViewById(R.id.btnSend);

        adapter = new ChatAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        chatViewModel.getMessages().observe(this, messages -> {
            adapter.setMessages(messages);
            recyclerView.scrollToPosition(messages.size() - 1);
        });

        sendBtn.setOnClickListener(v -> {
            String msg = messageInput.getText().toString().trim();
            if (!msg.isEmpty()) {
                chatViewModel.sendMessage(msg);
                messageInput.setText("");
            }
        });
    }
}
