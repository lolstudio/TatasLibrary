//package com.tata.android.adapter;
//
//import java.util.List;
//
//import android.content.Context;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.tata.android.adapter.base.AbstractBaseAdapter;
//import com.tata.android.bean.Card;
//import com.tata.android.main.R;
//
//public class CommonAdapter extends AbstractBaseAdapter<Card> {
//    
//    CardHolder holder = new CardHolder();
//    
//    public CommonAdapter(Context context, List<Card> mData2) {
//        super(context, mData2);
//    }
//    
//    @Override
//    public int onCreateItemLayout(int itemViewType) {
//        return R.layout.list_item_view;
//    }
//    
//    @Override
//    public BaseViewHolder getViewHolderInstance(int itemViewType) {
//        return holder;
//    }
//    
//    static class CardHolder implements BaseViewHolder {
//        TextView tv_name;
//        Button btn_ok;
//    }
//    
//    @Override
//    public void handleViewAndData(View view, Card data, int position, BaseViewHolder holder) {
//        CardHolder cardholder = (CardHolder) holder;
//        cardholder.tv_name.setText(data.getName());
//    }
//    
//}
