package mx.webgang.tryone;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MiniAdapter extends RecyclerView.Adapter<MiniAdapter.ViewHolder> {

    private static final int TYPE_ITEM = 1;

    public Intent callIntent;
    private String mNavTitles[];
    private int mIcons[];

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView; //Text for the list
        ImageView imageView; //Image for the list
        LinearLayout Lldrawer;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.rowText);
            imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
            Lldrawer = (LinearLayout) itemView.findViewById(R.id.lldrawer);
        }
    }

    MiniAdapter(String Titles[], int Icons[]) {
        mNavTitles = Titles;
        mIcons = Icons;
    }

    @Override
    public MiniAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType);
            return vhItem;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MiniAdapter.ViewHolder holder, int position) {
        final int pos = position;

        holder.textView.setText(mNavTitles[position]); // Setting the Text with the array of our Titles
        holder.imageView.setImageResource(mIcons[position]);// Setting the image with array of our icons

        holder.Lldrawer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (pos) {
                    case 0:
                        System.out.println("Llama el celular ñ_ñ");
                        call(v, "tel:" + mNavTitles[pos]);
                        break;
                    case 1:
                        System.out.println("Llama a Casa ñ_ñ");
                        call(v, "tel:" + mNavTitles[pos]);
                        break;
                    case 2:
                        System.out.println("Envía un mail :D");
                        send_email(v, "ga.marin.ce@gmail.com");
                        break;
                    case 4:
                        System.out.println("Maps");
                        openMaps(v, mNavTitles[pos]);
                        break;
                    default:
                        System.out.println("Click on item: " + pos);
                        break;
                }
            }
        });
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return mNavTitles.length;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    private void call(View v, String tel) {
        try {
            callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(tel));
            v.getContext().startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
            System.out.println("Falló la llamada a  " + tel);
        }
    }

    private void send_email(View v, String mail) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
        i.putExtra(Intent.EXTRA_SUBJECT, "Postulación para la vacante de programador");
        i.putExtra(Intent.EXTRA_TEXT, "Buenos Diás, Me gustaría postularme para la vacante de programador ñ_ñ");
        try {
            v.getContext().startActivity(Intent.createChooser(i, "Enviar e-mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(v.getContext(), "No hay cliente ", Toast.LENGTH_SHORT).show();
        }
    }

    private void openMaps(View v, String address) {
        try {
            address = address.replace(' ', '+');
            Intent geoIntent = new Intent(
                    android.content.Intent.ACTION_VIEW, Uri
                    .parse("geo:0,0?q=" + address));

            v.getContext().startActivity(geoIntent);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
