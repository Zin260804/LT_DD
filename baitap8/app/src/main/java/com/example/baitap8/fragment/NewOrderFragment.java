package com.example.baitap8.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.baitap8.databinding.FragmentNeworderBinding;

public class NewOrderFragment extends Fragment {
        private FragmentNeworderBinding binding;

        public NewOrderFragment() {
            // Constructor mặc định
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            // Ánh xạ layout bằng View Binding
            binding = FragmentNeworderBinding.inflate(inflater, container, false);

            // Vị trí để load dữ liệu: Adapter, RecyclerView, v.v.

            return binding.getRoot();
        }
}
