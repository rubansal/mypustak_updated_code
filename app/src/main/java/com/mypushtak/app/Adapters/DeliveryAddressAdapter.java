package com.mypushtak.app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mypushtak.app.Activity.DeliveryAddress;
import com.mypushtak.app.Activity.EditAddress;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.CartItems;
import com.mypushtak.app.Singleton.Delivery_Address;

import java.util.List;

public class DeliveryAddressAdapter extends RecyclerView.Adapter<DeliveryAddressAdapter.ViewHolder> {

    private Context context;
    private List<Delivery_Address> delivery_addresses;

    public DeliveryAddressAdapter(Context context, List<Delivery_Address> delivery_addresses) {
        this.context = context;

        this.delivery_addresses=delivery_addresses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.address_container,parent,false);

        return new DeliveryAddressAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.bookphoto.setImageResource(icons[position]);
        final Delivery_Address address=delivery_addresses.get(position);
        holder.address.setText(address.getRec_name()+"\n"+address.getAddress()+",\nLandmark:\t "
                +address.getLandmarkh()+",\nCity:\t "+address.getCity()+",\nState: \t "+address.getState_name()+",\nCountry : "+address.getCountry()+",\n Pincode: "+address.getPincode());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, EditAddress.class);
                i.putExtra("addressid",address.getAddress_id());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return delivery_addresses.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView address;
        private Button select,edit;

        public ViewHolder(View itemView) {
            super(itemView);

            address=itemView.findViewById(R.id.container_address);
            select=itemView.findViewById(R.id.container_select);
            edit=itemView.findViewById(R.id.container_edit);
        }
    }
}
