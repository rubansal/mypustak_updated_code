package com.mypushtak.app.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mypushtak.app.Activity.AddAddress;
import com.mypushtak.app.Activity.CartItemsActivity;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.CartItems;
import com.mypushtak.app.Singleton.ProductviewSignleton;
import com.squareup.picasso.Picasso;

import java.util.List;


/*
 ****@author Anubhav Kumar
 * *****
 */

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {

    private Context context;
    private List<CartItems> cartItems;

    public CartItemsAdapter(){

    }

    public CartItemsAdapter(Context context, List<CartItems> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }


    @NonNull
    @Override
    public CartItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_container,parent,false);

        return new CartItemsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemsAdapter.ViewHolder holder, final int position) {

        final CartItems cartItem=cartItems.get(position);

        String thumb=cartItem.getThumb();
        final int book_id=cartItem.getBook_id();
        String name=cartItem.getTitle();
        String authors=cartItem.getAuthor();
        int price=cartItem.getPrice();
        int qty=cartItem.getQty();
        int shipping=cartItem.getShipping();
        int handling=cartItem.getHandelling();

        int ship_hand=shipping+handling;
        holder.title.setText(name);
        holder.mrp.setText("MRP: "+context.getResources().getString(R.string.Rs)+price);
        holder.shipping.setText("SHIPPING/HANDELLING: "+context.getResources().getString(R.string.rs)+ship_hand);
        holder.qty.setText("QTY :"+qty);


//        Log.d("adapters","  "+thumb+"  "+book_id+"   "+"   "+name+"   "+author+"    "+price);

        Uri uri= Uri.parse("https://s3.amazonaws.com/mypustak_new/uploads/books/"+thumb);

        Log.d("adapters22",""+book_id+"   "+uri+"          "+name);
//        icon.setImageURI(uri);

        Picasso.get().load(uri).fit().into(holder.book_image);

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove_cart(book_id);
                cartItems.remove(cartItem);
                new CartItemsAdapter().notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }



    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title,mrp,shipping,qty;
        private ImageView book_image;
        private Button remove,wishlist;

        public ViewHolder(View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.cart_container_title);
            mrp=itemView.findViewById(R.id.cart_container_mrp);
            shipping=itemView.findViewById(R.id.cart_container_shipping);
            qty=itemView.findViewById(R.id.cart_containers_quantity);
            book_image=itemView.findViewById(R.id.cart_container_image);
            remove=itemView.findViewById(R.id.cart_container_remove);
            wishlist=itemView.findViewById(R.id.cart_container_wishlist);
        }
    }

    private void remove_cart(int book_id) {
        RequestQueue donationqueue = Volley.newRequestQueue(context);
        String donor_url = "http://192.168.43.243:8080/remove_cart/"+book_id+"/214";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, donor_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                    Log.d("onResponseString", "response: " + responseString);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        donationqueue.add(stringRequest);
    }
}

