package com.creativeshare.mrsool.activities_fragments.activity_home.client_home.fragments.fragment_home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creativeshare.mrsool.R;
import com.creativeshare.mrsool.activities_fragments.activity_home.client_home.activity.ClientHomeActivity;
import com.creativeshare.mrsool.models.OrderDataModel;
import com.creativeshare.mrsool.share.Common;
import com.creativeshare.mrsool.tags.Tags;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class Fragment_Delegate_Add_Offer extends Fragment{

    private final static  String TAG = "Data";
    private ImageView image_back;
    private LinearLayout ll_back,ll_client_container;
    private CircleImageView image;
    private TextView tv_client_name,tv_order_details,tv_order_address;
    private EditText edt_delivery_cost;
    private Button btn_accept,btn_refused;
    private OrderDataModel.OrderModel orderModel;
    private String current_language;
    private ClientHomeActivity activity;
    private AppBarLayout app_bar;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delegate_add_offer,container,false);
        initView(view);
        return view;
    }

    public static Fragment_Delegate_Add_Offer newInstance(OrderDataModel.OrderModel orderModel)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG,orderModel);

        Fragment_Delegate_Add_Offer fragment_delegate_add_offer = new Fragment_Delegate_Add_Offer();
        fragment_delegate_add_offer.setArguments(bundle);
        return fragment_delegate_add_offer;
    }

    private void initView(View view)
    {
        activity = (ClientHomeActivity) getActivity();
        Paper.init(activity);
        current_language = Paper.book().read("lang", Locale.getDefault().getLanguage());
        image_back = view.findViewById(R.id.image_back);

        if (current_language.equals("ar"))
        {
            image_back.setImageResource(R.drawable.ic_right_arrow);
        }else
        {

            image_back.setImageResource(R.drawable.ic_left_arrow);
        }
        ll_back = view.findViewById(R.id.ll_back);
        image = view.findViewById(R.id.image);
        tv_client_name = view.findViewById(R.id.tv_client_name);
        tv_order_details = view.findViewById(R.id.tv_order_details);
        tv_order_address = view.findViewById(R.id.tv_order_address);
        edt_delivery_cost = view.findViewById(R.id.edt_delivery_cost);
        btn_accept = view.findViewById(R.id.btn_accept);
        btn_refused = view.findViewById(R.id.btn_refused);
        ll_client_container = view.findViewById(R.id.ll_client_container);

        app_bar = view.findViewById(R.id.app_bar);


        Bundle bundle = getArguments();

        if (bundle!=null)
        {
            orderModel = (OrderDataModel.OrderModel) bundle.getSerializable(TAG);
            UpdateUI(orderModel);
        }

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.Back();
            }
        });

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckData();
            }
        });

        btn_refused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.delegateRefuse_FinishOrder(orderModel.getDriver_id(),orderModel.getClient_id(),orderModel.getOrder_id(),"refuse");

            }
        });


        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalRange= appBarLayout.getTotalScrollRange();
                if ((totalRange+verticalOffset)<=50)
                {
                    ll_client_container.setVisibility(View.GONE);
                }else
                    {
                        ll_client_container.setVisibility(View.VISIBLE);

                    }
            }
        });


    }



    private void UpdateUI(OrderDataModel.OrderModel orderModel) {
        if (orderModel!=null)
        {
            Picasso.with(activity).load(Tags.IMAGE_URL+orderModel.getClient_user_image()).placeholder(R.drawable.logo_only).fit().into(image);
            tv_client_name.setText(orderModel.getClient_user_full_name());
            tv_order_details.setText(orderModel.getOrder_details());
            tv_order_address.setText(orderModel.getClient_address());

        }

    }

    private void CheckData() {
        String m_cost = edt_delivery_cost.getText().toString().trim();
        if (!TextUtils.isEmpty(m_cost))
        {
            edt_delivery_cost.setError(null);
            Common.CloseKeyBoard(activity,edt_delivery_cost);
            activity.delegateAcceptOrder(orderModel.getDriver_id(),orderModel.getClient_id(),orderModel.getOrder_id(),m_cost);
        }else
            {
                edt_delivery_cost.setError(getString(R.string.field_req));
            }
    }


}
