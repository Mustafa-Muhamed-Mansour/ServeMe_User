package com.serveme_user.message;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.serveme_user.R;
import com.serveme_user.adapter.MessageAdapter;
import com.serveme_user.databinding.FragmentMessageBinding;
import com.serveme_user.databinding.ItemMessageRightBinding;
import com.serveme_user.model.MessageModel;
import com.serveme_user.model.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MessageFragment extends Fragment {

    private NavController navController;
    private FragmentMessageBinding binding;

    private PopupMenu popupMenu;

    private ArrayList<MessageModel> messageModels;
    private MessageAdapter messageAdapter;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference retriveRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMessageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        messageModels = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageModels);
        binding.rVMessage.setAdapter(messageAdapter);
        binding.rVMessage.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rVMessage.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        firebaseAuth = FirebaseAuth.getInstance();
        retriveRef = FirebaseDatabase.getInstance().getReference();

        retriveRef
                .child("Messages")
                .child(firebaseAuth.getUid())
                .addValueEventListener(new ValueEventListener()
                {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
//                            messageModels.clear();
                            MessageModel messageModel = dataSnapshot.getValue(MessageModel.class);
                            messageModels.add(messageModel);
                        }
                        messageAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        binding.fabSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String message = binding.editMessage.getText().toString();

                if (TextUtils.isEmpty(message))
                {
                    Toast.makeText(getActivity(), "Please enter your message", Toast.LENGTH_SHORT).show();
                    binding.editMessage.requestFocus();
                    return;
                }

                else
                {

                    String randomKey = FirebaseDatabase.getInstance().getReference().push().getKey();

                    MessageModel messageModel = new MessageModel(randomKey, firebaseAuth.getUid(), message);

                    retriveRef
                            .child("Messages")
                            .child(firebaseAuth.getUid())
                            .child(randomKey)
                            .setValue(messageModel)
                            .addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void unused)
                                {
                                    Toast.makeText(getContext(), "Done , send The message", Toast.LENGTH_SHORT).show();
                                    binding.editMessage.setText(null);
                                }
                            }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
}