package mx.webgang.tryone;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    SharedPreferences prefs;
    private static final int TYPE_SUMARRY = 0;
    private static final int TYPE_JOBS = 1;
    private static final int TYPE_EDUCATION = 2;
    private static final int TYPE_HEADER = 3;

    private ExpeManager expeM;
    private UserManagment userM;

    Expe[] expes;
    User usuario;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        int holderid;

        TextView SubHeader;
        TextView Header;
        TextView Content;
        LinearLayout Touch;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);

            if (ViewType == TYPE_HEADER) {
                SubHeader = (TextView) itemView.findViewById(R.id.subheader);
                holderid = 3;
            } else if (ViewType == TYPE_SUMARRY) {
                Header = (TextView) itemView.findViewById(R.id.header);
                Content = (TextView) itemView.findViewById(R.id.content);
                Touch = (LinearLayout) itemView.findViewById(R.id.touch);
                holderid = 0;
            }
        }
    }

    CardAdapter(Context context) {
        prefs = context.getSharedPreferences("main", 0);
        if (prefs.getBoolean("saved", false)) {
            expeM = new ExpeManager(context);
            expeM.open();
            expes = expeM.getAllExpe();
            expeM.close();

            userM = new UserManagment(context);
            userM.open();
            usuario = userM.getUser();
            userM.close();
        } else {
            System.out.println("No hay datos guardados");
        }
    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subheader, parent, false);
            return new ViewHolder(v, viewType);
        } else if (viewType == TYPE_SUMARRY) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
            return new ViewHolder(v, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
        if (prefs.getBoolean("saved", false)) {
            System.out.println("Cargar los datos desde la base de datos");
        } else {
            if (holder.holderid == 0) {
                holder.Touch.removeView(holder.Header);
                holder.Content.setText("Click aquí para llenar tu perfil");
                holder.Touch.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        System.out.println("Abrir la nueva Activity ñ_ñ");
                    }
                });
            } else {
                if (position == 0) holder.SubHeader.setText("No hay datos para mostrar");
                if (position == 2) holder.SubHeader.setText("Experiencia Profesional");
                if (expes != null && expes.length != 0) {
                    if (position == expes.length + 2)
                        holder.SubHeader.setText("Formación Académica");
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (prefs.getBoolean("saved", false)) {
            return 2;
        } else if (expes == null || expes.length == 0) {
            return 2;
        }
        return expes.length + 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 2) { //|| position == (expes.length + 2)) {
            return TYPE_HEADER;
        } else {
            return TYPE_SUMARRY;
        }
    }

}
