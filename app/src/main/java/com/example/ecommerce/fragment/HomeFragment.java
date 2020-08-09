package com.example.ecommerce.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.activities.DetailsActivity;
import com.example.ecommerce.activities.VideoPlayer;
import com.example.ecommerce.helpers.AppFragment;
import com.example.ecommerce.helpers.AppRecyclerViewAdapter;
import com.example.ecommerce.helpers.DefineClassType;
import com.example.ecommerce.helpers.MyApplication;
import com.example.ecommerce.models.Categories;
import com.example.ecommerce.models.Products;
import com.example.ecommerce.models.Tutorials;
import com.example.ecommerce.presenters.CategoryPresenters;
import com.example.ecommerce.presenters.ProductPresenters;
import com.example.ecommerce.presenters.TutorialsPresenters;

import java.util.List;


public class HomeFragment extends AppFragment implements TutorialsPresenters.View, CategoryPresenters.View, ProductPresenters.View {

    private RecyclerView recyclerView;
    private String url="";
    private DashboardRecyclerViewAdapter dashboardRecyclerViewAdapter;
    private HeaderRecyclerViewAdapter headerRecyclerViewAdapter;
    private ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    FooterRecyclerViewAdapter footerRecyclerViewAdapter;
    private TutorialsPresenters tutorialsPresenters;
    private CategoryPresenters categoryPresenters;
    private ProductPresenters productPresenters;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        initializedView(view);
        initializedListners();
        prepareRecyclerView();
        return view;
    }

    @Override
    protected void initializedView(View view ) {
        recyclerView=view.findViewById(R.id.recycler_view);

    }

    @Override
    protected void initializedListners() {
        tutorialsPresenters=new TutorialsPresenters(this);
        tutorialsPresenters.tutorials();
        categoryPresenters= new CategoryPresenters(this);
        categoryPresenters.categories();
        productPresenters=new ProductPresenters(this);
        productPresenters.products();
        headerRecyclerViewAdapter=new HeaderRecyclerViewAdapter();
        itemRecyclerViewAdapter=new ItemRecyclerViewAdapter();
        footerRecyclerViewAdapter=new FooterRecyclerViewAdapter();

    }
    private void prepareRecyclerView() {
        dashboardRecyclerViewAdapter=new DashboardRecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(dashboardRecyclerViewAdapter);

    }
    @Override
    public void onResponseSuccess(Tutorials tutorials) {
        headerRecyclerViewAdapter.add(tutorials);
        headerRecyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResponseFailure(String message) {

    }

    @Override
    public void onCategoryResponseSuccess(Categories categories) {
        itemRecyclerViewAdapter.add(categories);
        itemRecyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCategoryResponseFailure(String message) {

    }

    @Override
    public void onProductResponseSuccess(Products products) {
        footerRecyclerViewAdapter.add(products);
        footerRecyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onProductResponseFailure(String message) {


    }

    public class DashboardRecyclerViewAdapter extends AppRecyclerViewAdapter {

        private String[] name=new String[]{"Local Product","Trending Product"};


        private final int TYPE_HEADER=1;
        private final int TYPE_ITEM=2;
        private final int TYPE_FOOTER=3;


        @Override
        public void add(Object object) {

        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(viewType==1){
                View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header,parent,false);
                return new VHHeader(itemView);
            }
            if(viewType==2){
                View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content,parent,false);
                return new VHItem(itemView);
            }
            else if(viewType==3){
                View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_local_product,parent,false);
                return new VHFooter(itemView);
            }
            throw new RuntimeException("View Type Doesnot Match");

        }

        @Override
        public int getItemViewType(int position) {
            if(isTypeHeader(position)){
                return TYPE_HEADER;
            }
            if(isTypeItem(position)){
                return TYPE_ITEM;
            }
            else
                return TYPE_FOOTER;

        }


        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof VHHeader){
                VHHeader vhHeader= (VHHeader) holder;
                prepareInnerHeaderRecyclerView(vhHeader.recyclerView);

            }
            if(holder instanceof VHItem){
                VHItem vhItem= (VHItem) holder;
                prepareInnerItemRecyclerView(vhItem.recyclerView);
            }
            if(holder instanceof VHFooter){
                VHFooter vhFooter= (VHFooter) holder;
                vhFooter.name.setText(name[position-2]);
                prepareInnerFooterRecyclerView(vhFooter.recyclerView);
            }

        }

        @Override
        public int getItemCount() {

            return name.length+2;
        }

    }

    private void prepareInnerFooterRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(footerRecyclerViewAdapter);

    }

    private void prepareInnerItemRecyclerView(RecyclerView recyclerView) {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(itemRecyclerViewAdapter);
    }

    private void prepareInnerHeaderRecyclerView(RecyclerView innerRecyclerView) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        innerRecyclerView.setLayoutManager(linearLayoutManager);
        innerRecyclerView.setAdapter(headerRecyclerViewAdapter);

    }

    private class HeaderRecyclerViewAdapter extends AppRecyclerViewAdapter{
        private Tutorials tutorials;

        @Override
        public void add(Object object) {
            tutorials= DefineClassType.getType(object,Tutorials.class);

        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemVieW=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tutorials,parent,false);
            return new VhInnerHeader(itemVieW);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if(holder instanceof VhInnerHeader) {
                VhInnerHeader vhInnerHeader= (VhInnerHeader) holder;
                List<Tutorials.Detail> list=tutorials.getDetails();
                vhInnerHeader.tvVideoTitle.setText(list.get(position).getName());
                vhInnerHeader.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), VideoPlayer.class);
//                        Intent intent=new Intent(getActivity(), CartActivity.class);
                        intent.putExtra("url",list.get(position).getVideoUrl());
                        startActivity(intent);

                    }
                });
            }


        }

        @Override
        public int getItemCount() {
            if(tutorials!=null){
                return tutorials.getDetails().size();
            }

            return 0;
        }
    }

    private class ItemRecyclerViewAdapter extends AppRecyclerViewAdapter{
        private Categories categories;
        private String[] categoryname=new String[]{"Shop By category","Trending Products","Resources","Weather","Calendar"};
        private Integer[] image=new Integer[]{
                R.drawable.ic_address,
                R.drawable.ic_trending,
                R.drawable.ic_mining,
                R.drawable.ic_cloudy,
                R.drawable.ic_calen,
        };

        @Override
        public void add(Object object) {
            categories=DefineClassType.getType(object,Categories.class);


        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories,parent,false);
            return new VhInnerItem(itemView);

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof VhInnerItem){
                VhInnerItem vhInnerItem= (VhInnerItem) holder;
                List<Categories.Detail> detailList=categories.getDetails();
                vhInnerItem.textView.setText(detailList.get(position).getCategoryNameEnglish());
                Glide.with(getActivity()).load(image[position]).into(vhInnerItem.image);
            }

        }

        @Override
        public int getItemCount() {
            if(categories!=null){
                return categories.getDetails().size();
            }
            return 0;
        }
    }
    private class FooterRecyclerViewAdapter extends AppRecyclerViewAdapter{
        private Products products;
        private Integer[] image=new Integer[]{
                R.drawable.spinich,
                R.drawable.pome,
                R.drawable.lime,
                R.drawable.kiwi,
                R.drawable.cherry,

        };
        private  String[] product_name=new String[]{"Spinach","Pomegranate","Lime","Kiwi","Cherry"};
        private  String[] current_price=new String[]{"NRs 20","NRs 200","NRs 30","NRs 65","NRs 50"};
        private  String[] full_price=new String[]{"30","300","50","80","65"};
        @Override
        public void add(Object object) {
            products=DefineClassType.getType(object,Products.class);

        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_products,parent,false);
            return new VhInnerFooter(itemView);

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof VhInnerFooter){
                List<Products.Detail> detailList=products.getDetails();

                url="https://pngimg.com/uploads/honey/honey_PNG86308.png";
                VhInnerFooter vhInnerFooter= (VhInnerFooter) holder;
                vhInnerFooter.product_name.setText(detailList.get(position).getNameinEnglish());
                vhInnerFooter.current_price.setText(String.valueOf(detailList.get(position).getCurrentPrice()));
                vhInnerFooter.full_price.setText(String.valueOf(detailList.get(position).getBasePrice()));
                Glide.with(MyApplication.getAppContext()).load(image[position]).into(vhInnerFooter.imageView);
//            Glide.with(MyApplication.getAppContext())
//                    .load(url)
//                    .placeholder(R.drawable.close_icon)
//                    .error(R.drawable.ic_play_button)
//                    .fitCenter()
//                    .into(vhInnerFooter.imageView);
                vhInnerFooter.cvProducts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Products.Detail detailList=products.getDetails().get(position);
                        Intent intent=new Intent(getActivity(), DetailsActivity.class);
                        Bundle b= new Bundle();
                        b.putSerializable("product",detailList);
                        intent.putExtras(b);
                        startActivity(intent);

                    }
                });


            }

        }

        @Override
        public int getItemCount() {
            if(products!=null){
                return products.getDetails().size();
            }
            return 0;
        }
    }

    private boolean isTypeHeader(int position) {
        return position==0;
    }
    private boolean isTypeItem(int position) {
        return position==1;
    }

    private class VHHeader extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        public VHHeader(View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.header_recycler_view);
        }
    }

    private class VHItem extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        public VHItem(View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.content_recycler_View);
        }
    }

    private class VHFooter extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private TextView name;
        public VHFooter(View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.footer_recycler_view);
            name=itemView.findViewById(R.id.localproduct);
        }
    }

    private class VhInnerHeader extends RecyclerView.ViewHolder {
        private CardView cardView;
        TextView tvVideoTitle;

        public VhInnerHeader(View itemVieW) {
            super(itemVieW);
            tvVideoTitle=itemVieW.findViewById(R.id.video_title);
            cardView=itemVieW.findViewById(R.id.video) ;

        }
    }

    private class VhInnerItem extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView image;
        public VhInnerItem(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.category_name);
            image=itemView.findViewById(R.id.category_image);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), DetailsActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

    private class VhInnerFooter extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView product_name;
        private TextView current_price;
        private TextView full_price;
        private CardView cvProducts;
        public VhInnerFooter(View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_name);
            current_price=itemView.findViewById(R.id.current_price);
            imageView=itemView.findViewById(R.id.product_image);
            full_price=itemView.findViewById(R.id.full_price);
            cvProducts=itemView.findViewById(R.id.cvProducts);
            full_price.setPaintFlags(full_price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);



        }
    }

}