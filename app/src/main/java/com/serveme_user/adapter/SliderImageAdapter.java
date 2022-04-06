package com.serveme_user.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.serveme_user.databinding.ItemSliderBinding;
import com.serveme_user.model.SliderModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderImageAdapter extends SliderViewAdapter<SliderImageAdapter.SliderImageViewHolder>
{

    private ArrayList<SliderModel> sliderModels;

    public SliderImageAdapter(ArrayList<SliderModel> sliderModels)
    {
        this.sliderModels = sliderModels;
    }

    @Override
    public SliderImageViewHolder onCreateViewHolder(ViewGroup parent)
    {
        return new SliderImageViewHolder(ItemSliderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(SliderImageViewHolder viewHolder, int position)
    {
        SliderModel model = sliderModels.get(position);
        viewHolder.binding.itemImgSlider.setImageResource(model.getImageSlider());
        viewHolder.binding.itemTxtSlider.setText(model.getNameSlider());
    }

    @Override
    public int getCount()
    {
        return sliderModels.size();
    }


    public class SliderImageViewHolder extends SliderViewAdapter.ViewHolder
    {

        private ItemSliderBinding binding;

        public SliderImageViewHolder(@NonNull ItemSliderBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
