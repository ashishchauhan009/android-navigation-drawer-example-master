package net.simplifiedcoding.navigationdrawerexample.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.simplifiedcoding.navigationdrawerexample.Model.FaqCommon;
import net.simplifiedcoding.navigationdrawerexample.Model.NewTaskData;
import net.simplifiedcoding.navigationdrawerexample.Model.PendingTaskDetail;
import net.simplifiedcoding.navigationdrawerexample.R;
import net.simplifiedcoding.navigationdrawerexample.util.AndroidUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vibes on 6/3/17.
 */

public class AdapterMessageChain extends RecyclerView.Adapter<AdapterMessageChain.MyViewHolder>  {

    private Context context;
    private ArrayList<FaqCommon> faqModelsList;
    List<PendingTaskDetail.Response> data;
    String status;



    //    public AdapterNewTask(Context context, FaqModel data) {
//        this.context = context;
//        if (data != null) {
//            faqModelsList = data.getFaq();
//           // Log.e("check data on adapter",faqModelsList.toString());
//        }
//    }
    public AdapterMessageChain(Context context, List<PendingTaskDetail.Response> data, String status) {
        this.context = context;
        this.data = data;
       // this.status = status;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newmessage_chain, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (data.get(position).getUpdatedOn() != null && !data.get(position).getUpdatedOn().isEmpty()) {
            holder.responded_on.setText(" "+data.get(position).getUpdatedOn());
        }

        if (data.get(position).getCreatedBy() != null && !data.get(position).getCreatedBy().isEmpty()) {
            holder.created_by.setText(" " + data.get(position).getCreatedBy());
        }
        if (data.get(position).getRemark() != null && !data.get(position).getRemark().isEmpty()) {
            holder.task_message.setText(" " + data.get(position).getRemark());
        }
        if (data.get(position).getPreviousAttach() != null && !data.get(position).getPreviousAttach().isEmpty()) {
            holder.ll_prev_attach.setVisibility(View.VISIBLE);
            holder.attachment_prev_tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_attachment, 0, 0, 0);
            // tvAttachmentBottom.setText(" " + compTaskdataResponse.get(0).getDoc());
            holder.attachment_prev_tv.setText(" Click here");
        }
        if (data.get(position).getDoc() != null && !data.get(position).getDoc().isEmpty()) {
            holder.ll_curr_attach.setVisibility(View.VISIBLE);
            holder.attachment_current_tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_attachment, 0, 0, 0);
            // tvAttachmentBottom.setText(" " + compTaskdataResponse.get(0).getDoc());
            holder.attachment_current_tv.setText(" Click here");
            holder.attachment_current_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!data.get(position).getDoc().isEmpty()) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.get(position).getDoc()));
                        context.startActivity(browserIntent);
                    }
                }
            });
        }
        holder.attachment_prev_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!data.get(position).getPreviousAttach().isEmpty()) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.get(position).getPreviousAttach()));
                    context.startActivity(browserIntent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }





    class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView  created_on,created_by,task_message,
                responded_on,task_compon_date_tv,attachment_prev_tv,attachment_current_tv;
        LinearLayout ll_prev_attach,ll_curr_attach;

        public MyViewHolder(View v) {
            super(v);

            created_by = (TextView) v.findViewById(R.id.created_by);
            responded_on = (TextView) v.findViewById(R.id.responded_on);
            task_message = (TextView) v.findViewById(R.id.task_description_tv);
            ll_prev_attach = (LinearLayout) v.findViewById(R.id.ll_prev_attach);
            ll_curr_attach = (LinearLayout) v.findViewById(R.id.ll_curr_attach);
            attachment_prev_tv = (TextView) v.findViewById(R.id.attachment_prev_tv);
            attachment_current_tv = (TextView) v.findViewById(R.id.attachment_current_tv);

            ll_prev_attach.setVisibility(View.GONE);
            ll_curr_attach.setVisibility(View.GONE);



        }


    }



}

