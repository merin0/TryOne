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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    public Intent callIntent;

    private String mNavTitles[];
    private int mIcons[];

    private String name;
    private String carrera;
    private int profile;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;

        TextView textView;
        ImageView imageView;
        ImageView profile;
        LinearLayout Lldrawer;
        TextView Name;
        TextView Carrera;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                Lldrawer = (LinearLayout) itemView.findViewById(R.id.lldrawer);
                Holderid = 1;
            } else {
                Name = (TextView) itemView.findViewById(R.id.name);
                profile = (ImageView) itemView.findViewById(R.id.circleView);
                Carrera = (TextView) itemView.findViewById(R.id.carrera);
                Holderid = 0;
            }
        }
    }

    MyAdapter(String Titles[], int Icons[], String Name, String Carrera, int Profile) {
        mNavTitles = Titles;
        mIcons = Icons;
        name = Name;
        carrera = Carrera;
        profile = Profile;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType);
            return vhItem;
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
            ViewHolder vhHeader = new ViewHolder(v, viewType);
            return vhHeader;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        final int pos = position;
        if (holder.Holderid == 1) {
            holder.textView.setText(mNavTitles[position - 1]);
            holder.imageView.setImageResource(mIcons[position - 1]);

            holder.Lldrawer.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    switch (pos) {
                        case 1:
                            System.out.println("Llama el celular ñ_ñ");
                            call(v, "tel:" + mNavTitles[pos - 1]);
                            break;
                        case 2:
                            System.out.println("Llama a Casa ñ_ñ");
                            call(v, "tel:" + mNavTitles[pos - 1]);
                            break;
                        case 3:
                            System.out.println("Envía un mail :D");
                            send_email(v, "ga.marin.ce@gmail.com");
                            break;
                        case 5:
                            System.out.println("Maps");
                            openMaps(v, mNavTitles[pos - 1]);
                            break;
                        default:
                            System.out.println("Click on item: " + pos);
                            break;
                    }
                }
            });
        } else {
            holder.profile.setImageResource(profile);
            holder.Name.setText(name);
            holder.Carrera.setText(carrera);

            holder.profile.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AnimateToolbar.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
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
