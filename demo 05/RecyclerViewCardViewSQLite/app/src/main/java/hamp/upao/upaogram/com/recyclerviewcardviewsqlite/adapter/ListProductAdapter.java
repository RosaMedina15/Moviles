package hamp.upao.upaogram.com.recyclerviewcardviewsqlite.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hamp.upao.upaogram.com.recyclerviewcardviewsqlite.R;
import hamp.upao.upaogram.com.recyclerviewcardviewsqlite.entity.Product;

/**
 * Created by hamp on 19/09/2017.
 */

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ProductsViewHolder>  {

    //Context context;
    List<Product> lstProducts;

    public ListProductAdapter(List<Product> lstProducts) {
        //this.context=context;
        this.lstProducts = lstProducts;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsproducts,parent,false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        holder.id.setText(lstProducts.get(position).getId().toString());
        holder.name.setText(lstProducts.get(position).getName());
        holder.quantity.setText(lstProducts.get(position).getQuantity().toString());
        holder.image.setImageResource(R.drawable.food);
    }

    @Override
    public int getItemCount() {
        return lstProducts.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder {
        TextView name,quantity,id;
        ImageView image;
        ImageView deleteProduct;
        ImageView editProduct;

        public ProductsViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.product_id);
            name = (TextView) itemView.findViewById(R.id.product_name);
            quantity = (TextView) itemView.findViewById(R.id.product_quantity);
            image  = (ImageView) itemView.findViewById(R.id.product_image);

            deleteProduct = (ImageView)itemView.findViewById(R.id.delete_product);
            editProduct = (ImageView)itemView.findViewById(R.id.edit_product);
        }
    }
}
