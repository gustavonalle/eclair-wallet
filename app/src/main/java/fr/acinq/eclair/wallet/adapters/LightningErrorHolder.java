package fr.acinq.eclair.wallet.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import fr.acinq.eclair.wallet.R;
import fr.acinq.eclair.wallet.models.LightningPaymentError;

public class LightningErrorHolder extends RecyclerView.ViewHolder {

  private static final String TAG = "LightningErrorHolder";

  private final TextView mErrorCounter;
  private final TextView mErrorType;
  private final TextView mErrorCause;
  private final TextView mErrorOriginLabel;
  private final TextView mErrorOrigin;
  private final TextView mErrorOriginChannelIdLabel;
  private final TextView mErrorOriginChannelId;
  private final TextView mErrorHops;

  private final static String bullet = "&#9679;&nbsp;&nbsp;";

  public LightningErrorHolder(final View itemView) {
    super(itemView);
    mErrorCounter = itemView.findViewById(R.id.lightning_error_counter);
    mErrorType = itemView.findViewById(R.id.lightning_error_type);
    mErrorCause = itemView.findViewById(R.id.lightning_error_cause);
    mErrorOriginLabel = itemView.findViewById(R.id.lightning_error_origin_label);
    mErrorOrigin = itemView.findViewById(R.id.lightning_error_origin);
    mErrorOriginChannelIdLabel = itemView.findViewById(R.id.lightning_error_origin_channel_id_label);
    mErrorOriginChannelId = itemView.findViewById(R.id.lightning_error_origin_channel_id);
    mErrorHops = itemView.findViewById(R.id.lightning_error_hops);
  }

  @SuppressLint("SetTextI18n")
  public void bindErrorItem(final LightningPaymentError error, final int counter, final int total) {
    mErrorCounter.setText(itemView.getResources().getString(R.string.paymentfailure_error_counter, (counter + 1), total));
    mErrorType.setText(error.getType());
    if (error.getCause() != null) {
      mErrorCause.setText(itemView.getResources().getString(R.string.paymentfailure_error_cause, error.getCause().replace("$","")));
    }
    if (error.getOrigin() != null) {
      mErrorOriginLabel.setVisibility(View.VISIBLE);
      mErrorOrigin.setVisibility(View.VISIBLE);
      mErrorOrigin.setText(itemView.getResources().getString(R.string.paymentfailure_error_origin, error.getOrigin()));
    }
    if (error.getOriginChannelId() != null) {
      mErrorOriginChannelIdLabel.setVisibility(View.VISIBLE);
      mErrorOriginChannelId.setVisibility(View.VISIBLE);
      mErrorOriginChannelId.setText(error.getOriginChannelId());
    }
    if (error.getHops() != null && !error.getHops().isEmpty()) {
      final StringBuilder hopsBuilder = new StringBuilder();
      hopsBuilder.append("<div>Route used:</div>");
      for (String s : error.getHops()) {
        hopsBuilder.append("<div>").append(bullet).append(s.substring(0,12)).append("...</div>");
      }
      mErrorHops.setText(Html.fromHtml(hopsBuilder.toString()));
      mErrorHops.setVisibility(View.VISIBLE);
    }
  }

}
